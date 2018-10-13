package com.dankook.tagme.view.main;

import com.dankook.tagme.view.BaseAdapterContract;
import com.dankook.tagme.view.BasePresenter;
import com.dankook.tagme.view.BaseView;

public interface MainContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        void setAdapterView(BaseAdapterContract.View view);

        void setAdapterModel(BaseAdapterContract.Model model);

        void loadItems();
    }

}
