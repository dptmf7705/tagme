package com.dankook.tagme.view.store.storeList;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;

import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.mapper.RequestMapper;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.adapter.BaseAdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreListPresenter implements StoreListContract.Presenter {

    public final ObservableBoolean isLoading = new ObservableBoolean(true);

    private StoreListContract.View view;
    private BaseAdapterContract.View adapterView;
    private BaseAdapterContract.Model<Store> adapterModel;
    private final StoreRepository repository;

    private final int categoryKey;

    public StoreListPresenter(StoreListContract.View view, StoreRepository repository, int categoryKey){
        this.view = view;
        this.repository = repository;
        this.categoryKey = categoryKey;
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

        isLoading.set(true);

        repository.getStores(categoryKey)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storeList -> {
                    isLoading.set(false);
                    adapterModel.addItems(storeList);
                }, error -> {
                    isLoading.set(false);
                });
    }
}
