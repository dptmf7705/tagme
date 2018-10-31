package com.dankook.tagme.view.store.storeList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.dankook.tagme.R;
import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.databinding.FragmentStoreListBinding;
import com.dankook.tagme.view.BaseFragment;
import com.dankook.tagme.view.store.storeDetail.StoreDetailActivity;

public class StoreListFragment extends BaseFragment<FragmentStoreListBinding> implements StoreListContract.View {

    private static final String EXTRA_STORE_TYPE = "EXTRA_STORE_TYPE";

    private StoreListPresenter presenter;
    private StoreListAdapter adapter;

    private String storeType;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store_list;
    }

    public StoreListFragment(){}

    public static StoreListFragment newInstance(String storeType) {

        StoreListFragment fragment = new StoreListFragment();

        Bundle args = new Bundle();
        args.putString(EXTRA_STORE_TYPE, storeType);
        fragment.setArguments(args);

        return fragment;
    }

    private void getExtraStoreType(){

        Bundle args = getArguments();

        if(args != null){
            storeType = args.getString(EXTRA_STORE_TYPE);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getExtraStoreType();

        presenter = new StoreListPresenter(this, StoreRepository.getInstance(), storeType);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 가게 목록 어댑터 생성
        adapter = new StoreListAdapter(context);
        presenter.setAdapterView(adapter);
        presenter.setAdapterModel(adapter);

        initView();

        presenter.onViewCreated();
    }

    private void initView() {
        // 가게 목록 리사이클러뷰 생성
        binding.recyclerStoreList.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.recyclerStoreList.setAdapter(adapter);
        binding.recyclerStoreList.setNestedScrollingEnabled(false);
        binding.recyclerStoreList.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
    }

    @Override
    public void startStoreDetailActivity(String storeKey) {
        Intent intent = new Intent(context, StoreDetailActivity.class);
        intent.putExtra(StoreDetailActivity.EXTRA_IS_DYNAMIC_LINK, false);
        intent.putExtra(StoreDetailActivity.EXTRA_STORE_KEY, storeKey);
        startActivity(intent);
    }
}
