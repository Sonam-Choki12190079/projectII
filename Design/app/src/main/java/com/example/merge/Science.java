package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Science extends AppCompatActivity {

    private RelativeLayout che;
    private RelativeLayout math;
    private RelativeLayout phy;
    private RelativeLayout bio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_science);

        che = findViewById(R.id.che);
        math = findViewById(R.id.math);
        bio = findViewById(R.id.bio);
        phy=  findViewById(R.id.phy);
    }

    public void che(View view) {


        Intent intent  = new Intent(Science.this, BhsecChe.class);
        startActivity(intent);
    }

    public void math(View view) {

        Intent intent  = new Intent(Science.this, BhsecMath.class);
        startActivity(intent);
    }

    public void bio(View view) {

        Intent intent  = new Intent(Science.this, BhsecBio.class);
        startActivity(intent);

    }

    public void physics(View view) {
        Intent intent  = new Intent(Science.this, BhsecPhy.class);
        startActivity(intent);

    }
}