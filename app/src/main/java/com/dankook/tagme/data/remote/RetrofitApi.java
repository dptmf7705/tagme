package com.dankook.tagme.data.remote;

import com.dankook.tagme.vo.LoginVO;
import com.dankook.tagme.vo.UserVO;

import okhttp3.Request;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("user/login")
    Call<LoginVO> login(@Query("usr_id") String userId,
                        @Query("usr_password") String userPassword);

    @GET("user/join")
    Call<Void> join(@Body UserVO userVO);

    @POST("user/join")
    Call<Void> join(@Query("usr_id") String userId,
                    @Query("usr_password") String userPassword,
                    @Query("usr_name") String userName,
                    @Query("usr_phone") String userPhone,
                    @Query("usr_addr") String usrAddr);

    @GET("user/duplication")
    Call<ResponseBody> duplication(@Query("usr_id") String userId);

}
