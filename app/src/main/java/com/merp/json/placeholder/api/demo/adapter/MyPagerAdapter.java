package com.merp.json.placeholder.api.demo.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.adapter.FragmentViewHolder;

import com.merp.json.placeholder.api.demo.ui.FavoriteFragment;
import com.merp.json.placeholder.api.demo.ui.PostFragment;

import java.util.List;

public class MyPagerAdapter extends FragmentStateAdapter {
    public MyPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return new PostFragment();
        } else if (position == 1) {
            return new FavoriteFragment();
        }
        return new PostFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
