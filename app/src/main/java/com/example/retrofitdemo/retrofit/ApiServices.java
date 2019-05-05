package com.example.retrofitdemo.retrofit;

import com.example.retrofitdemo.models.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("/users/{userId}/repos")
    Call<List<GitHubRepo>> getReposForUser(@Path("userId") String userId);

}
