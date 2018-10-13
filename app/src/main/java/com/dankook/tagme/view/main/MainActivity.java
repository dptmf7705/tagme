package com.dankook.tagme.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.R;
import com.dankook.tagme.databinding.ActivityMainBinding;
import com.dankook.tagme.utils.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements MainContract.View {

    final int[] gridMenuIcon = {
            R.drawable.icon_menu_point,
            R.drawable.icon_menu_coupon,
            R.drawable.icon_menu_crown
    };

    private MainPresenter presenter;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MainPresenter(this);

        initView();
        initDrawerMenu();
        initMap();
    }

    private void initView() {
        /* toolbar init */
        binding.layoutToolbar.btnLeft.setImageResource(R.drawable.icon_menu);
        binding.layoutToolbar.btnRight.setImageResource(R.drawable.icon_search);
        binding.layoutToolbar.ivCenter.setImageResource(R.drawable.icon_logo_text);
        binding.layoutToolbar.tvCenter.setVisibility(View.GONE);

        /* drawable menu init */
        binding.layoutToolbar.btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.drawerLayout.isDrawerOpen(binding.drawerMenu)) {
                    binding.drawerLayout.closeDrawer(binding.drawerMenu);
                } else {
                    binding.drawerLayout.openDrawer(binding.drawerMenu);
                }
            }
        });

    }

    private void initDrawerMenu() {
        binding.layoutDrawerMenu.tvUserName.setText("이예슬님");
        binding.layoutDrawerMenu.btnUserProfile.setImageResource(R.drawable.icon_next);

        /* grid view menu init */
        String[] gridMenuName = resources.getStringArray(R.array.drawer_gird_menu);
        List<MenuVO> gridMenu = new ArrayList<>();
        for(int i = 0 ; i < gridMenuName.length ; i++){
            gridMenu.add(new MenuVO(gridMenuName[i], gridMenuIcon[i]));
        }
        GridMenuAdapter gridAdapter = new GridMenuAdapter(this, gridMenu);
        binding.layoutDrawerMenu.gridView.setLayoutManager(new GridLayoutManager(this, 3));
        binding.layoutDrawerMenu.gridView.addItemDecoration(new SimpleDividerItemDecoration(this));
        binding.layoutDrawerMenu.gridView.setAdapter(gridAdapter);

        /* list view menu init */
        String[] listMenuName = resources.getStringArray(R.array.drawer_list_menu);
        List<MenuVO> listMenu = new ArrayList<>();
        for(int i = 0 ; i < listMenuName.length ; i++){
            listMenu.add(new MenuVO(listMenuName[i], R.drawable.icon_next));
        }
        ListMenuAdapter listAdapter = new ListMenuAdapter(this, listMenu);
        binding.layoutDrawerMenu.listView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.layoutDrawerMenu.listView.addItemDecoration(new SimpleDividerItemDecoration(this));
        binding.layoutDrawerMenu.listView.setAdapter(listAdapter);
    }

    private void initMap() {
        /* map view init */
        NMapFragment fragment = new NMapFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.map_container, fragment);
        fragmentTransaction.commit();
    }

}

