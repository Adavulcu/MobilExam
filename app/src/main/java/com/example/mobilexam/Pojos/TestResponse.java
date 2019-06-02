package com.example.mobilexam.Pojos;

import com.google.gson.annotations.SerializedName;
public class TestResponse {

    @SerializedName("ad")
    private String ad;

    public void setAd(String ad)
    {
        this.ad=ad;
    }
    public String getAd()
    {
        return ad;
    }
}