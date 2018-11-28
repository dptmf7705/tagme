package com.dankook.tagme.view.store.storeDetail;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;

import com.dankook.tagme.data.remote.InsertOrderRequest;
import com.dankook.tagme.data.source.store.StoreRepository;
import com.dankook.tagme.mapper.RequestMapper;
import com.dankook.tagme.model.Image;
import com.dankook.tagme.model.Menu;
import com.dankook.tagme.view.adapter.BaseAdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreDetailPresenter implements StoreDetailContract.Presenter{

    public final ObservableBoolean isDynamicLink = new ObservableBoolean(false);

    private StoreDetailContract.View view;
    private BaseAdapterContract.View adapterView;
    private BaseAdapterContract.Model<Image> adapterModel;
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
    public void setAdapterModel(BaseAdapterContract.Model<Image> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadItems() {

        repository.getStore(RequestMapper.storeDetailMapping(storeKey))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(store -> {
                    view.onStoreDetailDataLoaded(store);
                    adapterModel.addItems(store.getImageList());
                }, error -> {});
    }

    @SuppressLint("CheckResult")
    @Override
    public void submitOrder(InsertOrderRequest request) {

        repository.insertOrder(request)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(orderKey -> {
                    view.makeToast("주문이 완료되었습니다.");
                });
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
