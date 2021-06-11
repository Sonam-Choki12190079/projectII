package com.example.merge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class showUserAdmin extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore fStore;
    private UserAdminAdaptor adaptor;
    private List<ModelAdmin> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_admin);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fStore =FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adaptor = new UserAdminAdaptor(this, list);
        recyclerView.setAdapter(adaptor);

        showData();
    }

    private void showData() {
        fStore.collection("Users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot snapshot:task.getResult()){
                            ModelAdmin modelAdmin= new ModelAdmin(snapshot.getString("user"),snapshot.getString("email"));
                            list.add(modelAdmin);
                        }
                        adaptor.notifyDataSetChanged();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(showUserAdmin.this,"something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}