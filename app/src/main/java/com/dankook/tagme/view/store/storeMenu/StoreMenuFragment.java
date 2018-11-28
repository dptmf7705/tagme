package com.dankook.tagme.view.store.storeMenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.dankook.tagme.R;
import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.source.store.StoreRepository;
import com.dankook.tagme.databinding.FragmentStoreMenuBinding;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.BaseFragment;
import com.dankook.tagme.view.listener.OnDataLoadedListener;
import com.dankook.tagme.view.listener.OnMenuItemClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreMenuFragment extends BaseFragment<FragmentStoreMenuBinding> implements OnDataLoadedListener{

    private OnMenuItemClickListener menuClickListener;

    private StoreMenuAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnMenuItemClickListener){
            menuClickListener = (OnMenuItemClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnMenuItemClickListener");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store_menu;
    }

    public static StoreMenuFragment newInstance(){

        return new StoreMenuFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new StoreMenuAdapter(context);
        adapter.setOnItemClickListener(position -> menuClickListener.onMenuItemClick(adapter.getItem(position)));

        initView();
    }

    @SuppressLint("CheckResult")
    private void initView() {

        // 메뉴 리사이클러뷰 생성
        binding.recyclerMenu.setLayoutManager(new GridLayoutManager(context, 2));
        binding.recyclerMenu.setNestedScrollingEnabled(false);
        binding.recyclerMenu.setAdapter(adapter);
        binding.recyclerMenu.setEmptyView(binding.empty);
    }

    @Override
    public void onDataLoaded(Store store) {
        adapter.addItems(store.getMenuList());
    }
}
