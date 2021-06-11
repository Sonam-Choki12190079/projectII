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

public class ShowNotification extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FirebaseFirestore fStore;
    private NotificationAdaptor adaptor;
    private List<NotificationModel> list;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notification);

        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fStore =FirebaseFirestore.getInstance();
        list = new ArrayList<>();
        adaptor = new NotificationAdaptor(this, list);
        recyclerView.setAdapter(adaptor);

        showNotification();
    }


    public void showNotification() {
        fStore.collection("Notification").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        for(DocumentSnapshot snapshot:task.getResult()){
                            NotificationModel model = new NotificationModel(snapshot.getString("year"),snapshot.getString("category"),snapshot.getString("subject"),snapshot.getString("description"));
                            list.add(model);
                        }
                        adaptor.notifyDataSetChanged();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ShowNotification.this, "something went wrong",Toast.LENGTH_SHORT).show();
            }
        });
    }
}