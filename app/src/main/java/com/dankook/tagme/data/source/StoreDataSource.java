package com.dankook.tagme.data.source;

import com.dankook.tagme.model.Category;
import com.dankook.tagme.model.Store;

import java.util.List;

import io.reactivex.Observable;

public interface StoreDataSource {

    Observable<List<Category>> getCategories();

    Observable<List<Store>> getStores(int categoryKey);

    Observable<Store> getStore(int storeKey);
}
