package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class BHSEC extends AppCompatActivity {
    private RelativeLayout science;
    private RelativeLayout commerce;
    private RelativeLayout arts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_h_s_e_c);
        science = findViewById(R.id.sci);
        commerce = findViewById(R.id.com);
        arts = findViewById(R.id.arts);
    }

    public void Science(View view) {
        Intent intent  = new Intent(BHSEC.this, Science.class);
        startActivity(intent);

    }


    public void Commerce(View view) {

        Intent intent  = new Intent(BHSEC.this, Commerce.class);
        startActivity(intent);
    }

    public void Arts(View view) {

        Intent intent  = new Intent(BHSEC.this, Arts.class);
        startActivity(intent);
    }



}