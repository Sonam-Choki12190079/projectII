package com.gcit.Linuxcommand;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView textView;
    TextView Description;

    View v;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView=itemView.findViewById(R.id.image_single_view);
        textView=itemView.findViewById(R.id.textView_single_view);
        Description=itemView.findViewById(R.id.Description);
        v=itemView;
    }
}
