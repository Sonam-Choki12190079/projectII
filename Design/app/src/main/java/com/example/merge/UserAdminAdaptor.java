package com.example.merge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdminAdaptor extends RecyclerView.Adapter<UserAdminAdaptor.MyViewHolder>{
    private showUserAdmin activity;
    private List<ModelAdmin> mList;

    public UserAdminAdaptor(showUserAdmin activity, List<ModelAdmin>mList){
        this.activity=activity;
        this.mList =mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(R.layout.adminitem,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.user.setText(mList.get(position).getUser());
        holder.email.setText(mList.get(position).getEmail());


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView user, email;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            user=itemView.findViewById(R.id.txt_userName);
            email=itemView.findViewById(R.id.txt_userEmail);

        }
    }

}
