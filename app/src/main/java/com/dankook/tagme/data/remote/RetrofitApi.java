package com.dankook.tagme.data.remote;


import com.dankook.tagme.model.LoginVO;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.model.UserVO;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {
    @POST("user/login")
    Call<LoginVO> login(@Body LoginRequest loginRequest);

    @POST("user/join")
    Call<Void> join(@Body UserVO userVO);

    @FormUrlEncoded
    @POST("user/duplication")
    Call<ResponseBody> duplication(@Field("usr_id") String usrId);

    // 가게 목록 조회
    @POST("store/selectStoreList")
    Observable<List<Store>> getStores();

    // 가게 상세정보 조회
    @POST("store/selectStore")
    Observable<Store> getStore(@Body StoreDetailRequest request);

}
