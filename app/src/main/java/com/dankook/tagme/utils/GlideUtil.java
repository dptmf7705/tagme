package com.dankook.tagme.utils;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.dankook.tagme.R;

public class GlideUtil {

    private static final String TAG = "GlideUtil";

    @BindingAdapter({"image"})
    public static void loadImage(ImageView imageView, String url){

        if (url == null) {
            Log.d(TAG, "loadImage() fail with url is NULL");
            return;
        }

        GlideApp.with(imageView).load(url).into(imageView);
    }

    @BindingAdapter({"image"})
    public static void loadImageResource(ImageView imageView, int resourceId){

        GlideApp.with(imageView).load(resourceId).into(imageView);
    }

    @BindingAdapter({"android:src"})
    public static void loadImageDrawable(ImageView imageView, Drawable drawable){

        GlideApp.with(imageView).load(drawable).into(imageView);
    }

    @BindingAdapter({"imageCenterCrop"})
    public static void loadImageCenterCrop(ImageView imageView, String url){

        if (url == null) return;

        GlideApp.with(imageView).load(url).centerCrop().into(imageView);
    }

    @BindingAdapter({"imageCircle"})
    public static void loadCircleImage(ImageView imageView, String url) {

        if (url == null) return;

        GlideApp.with(imageView).load(url).circleCrop().into(imageView);
    }

}
