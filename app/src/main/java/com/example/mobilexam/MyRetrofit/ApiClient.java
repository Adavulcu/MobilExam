package com.example.mobilexam.MyRetrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BaseUrl = "https://mobilechallange.veripark.com/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if (retrofit==null){

            //LOG
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            //
            retrofit =new  Retrofit.Builder().baseUrl(BaseUrl).addConverterFactory(GsonConverterFactory.create()
            ).client(client).build();
        }

        return retrofit;
    }
}
