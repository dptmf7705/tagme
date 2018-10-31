package com.dankook.tagme.view.launch;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dankook.tagme.R;
import com.dankook.tagme.utils.DynamicLinkUtil;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.databinding.ActivitySplashBinding;
import com.dankook.tagme.view.main.MainActivity;
import com.dankook.tagme.view.store.storeDetail.StoreDetailActivity;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private final String TAG = "DynamicLink";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleDeepLink();
    }

    private void handleDeepLink() {

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {

                    if(pendingDynamicLinkData == null){

                        new Handler().postDelayed(() -> {
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                            finish();
                        }, 2000); // 2초 후 액티비티 전환
                    } else {

                        final Uri deepLink = pendingDynamicLinkData.getLink();
                        Log.d(TAG, "deepLink: " + deepLink);

                        String segment = deepLink.getLastPathSegment();

                        switch (segment) {
                            case DynamicLinkUtil.SEGMENT_STORE:
                                String storeName = deepLink.getQueryParameter(DynamicLinkUtil.STORE_KEY);
                                String tableNumber = deepLink.getQueryParameter(DynamicLinkUtil.TABLE_NUMBER);

                                Intent intent = new Intent(getApplicationContext(), StoreDetailActivity.class);
                                intent.putExtra(StoreDetailActivity.EXTRA_IS_DYNAMIC_LINK, true);
                                intent.putExtra(StoreDetailActivity.EXTRA_STORE_KEY, storeName);
                                intent.putExtra(StoreDetailActivity.EXTRA_TABLE_NUMBER, tableNumber);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                        Intent.FLAG_ACTIVITY_SINGLE_TOP |
                                        Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                                finish();
                                break;
                        }
                    }
                })
                .addOnFailureListener(this, error -> Log.d(TAG, "get Dynamic Link Fail"));
    }

}
