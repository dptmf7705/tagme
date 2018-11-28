package com.dankook.tagme.view.launch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.dankook.tagme.R;
import com.dankook.tagme.data.source.user.UserRepository;
import com.dankook.tagme.model.UserVO;
import com.dankook.tagme.utils.App;
import com.dankook.tagme.utils.Constant;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.databinding.ActivitySplashBinding;
import com.dankook.tagme.view.main.MainActivity;
import com.dankook.tagme.view.store.storeDetail.StoreDetailActivity;
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks;

public class SplashActivity extends BaseActivity<ActivitySplashBinding> {

    private final String TAG = "DynamicLink";

    private boolean isUserLogin;
    private SharedPreferences prefLogin;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 화면 크기 구해서 어플리케이션 전역변수로 저장해놓기
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        ((App) getApplicationContext()).setWindowWidth(size.x);
        ((App) getApplicationContext()).setWindowHeight(size.y);

        // 로그인  상태 확인
        prefLogin = getSharedPreferences(Constant.PREFERENCE_USER, Context.MODE_PRIVATE);
        isUserLogin = prefLogin.getBoolean(Constant.PREFERENCE_USER_LOGIN, false);

        handleDeepLink();
    }

    private void handleDeepLink() {

        FirebaseDynamicLinks.getInstance()
                .getDynamicLink(getIntent())
                .addOnSuccessListener(this, pendingDynamicLinkData -> {

                    if(pendingDynamicLinkData == null){
                        new Handler().postDelayed(() -> {

                            Intent intent;

                            if(isUserLogin) {
                                // 로그인 사용자 정보 가져오기
                                int userKey = prefLogin.getInt(Constant.PREFERENCE_USER_KEY, 0);
                                String userId = prefLogin.getString(Constant.PREFERENCE_USER_ID, "");
                                String userName = prefLogin.getString(Constant.PREFERENCE_USER_NAME, "");
                                String userAddress = prefLogin.getString(Constant.PREFERENCE_USER_ADDRESS, "");
                                String userPhone = prefLogin.getString(Constant.PREFERENCE_USER_PHONE, "");
                                UserVO user = new UserVO(userKey, userId, userName, userAddress, userPhone);
                                UserRepository.getInstance().setLoginUser(user);

                                intent = new Intent(getApplicationContext(), MainActivity.class);

                            } else {
                                // 로그인 시키기
                                intent = new Intent(getApplicationContext(), LoginActivity.class);
                            }

                            startActivity(intent);
                            overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
                            finish();
                        }, 2000); // 2초 후 액티비티 전환
                    } else {

                        final Uri deepLink = pendingDynamicLinkData.getLink();
                        Log.d(TAG, "deepLink: " + deepLink);

                        String segment = deepLink.getLastPathSegment();

                        switch (segment) {
                            case Constant.DYNAMIC_SEGMENT_STORE:
                                int storeKey = 1;
                                int tableNumber = 1;
                                try{
                                    storeKey = Integer.parseInt(deepLink.getQueryParameter(Constant.DYNAMIC_STORE_KEY));
                                    tableNumber = Integer.parseInt(deepLink.getQueryParameter(Constant.DYNAMIC_TABLE_NUMBER));
                                } catch (Exception e){}

                                Intent intent = new Intent(getApplicationContext(), StoreDetailActivity.class);
                                intent.putExtra(StoreDetailActivity.EXTRA_IS_DYNAMIC_LINK, true);
                                intent.putExtra(StoreDetailActivity.EXTRA_STORE_KEY, storeKey);
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
