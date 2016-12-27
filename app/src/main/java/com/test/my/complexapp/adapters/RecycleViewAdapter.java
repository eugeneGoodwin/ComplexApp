package com.test.my.complexapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.my.complexapp.R;
import com.test.my.complexapp.retrofit.entries.JsonPost;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.RecycleViewHolder>{

    private List<JsonPost> posts;

    public RecycleViewAdapter(List<JsonPost> posts) {
        this.posts = posts;
    }

    public void setPosts(List<JsonPost> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.bindPost(posts.get(position));
    }

    @Override
    public int getItemCount() {
        return (this.posts != null)? this.posts.size() : 0;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userId)
        TextView userIdTextView;

        @BindView(R.id.title)
        TextView titleTextView;

        @BindView(R.id.description)
        TextView bodyTextView;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindPost(JsonPost post) {
            userIdTextView.setText(String.valueOf(post.getUserId()));
            titleTextView.setText(post.getTitle());
            bodyTextView.setText(post.getBody());
        }
    }
}
