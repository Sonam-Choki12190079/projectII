package com.example.merge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class Notificationedt extends AppCompatActivity {
    EditText mYear, mSubject, mCatogory, mDescription;
    Button mSave;
    FirebaseFirestore fStore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificationedt);

        mYear = findViewById(R.id.txtYear);
        mSubject = findViewById(R.id.txtSubject);
        mCatogory = findViewById(R.id.txtCategory);
        mDescription = findViewById(R.id.txtDescription);
        fStore = FirebaseFirestore.getInstance();


        mSave = findViewById(R.id.button_Save);


        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String year = mYear.getText().toString().trim();
                String subject = mSubject.getText().toString().trim();
                String category = mCatogory.getText().toString().trim();
                String description = mDescription.getText().toString().trim();

                String id = UUID.randomUUID().toString();

                saveToFireStore(id, year, subject, category, description);
            }
        });
    }

    private void saveToFireStore(String id, String year, String subject, String category, String description) {
        if(!year.isEmpty() && !subject.isEmpty() && !category.isEmpty() && !description.isEmpty()){
            HashMap<String, Object>map = new HashMap<>();
            map.put("id", id);
            map.put("year", year);
            map.put("category", category);
            map.put("subject", subject);
            map.put("description", description);


            fStore.collection("Notification").document(id).set(map)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText( Notificationedt.this," Notification Data are Saved",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(Notificationedt.this,"Failed",Toast.LENGTH_SHORT).show();

                }
            });


        }else{
            Toast.makeText(this,"empty is not allowed", Toast.LENGTH_SHORT).show();
        }

    }
}


