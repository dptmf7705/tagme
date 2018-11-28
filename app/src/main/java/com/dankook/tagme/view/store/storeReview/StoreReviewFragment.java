package com.dankook.tagme.view.store.storeReview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.FragmentStoreReviewBinding;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.BaseFragment;
import com.dankook.tagme.view.listener.OnDataLoadedListener;

public class StoreReviewFragment extends BaseFragment<FragmentStoreReviewBinding> implements OnDataLoadedListener{

    public static final String EXTRA_STORE_KEY = "EXTRA_STORE_KEY";

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_store_review;
    }

    public static StoreReviewFragment newInstance(){

        return new StoreReviewFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {

    }

    @Override
    public void onDataLoaded(Store store) {

    }
}
