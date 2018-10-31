package com.dankook.tagme.view.store.storeDetail;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.dankook.tagme.R;
import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.utils.DynamicLinkUtil;
import com.dankook.tagme.utils.GlideUtil;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.databinding.ActivityStoreDetailBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.dynamiclinks.DynamicLink;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;
import com.google.firebase.dynamiclinks.ShortDynamicLink;

public class StoreDetailActivity extends BaseActivity<ActivityStoreDetailBinding> implements StoreDetailContract.View{

    public static final String EXTRA_STORE_KEY = "EXTRA_STORE_KEY";
    public static final String EXTRA_IS_DYNAMIC_LINK = "EXTRA_IS_DYNAMIC_LINK";
    public static final String EXTRA_TABLE_NUMBER = "EXTRA_TABLE_NUMBER";

    private boolean isDynamicLink;
    private String storeKey;
    private String tableNumber;

    private StoreDetailPresenter presenter;
    private StoreMenuAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_store_detail;
    }

    private void getExtraBundles(Intent intent) {
        isDynamicLink = intent.getBooleanExtra(EXTRA_IS_DYNAMIC_LINK, false);
        if(isDynamicLink){
            tableNumber = intent.getStringExtra(EXTRA_TABLE_NUMBER);
        }
        storeKey = intent.getStringExtra(EXTRA_STORE_KEY);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtraBundles(getIntent());

        initView();
    }

    private void initView() {

        presenter = new StoreDetailPresenter(this, StoreRepository.getInstance(), storeKey);
        presenter.isDynamicLink.set(isDynamicLink);
        binding.content.setPresenter(presenter);

        // 툴바 생성
        binding.toolbar.btnLeft.setImageResource(R.drawable.icon_toolbar_back);
        binding.toolbar.btnLeft.setOnClickListener(v -> onBackPressed());

        adapter = new StoreMenuAdapter(this);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        // 메뉴 리사이클러뷰 생성
        binding.content.recyclerStoreMenu.setLayoutManager(new GridLayoutManager(this, 2));
        binding.content.recyclerStoreMenu.setNestedScrollingEnabled(false);
        binding.content.recyclerStoreMenu.setAdapter(adapter);

        // 태그 접속이랑 일반 접속 구분
        if(isDynamicLink) {
            // 태그 접속
            binding.content.tvTableNumber.setText(tableNumber);
        } else {
            // 일반 접속
            binding.content.btnDone.setOnClickListener(v -> {
                String tableNumber = binding.content.etTableNumber.getText().toString();
                makeDynamicLink(getStoreDeepLink(tableNumber));
            });
        }

        presenter.onViewCreated();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        getExtraBundles(intent);

        initView();
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
        binding.toolbar.tvCenter.setText(store.getStoreName());
        GlideUtil.loadImage(binding.ivAppbarMain, store.getMainImageUrl());
    }

    private Uri getStoreDeepLink(String tableNumber){

        StringBuilder sb = new StringBuilder();
        sb.append("https://tagme.com/");
        sb.append(DynamicLinkUtil.SEGMENT_STORE);
        sb.append("?");
        sb.append(DynamicLinkUtil.STORE_KEY);
        sb.append("=");
        sb.append(storeKey);
        sb.append("&");
        sb.append(DynamicLinkUtil.TABLE_NUMBER);
        sb.append("=");
        sb.append(tableNumber);

        return Uri.parse(sb.toString());
    }

    private void makeDynamicLink(Uri uri){

        FirebaseDynamicLinks.getInstance().createDynamicLink()
                .setLink(uri)
                // Firebase 콘솔에 있는 고유값
                .setDynamicLinkDomain("tagme.page.link")
                .setAndroidParameters(
                        new DynamicLink.AndroidParameters.Builder()
                                .build())
//            앱 미리보기 페이지를 건너뛰고 리다이렉션
//            .setNavigationInfoParameters(new DynamicLink.NavigationInfoParameters.Builder()
//                    .setForcedRedirectEnabled(true)
//                    .build())
//            링크와 연결된 타이틀, 이미지 설정
//            .setSocialMetaTagParameters(new DynamicLink.SocialMetaTagParameters.Builder()
//                    .setTitle("")
//                    .setImageUrl(null)
//                    .build())
                .buildShortDynamicLink()
                .addOnCompleteListener(this, new OnCompleteListener<ShortDynamicLink>() {
                    @Override
                    public void onComplete(@NonNull Task<ShortDynamicLink> task) {
                        if(task.isSuccessful()){
                            Uri shortLink = task.getResult().getShortLink();
                            try {
                                Intent sendIntent = new Intent();
                                sendIntent.setAction(Intent.ACTION_SEND);
                                sendIntent.putExtra(Intent.EXTRA_TEXT, shortLink.toString());
                                sendIntent.setType("text/plain");
                                startActivity(Intent.createChooser(sendIntent, "Share"));
                            } catch (ActivityNotFoundException exception) {

                            }
                        } else {
                            Log.w("Dynamic Link", task.toString());
                        }
                    }
                });
    }

}
