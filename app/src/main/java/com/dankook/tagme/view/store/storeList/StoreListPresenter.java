package com.dankook.tagme.view.store.storeList;

import android.annotation.SuppressLint;
import android.databinding.ObservableBoolean;

import com.dankook.tagme.data.source.store.StoreRepository;
import com.dankook.tagme.mapper.RequestMapper;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.adapter.BaseAdapterContract;

import io.reactivex.android.schedulers.AndroidSchedulers;

public class StoreListPresenter implements StoreListContract.Presenter {

    public final static int PAGE_UNIT = 20;
    public final ObservableBoolean isLoading = new ObservableBoolean(false);

    private StoreListContract.View view;
    private BaseAdapterContract.View adapterView;
    private BaseAdapterContract.Model<Store> adapterModel;
    private final StoreRepository repository;

    private final int categoryKey;

    private int currentPage = 0;
    private boolean isPageEnd = false;

    public StoreListPresenter(StoreListContract.View view, StoreRepository repository, int categoryKey){
        this.view = view;
        this.repository = repository;
        this.categoryKey = categoryKey;
    }

    @Override
    public void setAdapterView(BaseAdapterContract.View adapterView) {
        this.adapterView = adapterView;
        this.adapterView.setOnItemClickListener(position ->
                view.startStoreDetailActivity(adapterModel.getItem(position).getStoreKey()));
    }

    @Override
    public void setAdapterModel(BaseAdapterContract.Model<Store> adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onViewCreated() {
        loadItems(true);
    }

    @Override
    public void onViewDetached() {
        view = null;
    }

    @Override
    public void loadItems(boolean isRefresh) {

        // 새로고침 한 경우 end 플래그 OFF, 현재페이지 초기화, 어댑터 초기화
        if (isRefresh) {
            isPageEnd = false;
            currentPage = 0;
            adapterModel.clearItems();
        }

        // 마지막 페이지가 아니고, 데이터 로딩중이 아닌 경우 getStoreList() 호출
        if(!isPageEnd && !isLoading.get()){
            getStoreList();
        }
    }

    @SuppressLint("CheckResult")
    private void getStoreList(){

        isLoading.set(true);

        repository.getStoreList(RequestMapper.storeListMapping(categoryKey, currentPage++, PAGE_UNIT))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storeList -> {
                    // 프로그래스바 종료
                    isLoading.set(false);
                    // 어댑터에 리스트 추가
                    adapterModel.addItems(storeList);
                    // 요청결과 개수가 page unit 보다 작으면 end flag ON
                    if(storeList.size() < PAGE_UNIT){
                        isPageEnd = true;
                    }
                }, error -> {
                    isPageEnd = true;
                    isLoading.set(false);
                });
    }
}
