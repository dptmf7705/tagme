package com.dankook.tagme.data.source;

import com.dankook.tagme.model.Category;
import com.dankook.tagme.model.Store;

import java.util.ArrayList;
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

    private List<Category> cacheCategoryList = new ArrayList<>();
    private Boolean isCacheCategoryDirty = true;

    public void setCacheCategoryDirty(Boolean cacheCategoryDirty) {
        isCacheCategoryDirty = cacheCategoryDirty;
    }

    @Override
    public Observable<List<Category>> getCategories() {

        // 캐시 데이터 변경사항이 있을 경우 서버에 다시 요청
        if(isCacheCategoryDirty || cacheCategoryList.size() == 0) {
            // 카테고리 목록 요청 후 캐시에 저장
            return remoteDataSource.getCategories()
                    .doOnNext(list -> {
                        cacheCategoryList.addAll(list);
                        isCacheCategoryDirty = false;
                    });

        } else {
            // 캐시에 있는 경우 캐시 리턴
            return Observable.just(cacheCategoryList);
        }
    }

    @Override
    public Observable<List<Store>> getStores(int categoryKey) {

        return remoteDataSource.getStores(categoryKey);
    }

    @Override
    public Observable<Store> getStore(int storeKey) {

        return remoteDataSource.getStore(storeKey);
    }
}
