package com.example.merge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.ArrayList;

public class UploadPdfActivity extends AppCompatActivity {

    Button upload;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner catagory, subject;
    //    FirebaseFirestore fStore;
    Context mContext = UploadPdfActivity.this;

    ImageView pdfUpload;
    Uri pdfFilePath;

    StorageReference storageReference;
    DatabaseReference databaseReference;

    ArrayList<String> arrayList_Catagory;
    ArrayAdapter<String> arrayAdapter_Catagory;
    ProgressBar progressBar;
    StorageReference reference;

    ArrayList<String> arrayList_rcse;
    ArrayList<String> arrayList_pe;
    ArrayList<String> arrayList_bhsec;
    ArrayList<String> arrayList_bsec;
    ArrayAdapter<String> arrayAdapter_Subject;

    String year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_pdf);
        catagory = findViewById(R.id.SpinnerCatagory);
        subject = findViewById(R.id.spinnerSubject);
        progressBar = findViewById(R.id.progressBar);

        //fStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference("PDF");

        radioGroup = findViewById(R.id.yeargroup);

        upload = findViewById(R.id.upload);
        pdfUpload = findViewById(R.id.pdf);

        arrayList_Catagory = new ArrayList<>();
        arrayList_Catagory.add("RCSE");
        arrayList_Catagory.add("PE");
        arrayList_Catagory.add("BHSEC");
        arrayList_Catagory.add("BSEC");

        arrayAdapter_Catagory = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_Catagory);
        catagory.setAdapter(arrayAdapter_Catagory);

        arrayList_rcse = new ArrayList<>();
        arrayList_rcse.add("Technical");

        arrayList_pe = new ArrayList<>();
        arrayList_pe.add("General");

        arrayList_bhsec = new ArrayList<>();
        arrayList_bhsec.add("Chemistry");
        arrayList_bhsec.add("English-I");
        arrayList_bhsec.add("Commerce");
        arrayList_bhsec.add("Biology");
        arrayList_bhsec.add("Physics");
        arrayList_bhsec.add("Maths");
        arrayList_bhsec.add("Media");
        arrayList_bhsec.add("Geography");
        arrayList_bhsec.add("History");
        arrayList_bhsec.add("B.Maths");
        arrayList_bhsec.add("Economics");
        arrayList_bhsec.add("Accountancy");
        arrayList_bhsec.add("English-II");

        arrayList_bsec = new ArrayList<>();
        arrayList_bsec.add("History");
        arrayList_bsec.add("Chemistry");
        arrayList_bsec.add("Maths");

        pdfUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dexter.withContext(getApplicationContext())
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                Intent intent = new Intent();
                                intent.setType("application/pdf");
                                intent.setAction(Intent.ACTION_GET_CONTENT);
                                startActivityForResult(Intent.createChooser(intent, "Select PDF Files"),101);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                permissionToken.continuePermissionRequest();
                            }
                        }).check();
            }
        });
        catagory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    arrayAdapter_Subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_rcse);
                    subject.setAdapter(arrayAdapter_Subject);
                    upload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (year.equals("2016")) {
                                String year = "2016";
                                String title = "Technical";
                                String db = "RESC2018";
                                upload1(year, title, db);
                            }
                            else if (year.equals("2017")) {
                                String year = "2017";
                                String title = "Technical";
                                String db = "RESC2018";
                                upload1(year, title, db);
                            }
                            else if (year.equals("2018")) {
                                String year = "2018";
                                String title = "Technical";
                                String db = "RESC2018";
                                upload1(year, title, db);
                            }
                            else {
                                String year = "2019";
                                String title = "Technical";
                                String db = "RESC2018";
                                upload1(year, title, db);
                            }
                        }
                    });
                }
                if (position == 1) {
                    arrayAdapter_Subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_pe);
                    subject.setAdapter(arrayAdapter_Subject);
                    upload.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (year.equals("2016")) {
                                String year = "2016";
                                String title = "General";
                                String db = "PE2018";
                                upload1(year, title, db);
                            }
                            else if (year.equals("2017")) {
                                String year = "2017";
                                String title = "General";
                                String db = "PE2018";
                                upload1(year, title, db);
                            }
                            else if (year.equals("2018")) {
                                String year = "2018";
                                String title = "General";
                                String db = "PE2018";
                                upload1(year, title, db);
                            }
                            else {
                                String year = "2019";
                                String title = "General";
                                String db = "PE2018";
                                upload1(year, title, db);
                            }
                        }
                    });
                }
                if (position == 2) {
                    arrayAdapter_Subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_bhsec);
                    subject.setAdapter(arrayAdapter_Subject);

                    subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if(position == 0){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Chemistry";
                                            String db = "BHSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Chemistry";
                                            String db = "BHSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Chemistry";
                                            String db = "BHSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                        else{
                                            String year = "2019";
                                            String title = "Chemistry";
                                            String db = "BHSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 1){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "English";
                                            String db = "BHSECENGLISH2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "English";
                                            String db = "BHSECENGLISH2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "English";
                                            String db = "BHSECENGLISH2018";
                                            upload1(year, title, db);
                                        }
                                        if(year.equals("2019")) {
                                            String year = "2019";
                                            String title = "English";
                                            String db = "BHSECENGLISH2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 2){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Commerce";
                                            String db = "BHSECCOMMERCE2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Commerce";
                                            String db = "BHSECCOMMERCE2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Commerce";
                                            String db = "BHSECCOMMERCE2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Commerce";
                                            String db = "BHSECCOMMERCE2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 3){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Biology";
                                            String db = "BHSECBIOLOGY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Biology";
                                            String db = "BHSECBIOLOGY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Biology";
                                            String db = "BHSECBIOLOGY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Commerce";
                                            String db = "BHSECBIOLOGY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 4){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Physics";
                                            String db = "BHSECPHYSICS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Physcis";
                                            String db = "BHSECPHYSICS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Physics";
                                            String db = "BHSECPHYSICS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Physics";
                                            String db = "BHSECPYSICS2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 5){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Maths";
                                            String db = "BHSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Maths";
                                            String db = "BHSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Maths";
                                            String db = "BHSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Maths";
                                            String db = "BHSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 6){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Media";
                                            String db = "BHSECMEDIA2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Media";
                                            String db = "BHSECMEDIA2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Media";
                                            String db = "BHSECMEDIA2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Media";
                                            String db = "BHSECMEDIA2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 7){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Geography";
                                            String db = "BHSECGEOGRAPHY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Geography";
                                            String db = "BHSECGEOGRAPHY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Geography";
                                            String db = "BHSECGEOGRAPHY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Geography";
                                            String db = "BHSECGEOGRAPHY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 8){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "History";
                                            String db = "BHSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "History";
                                            String db = "BHSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "History";
                                            String db = "BHSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "History";
                                            String db = "BHSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 9){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "B-Maths";
                                            String db = "BHSECBMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "B-Maths";
                                            String db = "BHSECBMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "B-Maths";
                                            String db = "BHSECBMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "B-Maths";
                                            String db = "BHSECBMATHS2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 10){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Economics";
                                            String db = "BHSECECONOMICS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Economics";
                                            String db = "BHSECECONOMICS2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Economics";
                                            String db = "BHSECECONOMICS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Economics";
                                            String db = "BHSECECONOMICS2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else if(position == 11){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Accountancy";
                                            String db = "BHSECACCOUNTANCY018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Accountancy";
                                            String db = "BHSECACCOUNTANCY2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Accountancy";
                                            String db = "BHSECACCOUNTANCY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Accountancy";
                                            String db = "BHSECACCOUNTANCY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            else {
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "English-II";
                                            String db = "BHSECENGLISHII2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "English-II";
                                            String db = "BHSECENGLISHII2018";
                                            upload1(year, title, db);
                                        }
                                        else if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "English-II";
                                            String db = "BHSECENGLISHII2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "English-II";
                                            String db = "BHSECENGLISHII2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }

                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                if(position == 3){
                    arrayAdapter_Subject = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, arrayList_bsec);
                    subject.setAdapter(arrayAdapter_Subject);

                    subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            if (position==0){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "History";
                                            String db = "BSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "History";
                                            String db = "BSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "History";
                                            String db = "BSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                        if(year.equals("2019")) {
                                            String year = "2019";
                                            String title = "History";
                                            String db = "BSECHISTORY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            if (position==1){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Chemistry";
                                            String db = "BSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Chemistry";
                                            String db = "BSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Chemistry";
                                            String db = "BSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Chemistry";
                                            String db = "BSECCHEMISTRY2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                            if (position==2){
                                upload.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (year.equals("2016")) {
                                            String year = "2016";
                                            String title = "Maths";
                                            String db = "BSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2017")) {
                                            String year = "2017";
                                            String title = "Maths";
                                            String db = "BSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2018")) {
                                            String year = "2018";
                                            String title = "Maths";
                                            String db = "BSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                        if (year.equals("2019")){
                                            String year = "2019";
                                            String title = "Maths";
                                            String db = "BSECMATHS2018";
                                            upload1(year, title, db);
                                        }
                                    }
                                });
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void upload1(String year, String title, String db) {
        ProgressDialog progressDialog = new ProgressDialog(mContext);
        progressDialog.setTitle("Please wait");
        progressDialog.show();
        if (pdfFilePath != null) {
            reference = storageReference.child("PDF/" + databaseReference.push().getKey() + ".pdf");
            reference.putFile(pdfFilePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    PDFHelperClass pdf = new PDFHelperClass(year, title, uri.toString());
                                    databaseReference.child(db).child(year).setValue(pdf);
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "File Uploaded Successfully", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                            float percent = (100 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                            progressDialog.setMessage("File Uploading...." + (int) percent + "%");
                        }
                    });
        } else {
            progressDialog.dismiss();
            Toast.makeText(getApplicationContext(), "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 101 && resultCode == RESULT_OK){
            pdfFilePath = data.getData();
        }
    }

    public void check(View view) {
        int pos = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(pos);
        year = radioButton.getText().toString();
    }

    public void AdminNotification(View view) {
        Intent intent = new Intent(UploadPdfActivity.this, Notificationedt.class);
        startActivity(intent);
    }
}