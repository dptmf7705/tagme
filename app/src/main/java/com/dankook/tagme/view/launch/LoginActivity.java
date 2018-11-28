package com.dankook.tagme.view.launch;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dankook.tagme.R;
import com.dankook.tagme.data.remote.LoginRequest;
import com.dankook.tagme.data.remote.RetrofitApi;
import com.dankook.tagme.data.remote.RetrofitClient;
import com.dankook.tagme.data.source.user.UserRepository;
import com.dankook.tagme.databinding.ActivityLoginBinding;
import com.dankook.tagme.model.UserVO;
import com.dankook.tagme.utils.Constant;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.view.main.MainActivity;
import com.dankook.tagme.model.LoginVO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity<ActivityLoginBinding>{

    public static final boolean LOGIN_SUCCESS = true;
    public static final boolean LOGIN_FAIL = false;
    public static final String FAIL_ID = "id";
    public static final String FAIL_PASSWORD = "password";
    final  Context context = this;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView(){
        binding.btnFind.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO.아이디 or 비밀번호 찾는 액티비 호출
                //TODO 액티비티 말고 다이얼로그 띄우는 것도 다른 방법
            }
        });
        binding.btnJoin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        binding.btnLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO. 로그인 확인후 액티비티 전환
                String id = binding.etxtId.getText().toString();
                String password = binding.etxtPassword.getText().toString();
                LoginRequest loginRequest = new LoginRequest(id, password);

                RetrofitApi request = RetrofitClient.getClient().create(RetrofitApi.class);
                Call<LoginVO> call = request.login(loginRequest);

                call.enqueue(new Callback<LoginVO>() {
                    @Override
                    public void onResponse(Call<LoginVO> call, Response<LoginVO> response) {
                        LoginVO loginUser = response.body();
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

                        if(loginUser != null){
                            if(loginUser.getResult() == LOGIN_SUCCESS){
                                //로그인 유저 정보 유지하기
                                UserVO user = loginUser.getUVO();
                                SharedPreferences prefLogin = getSharedPreferences(Constant.PREFERENCE_USER, Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = prefLogin.edit();
                                editor.putBoolean(Constant.PREFERENCE_USER_LOGIN, true);
                                editor.putInt(Constant.PREFERENCE_USER_KEY, user.getUsrkey());
                                editor.putString(Constant.PREFERENCE_USER_ID, user.getUsrId());
                                editor.putString(Constant.PREFERENCE_USER_NAME, user.getUsrName());
                                editor.putString(Constant.PREFERENCE_USER_ADDRESS, user.getUsrAddr());
                                editor.putString(Constant.PREFERENCE_USER_PHONE, user.getUsrPhone());
                                editor.apply();

                                UserRepository.getInstance().setLoginUser(loginUser.getUVO());

                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else if(loginUser.getResult() == LOGIN_FAIL && loginUser.getFail().toString().equals(FAIL_ID)){
                                alertBuilder.setMessage("로그인 실패 : ID가 존재하지 않습니다.");
                                alertBuilder.setPositiveButton("확인", null);
                                AlertDialog alert = alertBuilder.create();
                                alert.show();
                            }else if(loginUser.getResult() == LOGIN_FAIL && loginUser.getFail().toString().equals(FAIL_PASSWORD)){
                                alertBuilder.setMessage("로그인 실패 : PASSWORD가 일치하지 않습니다.");
                                alertBuilder.setPositiveButton("확인", null);
                                AlertDialog alert = alertBuilder.create();
                                alert.show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginVO> call, Throwable t) {
                        Log.e("Login : 서버와 연결 실패",  t.getCause() +" " + t.getMessage());
                    }
                });
            }
        });
    }
}
