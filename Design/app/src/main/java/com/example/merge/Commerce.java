package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class Commerce extends AppCompatActivity {

    private RelativeLayout acc;
    private RelativeLayout Bmath;
    private RelativeLayout Eco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commerce);
        acc = findViewById(R.id.acc);
        Bmath = findViewById(R.id.Bmath);
        Eco = findViewById(R.id.Eco);
    }

    public void accounts(View view) {
        Intent intent  = new Intent(Commerce.this, BhsecAcc.class);
        startActivity(intent);

    }

    public void Bmath(View view) {

        Intent intent  = new Intent(Commerce.this, BhsecBmath.class);
        startActivity(intent);
    }

    public void Eco(View view) {
        Intent intent  = new Intent(Commerce.this, BhsecEco.class);
        startActivity(intent);
    }
}