package com.dankook.tagme.data.source;

import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.model.Store;

import java.util.List;

import io.reactivex.Observable;

public interface StoreDataSource {
    Observable<List<Store>> getStores();
    Observable<Store> getStore(StoreDetailRequest request);
}
