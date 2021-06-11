package com.example.merge;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Pattern;

public class  Registration extends AppCompatActivity {
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    // "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");
    private TextInputLayout rUser, rPassword, rEmail, mConfirm;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseFirestore fStore;
    private String userID;



    private  TextView mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
//        getSupportActionBar().hide();

        rUser = findViewById(R.id.edtUser1);
        rEmail = findViewById(R.id.edtEmail1);
        rPassword = findViewById(R.id.edtPassword1);
        btnRegister = findViewById(R.id.btn1);
        mConfirm = findViewById(R.id.edtConfirm);
        progressBar = findViewById(R.id.progressBar1);
        btnRegister.setOnClickListener(this::registered);
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        mLoginBtn = findViewById(R.id.textView);

        // hide the toolbar



    }

    private boolean validateEmail() {
        String email = rEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            rEmail.setError("Field Cannot Be Empty!");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            rEmail.setError("Invalid Email!");
            return false;
        } else {
            rEmail.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String password = rPassword.getEditText().getText().toString();
        String confirm = mConfirm.getEditText().getText().toString();
        if (password.isEmpty()) {
            rPassword.setError("Field Cannot Be Empty");
            return false;
        } else if (confirm.isEmpty()) {
            mConfirm.setError("Field Cannot Be  Empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            rPassword.setError("Password Is Weak");
            return false;
        }//else if(password.length()==8){
//            rPassword.setError("password is too long");
//            return false;
//
//        }

        else if (!password.equals(confirm)) {
            mConfirm.setError("Password Did Not Match");
            return false;

        } else {
            rPassword.setError(null);
            return true;
        }

    }

    private boolean validateUser() {
        String user = rUser.getEditText().getText().toString().trim();
        if (user.isEmpty()) {

            rUser.setError("Field Cannot Be Empty");
            return false;
        }

        else {
            rUser.setError(null);
            return true;
        }
    }
    private void sendToLogin(){
        Intent intent = new Intent(Registration.this, Login.class);
        startActivity(intent);
        finish();
    }


    public void registered(View view) {
        if (!validateEmail() | !validatePassword() | !validateUser()) {
            return;
        }
        String email = rEmail.getEditText().getText().toString().trim();
        String password = rPassword.getEditText().getText().toString().trim();
        String user = rUser.getEditText().getText().toString().trim();
        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    // send verification link

                    FirebaseUser fuser = mAuth.getCurrentUser();
                    fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(Registration.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: Email not sent " + e.getMessage());
                        }
                    });

                    Toast.makeText(Registration.this, "User Created.", Toast.LENGTH_SHORT).show();
                    userID = mAuth.getCurrentUser().getUid();
//                    FirebaseUser user =mAuth.getCurrentUser();
                    DocumentReference documentReference = fStore.collection("Users").document(userID);
                    Map<String, Object> user1 = new HashMap<>();
                    user1.put("user", user);
                    user1.put("email", email);
                    user1.put("password", password);
                    user1.put("isUser", "0");
                    documentReference.set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d("TAG", "successfully created");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d("TAG", "onFailure: " + e.toString());
                        }
                    });

                    sendToLogin();
                    mAuth.signOut();


                } else {
                    Toast.makeText(Registration.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });

//        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
//            if (task.isSuccessful()) {
//                // startActivity(new Intent(getApplicationContext(), main3.class));
//
//
//                Objects.requireNonNull(mAuth.getCurrentUser()).sendEmailVerification().addOnCompleteListener(task1 -> {
//                    if (task1.isSuccessful()) {
//                        Toast.makeText(Registration.this, "Verify Your Email to Login", Toast.LENGTH_SHORT).show();
//                        rEmail.getEditText().setText(" ");
//                        rPassword.getEditText().setText(" ");
//
//                        userID = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();
//                        DocumentReference data = fStore.collection("user").document(userID);
//                        Map<String, Object> user1 = new HashMap<>();
//                        user1.put("user", user);
//                        user1.put("email", email);
//                        user1.put("password", password);
//                        user1.put("isUser", "0");
////                        data.set(user1).addOnSuccessListener(aVoid -> {
////                        fStore.collection("user")
////                                .whereEqualTo("uid",uid)
////                                .add(new usermodel(user,email,password,userID));
////
////                        Toast.makeText(Registration.this, "Successfully Created", Toast.LENGTH_SHORT).show();
//
//
//                        sendToLogin();
//
//                        mAuth.signOut();
//                        data.set(user1).addOnSuccessListener(aVoid -> {
//                            Toast.makeText(Registration.this, "Successfully Created", Toast.LENGTH_SHORT).show();
//
//
//                        }).addOnFailureListener(e -> Toast.makeText(Registration.this, "Failed To Save ", Toast.LENGTH_SHORT).show());
//                        startActivity(new Intent(getApplicationContext(), Login.class));
//
//
//                    }
//                    else {
//                        Toast.makeText(Registration.this, Objects.requireNonNull(task1.getException()).getMessage(), Toast.LENGTH_SHORT).show();
//                    }
//
//
//                });
//
//
//                progressBar.setVisibility(View.INVISIBLE);
//
//            } else {
//
//                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
//                    rEmail.setError("Email Already Exist");
//                    progressBar.setVisibility(View.INVISIBLE);
//                }
//
//                //    Toast.makeText(MainActivity2.this, "error",  Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
////        mLoginBtn.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                startActivity(new Intent(getApplicationContext(), Login.class));
////            }
////        });
////
////
    }

    public void SignIn(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }


}