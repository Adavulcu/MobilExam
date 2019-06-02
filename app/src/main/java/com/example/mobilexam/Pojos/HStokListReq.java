package com.example.mobilexam.Pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HStokListReq {



    @SerializedName("period")
    @Expose
    private String period;

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

}