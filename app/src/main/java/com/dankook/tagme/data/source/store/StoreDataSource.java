package com.dankook.tagme.data.source.store;

import com.dankook.tagme.data.remote.InsertOrderRequest;
import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.remote.StoreListRequest;
import com.dankook.tagme.model.Category;
import com.dankook.tagme.model.Store;

import java.util.List;

import io.reactivex.Observable;

public interface StoreDataSource {

    Observable<List<Category>> getCategoryList();

    Observable<List<Store>> getStoreList(StoreListRequest request);

    Observable<Store> getStore(StoreDetailRequest request);

    Observable<Integer> insertOrder(InsertOrderRequest request);
}
