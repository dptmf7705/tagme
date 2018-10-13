package com.dankook.tagme.view.main;

import com.dankook.tagme.view.BaseAdapterContract;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;

    public MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setAdapterView(BaseAdapterContract.View view) {

    }

    @Override
    public void setAdapterModel(BaseAdapterContract.Model model) {

    }

    @Override
    public void loadItems() {

    }

    @Override
    public void onViewCreated() {

    }

    @Override
    public void detachView() {

    }
}
