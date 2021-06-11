package com.example.merge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;

public class admin extends AppCompatActivity {
    ImageView imageView;
//    RelativeLayout update;
//    RelativeLayout view;
//    RelativeLayout usertable;

   private CardView update;
    private CardView view;
   private CardView usertable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        imageView = findViewById(R.id.out);

//        update = findViewById(R.id.relative1);
//        view = findViewById(R.id.relative2);
//        usertable = findViewById(R.id.relative3);

        update = findViewById(R.id.update);
        view = findViewById(R.id.view);
        usertable = findViewById(R.id.usertable);





    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        if (item.getItemId()==R.id.night_mode){
//            int nightMode = AppCompatDelegate.getDefaultNightMode();
//            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES){
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//            }
//            else {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//            }
//        }
//        return true;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.example_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }



    public void update(View view) {
        Intent intent = new Intent (admin.this, UploadPdfActivity.class);
        startActivity(intent);
    }

    public void view(View view) {
        Intent intent = new Intent(admin.this, MainActivity.class);
        startActivity(intent);
    }

    public void usertable(View view) {
       Intent intent = new Intent(admin.this, showUserAdmin.class);
       startActivity(intent);
    }

    public void Log(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }


//    public void LogOut(View view) {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(getApplicationContext(),Login.class));
//    }


}