package com.merp.json.placeholder.api.demo.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.merp.json.placeholder.api.demo.adapter.PostResponseAdapter;
import com.merp.json.placeholder.api.demo.databinding.FragmentPostBinding;
import com.merp.json.placeholder.api.demo.model.PostResponse;
import com.merp.json.placeholder.api.demo.service.ApiClient;
import com.merp.json.placeholder.api.demo.service.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostFragment extends Fragment {

    private FragmentPostBinding binding;
    private ApiInterface apiInterface;
    private final ArrayList<PostResponse> postResponses = new ArrayList<>();
    private PostResponseAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentPostBinding.inflate(inflater, container, false);
        onInit();

        return binding.getRoot();
    }

    private void onInit() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        getAllPosts();
    }

    private void getAllPosts() {
        Call<List<PostResponse>> call = apiInterface.getAllPosts();
        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(@NonNull Call<List<PostResponse>> call, @NonNull Response<List<PostResponse>> response) {
                if (response.code() == 200 && response.body() != null) {
                    for (PostResponse item : response.body()) {
                        postResponses.add(new PostResponse(item.getUserId(), item.getId(), item.getTitle(), item.getBody(), false));
                    }
                    adapter = new PostResponseAdapter(postResponses);
                    binding.postRcv.setLayoutManager(new LinearLayoutManager(PostFragment.this.getContext(), LinearLayoutManager.VERTICAL, false));
                    binding.postRcv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PostResponse>> call, @NonNull Throwable t) {
                Log.e("===============> ", "API CALLING ERR -> " + t.getMessage());
            }
        });
    }
}