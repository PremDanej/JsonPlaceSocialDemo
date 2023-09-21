package com.merp.json.placeholder.api.demo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.merp.json.placeholder.api.demo.R;
import com.merp.json.placeholder.api.demo.adapter.PostResponseAdapter;
import com.merp.json.placeholder.api.demo.databinding.ActivityHomeBinding;
import com.merp.json.placeholder.api.demo.model.PostResponse;
import com.merp.json.placeholder.api.demo.service.ApiClient;
import com.merp.json.placeholder.api.demo.service.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private ApiInterface apiInterface;
    private ArrayList<PostResponse> postResponses = new ArrayList<>();
    private ActivityHomeBinding binding;
    private PostResponseAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onInit();
    }

    private void onInit() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getAllPosts();
    }

    private void getAllPosts() {
        Call<List<PostResponse>> call = apiInterface.getAllPosts();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<PostResponse>> call, Response<List<PostResponse>> response) {
                if (response.code() == 200 && response.body() != null) {
                    postResponses.addAll(response.body());
                    adapter = new PostResponseAdapter(postResponses);
                    binding.postRcv.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.VERTICAL, false));
                    binding.postRcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<PostResponse>> call, Throwable t) {
                Log.e("===============> ", "API CALLING ERR -> " + t.getMessage());
            }
        });
    }
}