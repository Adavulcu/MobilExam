package com.example.mobilexam.Pojos;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;



public class HStartResp {

    @SerializedName("aesKey")
    @Expose
    private String aesKey;
    @SerializedName("aesIV")
    @Expose
    private String aesIV;
    @SerializedName("authorization")
    @Expose
    private String authorization;
    @SerializedName("lifeTime")
    @Expose
    private String lifeTime;
    @SerializedName("status")
    @Expose
    private Status status;

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAesIV() {
        return aesIV;
    }

    public void setAesIV(String aesIV) {
        this.aesIV = aesIV;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }

    public String getLifeTime() {
        return lifeTime;
    }

    public void setLifeTime(String lifeTime) {
        this.lifeTime = lifeTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
      class MyError {

        @SerializedName("code")
        @Expose
        private Integer code;
        @SerializedName("message")
        @Expose
        private String message;

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

    }
    class Status {

        @SerializedName("isSuccess")
        @Expose
        private Boolean isSuccess;
        @SerializedName("error")
        @Expose
        private MyError error;

        public Boolean getIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(Boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public MyError getError() {
            return error;
        }

        public void setError(MyError error) {
            this.error = error;
        }

    }
}





