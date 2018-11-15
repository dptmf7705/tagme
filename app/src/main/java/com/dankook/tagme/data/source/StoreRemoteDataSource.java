package com.dankook.tagme.data.source;

import com.dankook.tagme.data.remote.RetrofitApi;
import com.dankook.tagme.data.remote.RetrofitClient;
import com.dankook.tagme.model.Category;
import com.dankook.tagme.model.Store;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class StoreRemoteDataSource implements StoreDataSource {

    private static StoreRemoteDataSource storeRemoteDataSource;

    private StoreRemoteDataSource(){}

    public static StoreRemoteDataSource getInstance(){
        if(storeRemoteDataSource == null){
            storeRemoteDataSource = new StoreRemoteDataSource();
        }
        return storeRemoteDataSource;
    }

    @Override
    public Observable<List<Category>> getCategories() {
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getCategories()
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<List<Store>> getStores(int categoryKey) {
        HashMap<String, Integer> request = new HashMap<>();
        request.put("category_key", categoryKey);

        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getStores(request)
                .subscribeOn(Schedulers.newThread());
    }

    @Override
    public Observable<Store> getStore(int storeKey) {
        HashMap<String, Integer> request = new HashMap<>();
        request.put("store_key", storeKey);

        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getStore(request)
                .subscribeOn(Schedulers.newThread());
    }
}
