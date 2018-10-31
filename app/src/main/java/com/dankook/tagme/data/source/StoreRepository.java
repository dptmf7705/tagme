package com.dankook.tagme.data.source;

import com.dankook.tagme.data.remote.RetrofitApi;
import com.dankook.tagme.data.remote.RetrofitClient;
import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.model.Store;

import java.util.List;

import io.reactivex.Observable;

public class StoreRepository implements StoreDataSource {

    private static StoreRepository storeRepository;

    private StoreRemoteDataSource remoteDataSource;

    private StoreRepository(){
        remoteDataSource = StoreRemoteDataSource.getInstance();
    }

    public static StoreRepository getInstance(){
        if(storeRepository == null){
            storeRepository = new StoreRepository();
        }
        return storeRepository;
    }

    @Override
    public Observable<List<Store>> getStores() {
        return remoteDataSource.getStores();
    }

    @Override
    public Observable<Store> getStore(StoreDetailRequest request) {
        return remoteDataSource.getStore(request);
    }
}
