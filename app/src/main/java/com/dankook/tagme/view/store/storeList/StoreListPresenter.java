package com.dankook.tagme.view.store.storeList;

import android.annotation.SuppressLint;

import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.BaseAdapterContract;
import com.dankook.tagme.view.listener.OnItemClickListener;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreListPresenter implements StoreListContract.Presenter {

    StoreListContract.View view;
    BaseAdapterContract.View adapterView;
    BaseAdapterContract.Model<Store> adapterModel;
    StoreRepository repository;

    private String storeType;

    public StoreListPresenter(StoreListContract.View view, StoreRepository repository, String storeType){
        this.view = view;
        this.repository = repository;
        this.storeType = storeType;
    }

    @Override
    public void setAdapterView(BaseAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position ->
                view.startStoreDetailActivity(adapterModel.getItem(position).getStoreKey()));
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.Model<Store> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onViewCreated() {
        loadItems();
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadItems() {
        repository.getStores()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storeList -> adapterModel.addItems(storeList), error -> {});
    }
}
