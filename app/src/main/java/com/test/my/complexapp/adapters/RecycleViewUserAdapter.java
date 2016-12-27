package com.test.my.complexapp.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.my.complexapp.R;
import com.test.my.complexapp.entities.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewUserAdapter extends RecyclerView.Adapter<RecycleViewUserAdapter.RecycleViewHolder>{

    private List<User> users;

    public RecycleViewUserAdapter(List<User> users) {
        this.users = users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.bindPost(users.get(position));
    }

    @Override
    public int getItemCount() {
        return (this.users != null)? this.users.size() : 0;
    }

    public static class RecycleViewHolder extends RecyclerView.ViewHolder {
        //@BindView(R.id.id)
        //TextView idTextView;

        @BindView(R.id.name)
        TextView nameTextView;

        @BindView(R.id.username)
        TextView unTextView;

        @BindView(R.id.email)
        TextView emailTextView;

        @BindView(R.id.phone)
        TextView phoneTextView;

        @BindView(R.id.website)
        TextView webTextView;

        public RecycleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindPost(User user) {
            //idTextView.setText(String.valueOf(user.id()));
            nameTextView.setText(user.name());
            unTextView.setText(user.username());
            emailTextView.setText(user.email());
            phoneTextView.setText(user.phone());
            webTextView.setText(user.website());
        }
    }
}
