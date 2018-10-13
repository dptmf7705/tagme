package com.dankook.tagme.binding;

import android.databinding.BindingAdapter;
import android.widget.ImageButton;

public class BindAdapter {

    @BindingAdapter({"resourceId"})
    public static void resourceId(ImageButton imageButton, int resourceId){

        imageButton.setImageResource(resourceId);
    }

}
