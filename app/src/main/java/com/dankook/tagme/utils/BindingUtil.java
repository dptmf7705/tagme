package com.dankook.tagme.utils;

import android.databinding.BindingAdapter;
import android.databinding.BindingConversion;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DecimalFormat;

public class BindingUtil {

    @BindingAdapter({"resourceId"})
    public static void resourceId(ImageButton imageButton, int resourceId){

        imageButton.setImageResource(resourceId);
    }

    @BindingConversion
    public static int setVisibility(boolean isVisible){

        return isVisible ? View.VISIBLE : View.GONE;
    }

    @BindingAdapter({"refreshing"})
    public static void setRefreshing(SwipeRefreshLayout refreshLayout, boolean refreshing){
        refreshLayout.setRefreshing(refreshing);
    }

    @BindingAdapter({"price"})
    public static void convertTextToDisplayPrice(TextView textView, String inputText) {
        DecimalFormat priceFormat = new DecimalFormat("###,###");
        textView.setText(priceFormat.format(Long.parseLong(inputText)));
    }

    @BindingAdapter({"price"})
    public static void convertTextToDisplayPrice(TextView textView, int inputText) {
        DecimalFormat priceFormat = new DecimalFormat("###,###");
        textView.setText(priceFormat.format(inputText));
    }

}
