package com.dankook.tagme.data.remote;


import com.dankook.tagme.model.LoginVO;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.model.UserVO;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    // 가게 목록 조회
    @POST("store/selectStoreList")
    Observable<List<Store>> getStores();

    // 가게 상세정보 조회
    @POST("store/selectStore")
    Observable<Store> getStore(@Body StoreDetailRequest request);

}
