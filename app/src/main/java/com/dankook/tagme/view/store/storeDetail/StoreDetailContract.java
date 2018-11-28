package com.dankook.tagme.view.store.storeDetail;

import com.dankook.tagme.data.remote.InsertOrderRequest;
import com.dankook.tagme.model.Image;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.model.Menu;
import com.dankook.tagme.view.adapter.BaseAdapterContract;
import com.dankook.tagme.view.BasePresenter;
import com.dankook.tagme.view.BaseView;

public interface StoreDetailContract {

    interface View extends BaseView<Presenter>{

        void onStoreDetailDataLoaded(Store store);

        void makeToast(String text);
    }

    interface Presenter extends BasePresenter{

        void setAdapterView(BaseAdapterContract.View adapterView);

        void setAdapterModel(BaseAdapterContract.Model<Image> adapterModel);

        void loadItems();

        void submitOrder(InsertOrderRequest request);
    }
}
