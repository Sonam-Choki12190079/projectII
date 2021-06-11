package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Arts extends AppCompatActivity {
    private RelativeLayout acc;
    private RelativeLayout Bmath;
    private RelativeLayout Eco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts);

        acc = findViewById(R.id.acc);
        Bmath = findViewById(R.id.Bmath);
        Eco = findViewById(R.id.Eco);
    }

    public void media(View view) {
        Intent intent  = new Intent(Arts.this, ArtsMedia.class);
        startActivity(intent);
    }

    public void geo(View view) {
        Intent intent  = new Intent(Arts.this, ArtsGeo.class);
        startActivity(intent);
    }

    public void his(View view) {    Intent intent  = new Intent(Arts.this, ArtsHis.class);
        startActivity(intent);

    }
}