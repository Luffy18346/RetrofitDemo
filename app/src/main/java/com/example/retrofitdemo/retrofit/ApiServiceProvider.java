package com.example.retrofitdemo.retrofit;

import android.content.Context;
import android.util.Log;

import com.example.retrofitdemo.models.ErrorObject;
import com.example.retrofitdemo.models.GitHubRepo;
import com.example.retrofitdemo.retrofit.retrofitutils.HttpUtil;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiServiceProvider extends RetrofitBase {
    private static final String TAG = ApiServiceProvider.class.getSimpleName();

    private static ApiServiceProvider apiServiceProvider;
    private ApiServices apiServices;
    private Context context;
    private RetrofitListener mRequestCallback;

    private ApiServiceProvider(Context context, RetrofitListener mRequestCallback) {
        super(context, true);
        this.context = context;
        this.mRequestCallback = mRequestCallback;
        apiServices = retrofit.create(ApiServices.class);
    }

    public static ApiServiceProvider getInstance(Context context, RetrofitListener mRequestCallback) {

        if (apiServiceProvider == null) {
            apiServiceProvider = new ApiServiceProvider(context, mRequestCallback);
        }
        return apiServiceProvider;
    }

    public void getReposForUser(String parameter, final String apiFlag) {
        Call<List<GitHubRepo>> call = apiServices.getReposForUser(parameter);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                validateResponse(response, mRequestCallback, apiFlag);
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                mRequestCallback.onResponseError(HttpUtil.getServerErrorPojo(context), t, apiFlag);
            }
        });
    }

    private void validateResponse(Response response, RetrofitListener retrofitListener, String apiFlag) {

        Log.d(TAG, "validateResponse: " + response.code());

        if (response.code() == 200) {
            retrofitListener.onResponseSuccess(response, apiFlag);
        } else {
            error(response, retrofitListener, apiFlag);
        }
    }

    private void error(Response response, RetrofitListener retrofitListener, String apiFlag) {
        Gson gson = new Gson();
        ErrorObject errorPojo;
        try {
            errorPojo = gson.fromJson((response.errorBody()).string(), ErrorObject.class);
            if (errorPojo == null) {
                errorPojo = HttpUtil.getServerErrorPojo(context);
            }
            retrofitListener.onResponseError(errorPojo, null, apiFlag);
        } catch (Exception e) {
            retrofitListener.onResponseError(HttpUtil.getServerErrorPojo(context), null, apiFlag);
        }
    }

}
