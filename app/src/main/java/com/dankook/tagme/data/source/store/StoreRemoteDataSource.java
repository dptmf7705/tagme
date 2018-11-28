package com.dankook.tagme.data.source.store;

import com.dankook.tagme.data.remote.InsertOrderRequest;
import com.dankook.tagme.data.remote.LoadDataListResponse;
import com.dankook.tagme.data.remote.LoadDataResponse;
import com.dankook.tagme.data.remote.RetrofitApi;
import com.dankook.tagme.data.remote.RetrofitClient;
import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.remote.StoreListRequest;
import com.dankook.tagme.model.Category;
import com.dankook.tagme.model.Store;

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
    public Observable<List<Category>> getCategoryList() {
        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getCategoryList()
                .subscribeOn(Schedulers.newThread())
                .map(LoadDataListResponse::getContent);
    }

    @Override
    public Observable<List<Store>> getStoreList(StoreListRequest request) {

        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getStoreList(request)
                .subscribeOn(Schedulers.newThread())
                .map(LoadDataListResponse::getContent);
    }

    @Override
    public Observable<Store> getStore(StoreDetailRequest request) {

        return RetrofitClient.getClient().create(RetrofitApi.class)
                .getStore(request)
                .subscribeOn(Schedulers.newThread())
                .map(LoadDataResponse::getContent);
    }

    @Override
    public Observable<Integer> insertOrder(InsertOrderRequest request) {

        return RetrofitClient.getClient().create(RetrofitApi.class)
                .insertOrder(request)
                .subscribeOn(Schedulers.newThread())
                .map(LoadDataResponse::getContent);
    }
}
