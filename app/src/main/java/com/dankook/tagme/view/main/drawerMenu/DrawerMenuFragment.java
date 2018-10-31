package com.dankook.tagme.view.main.drawerMenu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.FragmentDrawerMenuBinding;
import com.dankook.tagme.utils.SimpleDividerItemDecoration;
import com.dankook.tagme.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class DrawerMenuFragment extends BaseFragment<FragmentDrawerMenuBinding> {

    final int[] gridMenuIcon = {
            R.drawable.icon_menu_point,
            R.drawable.icon_menu_coupon,
            R.drawable.icon_menu_crown
    };

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_drawer_menu;
    }

    public DrawerMenuFragment(){}

    public static DrawerMenuFragment newInstance() {
        return new DrawerMenuFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {

        binding.tvUserName.setText("이예슬님");
        binding.btnUserProfile.setImageResource(R.drawable.icon_next);

        // TODO 리사이클러뷰로 변경하기
        String[] gridMenuName = getResources().getStringArray(R.array.drawer_gird_menu);
        List<MenuVO> gridMenu = new ArrayList<>();
        for(int i = 0 ; i < gridMenuName.length ; i++){
            gridMenu.add(new MenuVO(gridMenuName[i], gridMenuIcon[i]));
        }
        GridMenuAdapter gridAdapter = new GridMenuAdapter(context, gridMenu);
        binding.gridView.setLayoutManager(new GridLayoutManager(context, 3));
        binding.gridView.addItemDecoration(new SimpleDividerItemDecoration(context));
        binding.gridView.setAdapter(gridAdapter);

        // TODO 리사이클러뷰로 변경하기
        String[] listMenuName = getResources().getStringArray(R.array.drawer_list_menu);
        List<MenuVO> listMenu = new ArrayList<>();
        for(int i = 0 ; i < listMenuName.length ; i++){
            listMenu.add(new MenuVO(listMenuName[i], R.drawable.icon_next));
        }
        ListMenuAdapter listAdapter = new ListMenuAdapter(context, listMenu);
        binding.listView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.listView.addItemDecoration(new SimpleDividerItemDecoration(context));
        binding.listView.setAdapter(listAdapter);
    }

}
