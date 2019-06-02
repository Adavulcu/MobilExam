package com.example.mobilexam.MyRetrofit;

import com.example.mobilexam.Pojos.HStartReq;
import com.example.mobilexam.Pojos.HStartResp;
import com.example.mobilexam.Pojos.HStokDetailReq;
import com.example.mobilexam.Pojos.HStokDetailResp;
import com.example.mobilexam.Pojos.HStokListReq;
import com.example.mobilexam.Pojos.HStokListResp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface IMyClient {

    @POST("handshake/start")
    Call<HStartResp> postHandShake(@Body HStartReq data);


    @POST("stocks/detail")
    Call<HStokDetailResp> postStoksDetail(@Header("X-VP-Authorization") String autorizationKey, @Body() HStokDetailReq stokDetailReq);

    @POST("stocks/list")
    Call<HStokListResp> postStoksList(@Header("X-VP-Authorization")String autorizationKey, @Body() HStokListReq stokListReq);
}
