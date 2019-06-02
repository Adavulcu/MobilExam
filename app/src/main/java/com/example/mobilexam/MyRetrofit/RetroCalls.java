package com.example.mobilexam.MyRetrofit;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mobilexam.Enums;
import com.example.mobilexam.MyAes;
import com.example.mobilexam.Pojos.HStartReq;
import com.example.mobilexam.Pojos.HStartResp;
import com.example.mobilexam.Pojos.HStokDetailReq;
import com.example.mobilexam.Pojos.HStokDetailResp;
import com.example.mobilexam.Pojos.HStokListReq;
import com.example.mobilexam.Pojos.HStokListResp;
import com.example.mobilexam.Pojos.Test;
import com.example.mobilexam.Pojos.TestResponse;
import com.example.mobilexam.R;
import com.example.mobilexam.StokListAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RetroCalls {

    public static void PostHandShake(HStartReq data)
    {
        IMyClient myClient=ApiClient.getRetrofitInstance().create(IMyClient.class);
        Call<HStartResp> postCall=myClient.postHandShake(data);
        postCall.enqueue(new Callback<HStartResp>() {
            @Override
            public void onResponse(Call<HStartResp> call, Response<HStartResp> response) {
                if (response.isSuccessful())
                {
                    Enums.Autorizations=response.body();

                    Log.i("RetroCalls","Yetki key :" +Enums.Autorizations.getAuthorization());
                }
            }

            @Override
            public void onFailure(Call<HStartResp> call, Throwable t) {

            }
        });
    }

    public static void PostStokDetail(HStokDetailReq data)
    {
        IMyClient myClient=ApiClient.getRetrofitInstance().create(IMyClient.class);
        Call<HStokDetailResp> postCall=myClient.postStoksDetail(Enums.Autorizations.getAuthorization(),data);
        postCall.enqueue(new Callback<HStokDetailResp>() {
            @Override
            public void onResponse(Call<HStokDetailResp> call, Response<HStokDetailResp> response) {
                if (response.isSuccessful())
                {
                    Enums.StokDetail=response.body();
                    Log.i("PostStokDetail","Succes");
                    Enums.IsStokDetailReady=true;
                }
            }

            @Override
            public void onFailure(Call<HStokDetailResp> call, Throwable t) {

            }
        });
    }

    public static void PostStokList(HStokListReq data)
    {
        MyAes aes=new MyAes();
        IMyClient myClient=ApiClient.getRetrofitInstance().create(IMyClient.class);
        Call<HStokListResp> postCall=myClient.postStoksList(Enums.Autorizations.getAuthorization(),data);
        postCall.enqueue(new Callback<HStokListResp>() {
            @Override
            public void onResponse(Call<HStokListResp> call, Response<HStokListResp> response) {

                if (response.isSuccessful())
                {

                    Log.i("RetroCalls ",response.body().toString());
                    Enums.Stocks=response.body().getStocks();
                /*
                    for (int i=0;i<Enums.Stocks.size();i++)
                    {
                        Enums.Stocks.get(i).setSymbol(aes.Decrypt( Enums.Stocks.get(i).getSymbol(),
                                Enums.Autorizations.getAesKey(),Enums.Autorizations.getAesIV()));
                    }
                    */

                    Enums.IsStoksReady=true;

                }
                else
                    Log.i("RetroCalls Failure",response.toString());
            }

            @Override
            public void onFailure(Call<HStokListResp> call, Throwable t) {

            }
        });
    }




}
