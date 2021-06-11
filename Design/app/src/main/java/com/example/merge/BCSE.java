package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class BCSE extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_c_s_e);
    }

    public void Science(View view) {
        Intent intent  = new Intent(BCSE.this, BcseChem.class);
        startActivity(intent);
    }

    public void maths(View view) {
        Intent intent  = new Intent(BCSE.this, BcseM.class);
        startActivity(intent);
    }

    public void geo(View view) {
        Intent intent  = new Intent(BCSE.this, BcseGeo.class);
        startActivity(intent);
    }
}