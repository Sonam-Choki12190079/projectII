package com.example.merge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class ArtsMedia extends AppCompatActivity {

    RecyclerView recyclerView;
    PDFAdapter pdfAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arts_media);
        recyclerView = findViewById(R.id.reyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<PDFHelperClass> options =
                new FirebaseRecyclerOptions.Builder<PDFHelperClass>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("PDF").child("BHSECMEDIA2018"),PDFHelperClass.class)
                        .build();

        pdfAdapter = new PDFAdapter(options);
        recyclerView.setAdapter(pdfAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        pdfAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        pdfAdapter.stopListening();
    }
}