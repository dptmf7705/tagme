package com.dankook.tagme.view.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.ActivityLoginBinding;
import com.dankook.tagme.view.BaseActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> implements MainContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView(){
        binding.btnFind.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //아이디 or 비밀번호 찾는 액티비 호출
            }
        });
        binding.btnJoin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //가입 액티비티 호출
            }
        });
        binding.btnLogin.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //로그인 확인후 액티비티 전환
                String id = binding.etxtId.getText().toString();
                String password = binding.etxtPassword.getText().toString();
            }
        });
    }


}
