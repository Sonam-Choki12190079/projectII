package com.example.merge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Discussion extends AppCompatActivity {

    ListView lvDiscussionTopics;
    ArrayList<String> ListOfDiscussion = new ArrayList<String>( );
    ArrayAdapter arrayAdpt;
    String Username;

    private DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().getRoot();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);

        lvDiscussionTopics = (ListView) findViewById(R.id.lvDiscussionTopics);
        arrayAdpt  = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListOfDiscussion);
        lvDiscussionTopics.setAdapter(arrayAdpt);

        getUserName();

        dbr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Set<String> set = new HashSet<String>();
                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()){
                    set.add(((DataSnapshot)i.next()).getKey());
                }

                arrayAdpt.clear();
                arrayAdpt.addAll(set);
                arrayAdpt.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        lvDiscussionTopics.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(getApplicationContext(), ChatActivity.class);
                i.putExtra("Selected_topic", ((TextView)view).getText().toString());
                i.putExtra("User_name", Username);
                startActivity(i);



            }
        });

    }

    private void getUserName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        EditText userName = new EditText(this);
        builder.setMessage("Enter Your Name to Chat");


        builder.setView(userName);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Username = userName.getText().toString();

            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getUserName();
            }
        });
        builder.show();

    }
}