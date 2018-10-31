package com.dankook.tagme.view.store.storeDetail;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;
import android.util.Log;

import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.source.StoreRepository;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.model.StoreMenu;
import com.dankook.tagme.view.BaseAdapterContract;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreDetailPresenter implements StoreDetailContract.Presenter{

    public final ObservableBoolean isDynamicLink = new ObservableBoolean(false);

    private StoreDetailContract.View view;
    private BaseAdapterContract.View adapterView;
    private BaseAdapterContract.Model<StoreMenu> adapterModel;
    private StoreRepository repository;

    private final String storeKey;

    public StoreDetailPresenter(StoreDetailContract.View view, StoreRepository repository, String storeKey){
        this.view = view;
        this.repository = repository;
        this.storeKey = storeKey;
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

        repository.getStore(new StoreDetailRequest(storeKey))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(store -> {
                    view.onStoreDetailDataLoaded(store);
                    List<StoreMenu> menuList = new ArrayList<>();
                    for(int i = 0 ; i < 6 ; i++) {
                        StoreMenu menu = new StoreMenu();
                        menu.setMenuName("메뉴" + (i+1));
                        menu.setMenuImageUrl(store.getMainImageUrl());
                        menuList.add(menu);
                    }
                    adapterModel.addItems(menuList);
                }, error -> {});
    }

    @Override
    public void onViewDetached() {
        this.view = null;
    }
}
