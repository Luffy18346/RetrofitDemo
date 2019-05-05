package com.example.retrofitdemo.retrofit;

import com.example.retrofitdemo.models.ErrorObject;

import retrofit2.Response;

public interface RetrofitListener {
    void onResponseSuccess(Response response, String apiFlag);

    void onResponseError(ErrorObject errorObject, Throwable throwable, String apiFlag);
}
