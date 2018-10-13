package com.dankook.tagme.data.remote;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("/user/login")
    Call<User> login(@Query("user_id") String userId,
                     @Query("user_password") String userPassword);


}
