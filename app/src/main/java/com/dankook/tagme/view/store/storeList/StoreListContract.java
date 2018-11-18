package com.dankook.tagme.view.store.storeList;

import com.dankook.tagme.model.Store;
import com.dankook.tagme.view.adapter.BaseAdapterContract;
import com.dankook.tagme.view.BasePresenter;
import com.dankook.tagme.view.BaseView;

public interface StoreListContract {

    interface View extends BaseView<Presenter> {

        void startStoreDetailActivity(int storeKey);
    }

    interface Presenter extends BasePresenter {

        void setAdapterView(BaseAdapterContract.View adapterView);

        void setAdapterModel(BaseAdapterContract.Model<Store> adapterModel);

        void loadItems(boolean isRefresh);
    }
}
