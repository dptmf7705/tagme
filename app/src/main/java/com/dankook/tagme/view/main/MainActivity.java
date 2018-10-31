package com.dankook.tagme.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.ActivityMainBinding;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.view.main.drawerMenu.DrawerMenuFragment;
import com.dankook.tagme.view.main.map.NMapFragment;
import com.dankook.tagme.view.store.storeList.StoreListFragment;

public class MainActivity extends BaseActivity<ActivityMainBinding>{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

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
        binding.viewPagerStoreList.setAdapter(new StoreListPagerAdapter(getSupportFragmentManager()));
        binding.viewPagerStoreList.setCurrentItem(0);

        // 탭 생성
        binding.tabStoreType.setupWithViewPager(binding.viewPagerStoreList);
        String[] storeTypeArr = getResources().getStringArray(R.array.store_type);
        for(int i = 0 ; i < storeTypeArr.length ; i++){
            binding.tabStoreType.getTabAt(i).setText(storeTypeArr[i]);
        }

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

        private String[] storeTypeKeys = getResources().getStringArray(R.array.store_type_key);
        private final int NUM_OF_TAB = storeTypeKeys.length;

        public StoreListPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return StoreListFragment.newInstance(storeTypeKeys[position]);
        }

        @Override
        public int getCount() {
            return NUM_OF_TAB;
        }
    }

}

