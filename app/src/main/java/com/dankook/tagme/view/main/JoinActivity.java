package com.dankook.tagme.view.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dankook.tagme.R;
import com.dankook.tagme.databinding.ActivityJoinBinding;
import com.dankook.tagme.view.BaseActivity;
import com.dankook.tagme.vo.UserVO;

public class JoinActivity extends BaseActivity<ActivityJoinBinding> {

    private boolean idDuplication = false;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_join;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView(){

        binding.btnJoinFinish.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                UserVO userVO = new UserVO();

                //TODO. 아이디 중복 체크 & 비밀번호 체크 확인

                userVO.setUsr_id(binding.etxtJoinId.getText().toString());
                userVO.setUsr_password(binding.etxtJoinPassword.getText().toString());
                userVO.setUsr_name(binding.etxtName.getText().toString());
                userVO.setUsr_phone(binding.etxtJoinPhone.getText().toString());
                userVO.setUsr_addr(binding.etxtJoinAddr.getText().toString());
                //TODO. 서버로 userVO보내기
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnDupCheck.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                //TODO. 통신하고 idDuplication true 변환
            }
        });
    }
}
