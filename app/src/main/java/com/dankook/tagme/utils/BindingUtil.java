package com.dankook.tagme.utils;

import android.databinding.BindingConversion;
import android.view.View;

public class BindingUtil {

    @BindingConversion
    public static int setVisibility(boolean isVisible){

        return isVisible ? View.VISIBLE : View.GONE;
    }

}
