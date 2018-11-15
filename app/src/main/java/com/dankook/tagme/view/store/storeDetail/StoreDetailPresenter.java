package com.dankook.tagme.view.store.storeDetail;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;

import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.mapper.RequestMapper;
import com.dankook.tagme.model.StoreMenu;
import com.dankook.tagme.view.adapter.BaseAdapterContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreDetailPresenter implements StoreDetailContract.Presenter{

    public final ObservableBoolean isDynamicLink = new ObservableBoolean(false);

    private StoreDetailContract.View view;
    private BaseAdapterContract.View adapterView;
    private BaseAdapterContract.Model<StoreMenu> adapterModel;
    private StoreRepository repository;

    private final int storeKey;

    public StoreDetailPresenter(StoreDetailContract.View view, StoreRepository repository, int storeKey, boolean isDynamicLink){
        this.view = view;
        this.repository = repository;
        this.storeKey = storeKey;
        this.isDynamicLink.set(isDynamicLink);
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void setAdapterView(BaseAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.Model<StoreMenu> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadItems() {

        repository.getStore(storeKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(store -> {
                    view.onStoreDetailDataLoaded(store);
                    adapterModel.addItems(store.getStoreMenuList());
                }, error -> {});
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
