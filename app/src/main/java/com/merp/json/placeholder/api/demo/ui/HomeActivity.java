package com.merp.json.placeholder.api.demo.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.tabs.TabLayoutMediator;
import com.merp.json.placeholder.api.demo.adapter.MyPagerAdapter;
import com.merp.json.placeholder.api.demo.animation.ZoomOutPageTransformer;
import com.merp.json.placeholder.api.demo.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        onInit();
    }

    private void onInit() {
        MyPagerAdapter fragAdapter = new MyPagerAdapter(this);
        binding.viewPager2.setAdapter(fragAdapter);
        binding.viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        new TabLayoutMediator(binding.myTab, binding.viewPager2,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText("Home");
                    } else {
                        tab.setText("Favorite");
                    }
                }).attach();
    }

}