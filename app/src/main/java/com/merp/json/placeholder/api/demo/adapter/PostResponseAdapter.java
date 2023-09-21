package com.merp.json.placeholder.api.demo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.merp.json.placeholder.api.demo.R;
import com.merp.json.placeholder.api.demo.databinding.ItemViewPostBinding;
import com.merp.json.placeholder.api.demo.model.PostResponse;

import java.util.ArrayList;

public class PostResponseAdapter extends RecyclerView.Adapter<PostResponseAdapter.MyViewHolder> {
    ArrayList<PostResponse> list;

    public PostResponseAdapter(ArrayList<PostResponse> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(ItemViewPostBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PostResponse item = list.get(position);
        holder.binding.txtTitle.setText(item.getTitle());
        holder.binding.txtBody.setText(item.getBody());
        holder.binding.imgFavorite.setImageResource(setFavoriteItem(item.isFavorite()));
        holder.binding.imgFavorite.setOnClickListener(view -> {
            item.setFavorite(!item.isFavorite());
            notifyDataSetChanged();
        });
    }

    private int setFavoriteItem(boolean favorite) {
        if (favorite)
            return R.drawable.ic_favorite;
        else
            return R.drawable.ic_un_favorite;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemViewPostBinding binding;

        public MyViewHolder(@NonNull ItemViewPostBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
