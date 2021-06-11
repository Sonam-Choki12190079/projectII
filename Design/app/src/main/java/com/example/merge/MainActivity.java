package com.example.merge;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

   private CardView classten;
    private CardView classtwelve;
    private CardView pe;
    private CardView rcse;

//    private Button RCSE;
//    private Button PE;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        classten = (CardView) findViewById(R.id.button);
        classtwelve = (CardView) findViewById(R.id.BHSEC);
        pe = (CardView) findViewById(R.id.pe);
        rcse = (CardView) findViewById(R.id.RCSE);



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void LogOut(MenuItem item) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),Login.class));
        finish();
    }

    public void Share(MenuItem item) {
        Intent intent = new Intent(MainActivity.this, Discussion.class);
        startActivity(intent);
    }

    public void Notification(MenuItem item) {
        startActivity(new Intent(this, ShowNotification.class));
    }

    public void classten(View view) {
        Intent intent = new Intent(MainActivity.this, BCSE.class);
        startActivity(intent);

    }

    public void classtwelve(View view) {
        Intent intent = new Intent(MainActivity.this, BHSEC.class);
                startActivity(intent);
    }

    public void pee(View view) {
        Intent intent = new Intent(MainActivity.this, PE.class);
              startActivity(intent);
    }

    public void rece(View view) {
        Intent intent = new Intent(MainActivity.this, RCSE.class);
               startActivity(intent);
    }

    public void reset(MenuItem item) {

        Intent intent = new Intent(MainActivity.this, resetPassword.class);
        startActivity(intent);

    }
}
