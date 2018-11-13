package com.dankook.tagme.data.source;

import com.dankook.tagme.data.remote.StoreDetailRequest;
import com.dankook.tagme.data.remote.StoreListRequest;
import com.dankook.tagme.model.Category;
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
    public Observable<List<Category>> getCategories() {

        return remoteDataSource.getCategories();
    }

    @Override
    public Observable<List<Store>> getStores(StoreListRequest request) {

        return remoteDataSource.getStores(request);
    }

    @Override
    public Observable<Store> getStore(StoreDetailRequest request) {

        return remoteDataSource.getStore(request);
    }
}
