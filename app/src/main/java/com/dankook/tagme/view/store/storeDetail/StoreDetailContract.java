package com.dankook.tagme.view.store.storeDetail;

import com.dankook.tagme.model.Store;
import com.dankook.tagme.model.StoreMenu;
import com.dankook.tagme.view.BaseAdapterContract;
import com.dankook.tagme.view.BasePresenter;
import com.dankook.tagme.view.BaseView;

public interface StoreDetailContract {

    interface View extends BaseView<Presenter>{

        void onStoreDetailDataLoaded(Store store);
    }

    interface Presenter extends BasePresenter{

        void setAdapterView(BaseAdapterContract.View adapterView);

        void setAdapterModel(BaseAdapterContract.Model<StoreMenu> adapterModel);

        void loadItems();
    }
}
