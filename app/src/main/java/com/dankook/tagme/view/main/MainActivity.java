package com.dankook.tagme.view.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.dankook.tagme.R;
import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.databinding.ActivityMainBinding;
import com.dankook.tagme.model.Category;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.view.main.drawerMenu.DrawerMenuFragment;
import com.dankook.tagme.view.main.map.NMapFragment;
import com.dankook.tagme.view.store.storeList.StoreListFragment;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends BaseActivity<ActivityMainBinding>{

    private List<Category> categoryList = new ArrayList<>();
    private StoreListPagerAdapter adapter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    @SuppressLint("CheckResult")
    private void initView() {

        // 툴바 생성
        binding.layoutToolbar.btnLeft.setImageResource(R.drawable.icon_menu);
        binding.layoutToolbar.btnLeft.setOnClickListener(v -> toggleDrawerMenu());
        binding.layoutToolbar.btnRight.setImageResource(R.drawable.icon_search);
        binding.layoutToolbar.ivCenter.setImageResource(R.drawable.icon_logo_text);

        // 맵뷰 생성
        NMapFragment fragment = new NMapFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.map_container, fragment)
                .commit();

        // 뷰페이저 생성
        adapter = new StoreListPagerAdapter(getSupportFragmentManager());
        binding.viewPagerStoreList.setAdapter(adapter);
        binding.viewPagerStoreList.setCurrentItem(0);

        // 탭 생성
        binding.tabStoreType.setupWithViewPager(binding.viewPagerStoreList);

        // 카테고리 리스트 서버에서 받아오기
        StoreRepository.getInstance().getCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                            categoryList = response;

                            adapter.updateTabItems(categoryList);

                            // 받아온 카테고리 목록을 탭에 등록
                            if(categoryList != null) {
                                for (int i = 0 ; i < categoryList.size() ; i++) {
                                    binding.tabStoreType.getTabAt(i).setText(categoryList.get(i).getCategoryNameKor());
                                }
                            }
                        },
                        error -> Log.d("getCategory", "error"));

        // 드로어 메뉴 생성
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.drawer_menu, DrawerMenuFragment.newInstance())
                .commit();
    }

    /**
     * 드로어 메뉴 open & close */
    private void toggleDrawerMenu() {
        if(binding.drawerLayout.isDrawerOpen(binding.drawerMenu)){
            binding.drawerLayout.closeDrawer(binding.drawerMenu);
        } else {
            binding.drawerLayout.openDrawer(binding.drawerMenu);
        }
    }

    /**
     * 카테고리별 가게 목록 보여줄 뷰페이저의 어댑터 */
    private class StoreListPagerAdapter extends FragmentStatePagerAdapter{

        List<Category> itemList = new ArrayList<>();

        public StoreListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void updateTabItems(List<Category> itemList){
            this.itemList.addAll(itemList);
            notifyDataSetChanged();
        }

        @Override
        public Fragment getItem(int position) {

            Log.d("getitem", "getitem position" + position);
            return StoreListFragment.newInstance(this.itemList.get(position).getCategoryKey());
        }

        @Override
        public int getCount() {
            return this.itemList.size();
        }
    }

}

