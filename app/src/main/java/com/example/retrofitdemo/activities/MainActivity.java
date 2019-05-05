package com.example.retrofitdemo.activities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.retrofitdemo.R;
import com.example.retrofitdemo.models.ErrorObject;
import com.example.retrofitdemo.models.GitHubRepo;
import com.example.retrofitdemo.retrofit.ApiServiceProvider;
import com.example.retrofitdemo.retrofit.RetrofitListener;
import com.example.retrofitdemo.retrofit.retrofitutils.Constants;

import java.util.List;

import retrofit2.Response;

public class MainActivity extends Activity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private ApiServiceProvider apiServiceProvider;

    private ProgressDialog pdLoading = null;
    private Toast mToast = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setProgressDialog();
        retrofitSetup();

        apiServiceProvider.getReposForUser("Luffy18346", Constants.ApiFlags.API_GET_REPOS_OF_USER);
//        //1st api call
//        apiServiceProvider.getSomething("your parameter");
//        //2nd api call
//        apiServiceProvider.getSomethingElse();
    }

    private void retrofitSetup() {

        RetrofitListener mRequestCallback = new RetrofitListener() {
            @Override
            public void onResponseSuccess(Response response, String apiFlag) {

                if (response.body() != null) {

                    switch (apiFlag) {

                        case Constants.ApiFlags.API_GET_REPOS_OF_USER:
                            //handle response
                            //parse response json using gson or keep you response object as return type in ApiServices.java itself
                            List<GitHubRepo> gitHubRepoList = (List<GitHubRepo>) response.body();
                            if (!gitHubRepoList.isEmpty()) {
                                Log.d(TAG, "onResponseSuccess: " + gitHubRepoList.size());
                            }
                            break;
                    }

                } else {

                }

            }

            @Override
            public void onResponseError(ErrorObject errorObject, Throwable throwable, String apiFlag) {
                switch (apiFlag) {
                    case Constants.ApiFlags.API_GET_REPOS_OF_USER:
                        //handle error
                        break;

                }
            }
        };

        apiServiceProvider = ApiServiceProvider.getInstance(this, mRequestCallback);

    }


    private void setProgressDialog() {

        if (pdLoading == null) {

            pdLoading = new ProgressDialog(this);
            pdLoading.setMessage("Loading...");
            pdLoading.setCancelable(false);

        }

    }

    private void showProgressDialog() {

        if (pdLoading == null) {
            setProgressDialog();
        }
        if (pdLoading.isShowing()) {
            pdLoading.cancel();
        }
        pdLoading.show();

    }

    private void hideProgressDialog() {

        if (pdLoading == null) {
            setProgressDialog();
        }

        if (pdLoading.isShowing()) {
            pdLoading.cancel();
        }

    }

    private void showToast(String sDisplayMessage) {

        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, "" + sDisplayMessage, Toast.LENGTH_SHORT);
        mToast.show();

    }

}
