package com.dankook.tagme.view.launch;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.dankook.tagme.R;
import com.dankook.tagme.data.remote.DuplicationRequest;
import com.dankook.tagme.data.remote.RetrofitApi;
import com.dankook.tagme.data.remote.RetrofitClient;
import com.dankook.tagme.databinding.ActivityJoinBinding;
import com.dankook.tagme.model.UserVO;
import com.dankook.tagme.view.BaseActivity;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends BaseActivity<ActivityJoinBinding> {
    public boolean idDuplication = true;
    final Context context = this;
    final String DUPLICATED_ID_EXIST = "true";

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
                if(idDuplication == false &&
                        binding.etxtJoinPassword.getText().toString().equals(binding.etxtJoinPasswordCheck.getText().toString())){

                    userVO.setUsrId(binding.etxtJoinId.getText().toString());
                    userVO.setUsrPassword(binding.etxtJoinPassword.getText().toString());
                    userVO.setUsrName(binding.etxtJoinName.getText().toString());
                    userVO.setUsrPhone(binding.etxtJoinPhone.getText().toString());
                    userVO.setUsrAddr(binding.etxtJoinAddr.getText().toString());

                    RetrofitApi request = RetrofitClient.getClient().create(RetrofitApi.class);
                    Call<Void> call = request.join(userVO
//                            binding.etxtJoinId.getText().toString(),
//                            binding.etxtJoinPassword.getText().toString(),
//                            binding.etxtName.getText().toString(),
//                            binding.etxtJoinPhone.getText().toString(),
//                            binding.etxtJoinAddr.getText().toString()
                    );

                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("Join : 서버와 연결 실패",  t.getCause() +" " + t.getMessage());
                        }
                    });
                }else if(idDuplication == true){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setMessage("아이디 중복체크를 하세요");
                    alertBuilder.setPositiveButton("확인", null);
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                }else if(!binding.etxtJoinPassword.getText().toString().equals(binding.etxtJoinPasswordCheck.getText().toString())){
                    AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                    alertBuilder.setMessage("비밀번호가 다릅니다.");
                    alertBuilder.setPositiveButton("확인", null);
                    AlertDialog alert = alertBuilder.create();
                    alert.show();
                }
            }
        });

        binding.btnDupCheck.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("리스너진입", "리스너");
                RetrofitApi request = RetrofitClient.getClient().create(RetrofitApi.class);
//                DuplicationRequest duplicationRequest = new DuplicationRequest(binding.etxtJoinId.getText().toString());

                Call<ResponseBody> call = request.duplication(binding.etxtJoinId.getText().toString());

                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        String result = null;
                        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                        try {
                            String Object;
                            Log.d("파싱에러", response.body()+"");
                            Object = response.body().string();
                            JsonParser parser = new JsonParser();
                            JsonObject jsonObject = (JsonObject)parser.parse(Object);
                            result = jsonObject.get("result").getAsString();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        if(result.equals(DUPLICATED_ID_EXIST)){
                            Log.d("dupsuccess", result.toString());
                            alertBuilder.setMessage("중복된 아이디가 존재합니다.");
                        }else if(!result.equals(DUPLICATED_ID_EXIST)){
                            Log.d("dupfail", result.toString());
                            alertBuilder.setMessage("중복된 아이디가 없습니다.");
                            idDuplication = false;
                        }
                        alertBuilder.setPositiveButton("확인", null);
                        AlertDialog alert = alertBuilder.create();
                        alert.show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //TODO 네트워크 연결 실패시 다이얼로그 호출
                        Log.e("Duplication : 서버와 연결 실패",  t.getCause() +" " + t.getMessage());
                    }
                });
            }
        });
    }
}
