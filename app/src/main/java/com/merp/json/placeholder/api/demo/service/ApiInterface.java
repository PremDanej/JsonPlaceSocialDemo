package com.merp.json.placeholder.api.demo.service;

import com.merp.json.placeholder.api.demo.model.PostResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("posts")
    Call<List<PostResponse>> getAllPosts();
}


