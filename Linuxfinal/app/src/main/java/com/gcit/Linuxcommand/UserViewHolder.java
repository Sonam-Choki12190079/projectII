package com.gcit.Linuxcommand;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    TextView Description;
    Button Deletebtn;
    View v;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        Deletebtn=itemView.findViewById(R.id.btnDelete);
        imageView=itemView.findViewById(R.id.image_single_view);
        textView=itemView.findViewById(R.id.textView_single_view);
        Description=itemView.findViewById(R.id.Description);
        v=itemView;
    }
}
