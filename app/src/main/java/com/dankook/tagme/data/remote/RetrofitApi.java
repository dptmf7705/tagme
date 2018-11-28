package com.dankook.tagme.data.remote;


import com.dankook.tagme.model.Category;
import com.dankook.tagme.model.LoginVO;
import com.dankook.tagme.model.Store;
import com.dankook.tagme.model.UserVO;
import com.google.gson.annotations.JsonAdapter;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {

    // 로그인
    @POST("user/login")
    Call<LoginVO> login(@Body LoginRequest loginRequest);

    // 회원 가입
    @POST("user/join")
    Call<Void> join(@Body UserVO userVO);

    // ID 중복 조회
    @POST("user/duplication")
    Call<ResponseBody> duplication(@Body HashMap<String, String> userId);

    // 카테고리 목록 조회
    @GET("store/selectCategoryList")
    Observable<LoadDataListResponse<Category>> getCategoryList();

    // 가게 목록 조회
    @POST("store/selectStoreList")
    Observable<LoadDataListResponse<Store>> getStoreList(@Body StoreListRequest request);

    // 가게 상세정보 조회
    @POST("store/selectStore")
    Observable<LoadDataResponse<Store>> getStore(@Body StoreDetailRequest request);

    // 가게 주소 조회 - 좌표 찍기 위해
    @POST("store/selectStoreAddr")
    Call<LoadDataListResponse<Store>> getStoreAddr(@Body StoreAddrRequest request);

    // 주문하기
    @POST("order/insertOrder")
    Observable<LoadDataResponse<Integer>> insertOrder(@Body InsertOrderRequest request);

    // 전체 주문 조회
    @POST("order/selectOrderAll")
    Observable<LoadDataResponse<Integer>> selectOrderAll(@Body InsertOrderRequest request);

    // 주문 카운트
    @POST("order/countOrderList")
    Observable<LoadDataResponse<Integer>> countOrderList(@Body InsertOrderRequest request);

    // 테이블별 주문 조회
    @POST("order/selectOrderList")
    Observable<LoadDataResponse<Integer>> selectOrderList(@Body InsertOrderRequest request);

}
