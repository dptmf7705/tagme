package com.dankook.tagme.view;

import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dankook.tagme.utils.SnackBarUtil;

public abstract class BaseActivity<B extends ViewDataBinding> extends AppCompatActivity {

    protected final String TAG = getClass().getSimpleName();

    protected B binding;

    protected abstract int getLayoutId();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 화면 세로 고정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR); // 상태바 글씨 검정
        }
        binding = DataBindingUtil.setContentView(this, getLayoutId());
    }

    protected void showSnackBar(String message){
        SnackBarUtil.showSnackbar(binding.getRoot(), message);
    }
}