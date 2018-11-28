package com.dankook.tagme.view.store.storeDetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.SnapHelper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dankook.tagme.R;
import com.dankook.tagme.data.source.store.StoreRepository;
import com.dankook.tagme.databinding.ActivityStoreDetailBinding;
import com.dankook.tagme.mapper.RequestMapper;
import com.dankook.tagme.model.Menu;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.view.listener.AppBarStateChangeListener;
import com.dankook.tagme.view.listener.OnDataLoadedListener;
import com.dankook.tagme.view.listener.OnMenuItemClickListener;
import com.dankook.tagme.view.store.storeMenu.StoreMenuFragment;
import com.dankook.tagme.view.store.storeNFC.StoreNfcFragment;
import com.dankook.tagme.view.store.storeReview.StoreReviewFragment;

public class StoreDetailActivity extends BaseActivity<ActivityStoreDetailBinding> implements StoreDetailContract.View, OnMenuItemClickListener{

    public static final String EXTRA_STORE_KEY = "EXTRA_STORE_KEY";
    public static final String EXTRA_IS_DYNAMIC_LINK = "EXTRA_IS_DYNAMIC_LINK";
    public static final String EXTRA_TABLE_NUMBER = "EXTRA_TABLE_NUMBER";

    public final ObservableBoolean isCollapse = new ObservableBoolean(false);
    public final ObservableInt amount = new ObservableInt(1);
    public final ObservableInt totalPrice = new ObservableInt(0);

    private int storeKey;
    private boolean isDynamicLink;
    private int tableNumber;

    private StoreDetailPresenter presenter;
    private StoreImageAdapter adapter;

    private StorePagerAdapter pagerAdapter;

    private final int NUM_OF_PAGES = 3;
    private String[] TAB_NAMES = {
            "메뉴",
            "리뷰",
            "NFC 테스트"
    };

    private Handler handler;
    private Runnable runnable;
    private LinearLayoutManager layoutManager;
    private BottomSheetBehavior<LinearLayout> behavior;
    private PagerSnapHelper snapHelper;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_detail;
    }

    private void getExtraBundles(Intent intent) {
        isDynamicLink = intent.getBooleanExtra(EXTRA_IS_DYNAMIC_LINK, false);
        if(isDynamicLink){
            tableNumber = intent.getIntExtra(EXTRA_TABLE_NUMBER, 1);
        }
        storeKey = intent.getIntExtra(EXTRA_STORE_KEY, 0);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtraBundles(getIntent());

        // 프레젠터 생성
        presenter = new StoreDetailPresenter(this, StoreRepository.getInstance(), storeKey, isDynamicLink);

        // 이미지 어댑터 생성 후 프레젠터에 등록
        adapter = new StoreImageAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {

        binding.setActivity(this);

        behavior = BottomSheetBehavior.from(binding.bottomSheet);
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        // 툴바 생성
        binding.content.btnLeft.setImageResource(R.drawable.icon_toolbar_back);
        binding.content.btnLeft.setOnClickListener(v -> onBackPressed());

        // 앱바 리스너
        binding.appBar.addOnOffsetChangedListener(new AppBarStateChangeListener(){
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if(State.EXPANDED == state){
                    isCollapse.set(false);
                }else if(State.COLLAPSED == state){
                    isCollapse.set(true);
                }
            }
        });

        // 바텀시트 리스너
        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if(newState == BottomSheetBehavior.STATE_HIDDEN){ // 닫힐 때
                    binding.darkPanel.setVisibility(View.GONE);
                    amount.set(1);
                } else if(newState == BottomSheetBehavior.STATE_EXPANDED){
                    binding.darkPanel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerStoreImage.setLayoutManager(layoutManager);
        binding.recyclerStoreImage.setAdapter(adapter);

        if(snapHelper != null) {
            snapHelper = new PagerSnapHelper();
            snapHelper.attachToRecyclerView(binding.recyclerStoreImage);
        }

        binding.viewPager.setOffscreenPageLimit(3);
    }

    public void closeBottomSheet(){
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    public void submitOrder(int menuKey){
        presenter.submitOrder(RequestMapper.InsertOrdertMapping(storeKey, tableNumber, menuKey, amount.get()));
    }

    public void plusAmount(boolean plus, int price){

        if(plus){
            amount.set(amount.get()+1);
        } else if(amount.get() > 1) {
            amount.set(amount.get()-1);
        } else {
            return;
        }

        totalPrice.set(amount.get() * price);
    }

    /**
     * 액티비티가 실행되는 중에 태깅했을 경우 호출됨 */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        getExtraBundles(intent);

        initView();

        presenter.onViewCreated();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void onStoreDetailDataLoaded(Store store) {

        binding.setStore(store);

        // 탭 등록
        pagerAdapter = new StorePagerAdapter(getSupportFragmentManager());
        binding.viewPager.setAdapter(pagerAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewPager);
        for(int i = 0 ; i < NUM_OF_PAGES ; i++){
            binding.tabLayout.getTabAt(i).setText(TAB_NAMES[i]);
            Fragment fragment = pagerAdapter.getItem(i);
            if (fragment instanceof OnDataLoadedListener) {
                ((OnDataLoadedListener) fragment).onDataLoaded(store);
            }
        }

        // 자동넘기기
        if(handler == null) {
            handler = new Handler();
            runnable = new Runnable() {
                @Override
                public void run() {
                    if (layoutManager.findFirstVisibleItemPosition() != adapter.getItemCount()-1) {
                        binding.recyclerStoreImage.smoothScrollToPosition(layoutManager.findFirstVisibleItemPosition() + 1);
                    } else {
                        binding.recyclerStoreImage.smoothScrollToPosition(0);
                    }
                    handler.postDelayed(this, 4000);
                }
            };
        }
        handler.postDelayed(runnable, 4000);
    }

    @Override
    public void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onMenuItemClick(Menu menu) {
        if(isDynamicLink) {
            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            binding.bottomSheet.setOnTouchListener((v, event) -> true);
            binding.setMenu(menu);
            totalPrice.set(menu.getMenuPrice());
        } else {
            Toast.makeText(this, "NFC를 태그해주세요!", Toast.LENGTH_SHORT).show();
        }
    }

    public class StorePagerAdapter extends FragmentStatePagerAdapter{

        private Fragment[] fragments = new Fragment[NUM_OF_PAGES];

        public StorePagerAdapter(FragmentManager fm) {
            super(fm);
            fragments[0] = StoreMenuFragment.newInstance();
            fragments[1] = StoreReviewFragment.newInstance();
            fragments[2] = StoreNfcFragment.newInstance(storeKey, tableNumber, isDynamicLink);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments[position];
        }

        @Override
        public int getCount() {
            return NUM_OF_PAGES;
        }
    }
}
