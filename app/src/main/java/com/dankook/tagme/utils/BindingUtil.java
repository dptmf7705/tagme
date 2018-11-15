package com.dankook.tagme.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

public class BindingUtil {

    @BindingConversion
    public static int setVisibility(boolean isVisible){

        return isVisible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing){
        refreshLayout.setRefreshing(refreshing);
    }
}
