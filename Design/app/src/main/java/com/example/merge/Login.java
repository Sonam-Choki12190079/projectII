package com.example.merge;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;


public class Login extends AppCompatActivity {
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
    private TextInputLayout mEmail, mPassword;
    private Button btnLogin;
    private TextView mSignUp, mForgotPass;
    private FirebaseAuth mAuth;
    private ProgressBar progressBar;
    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmail = findViewById(R.id.edtEmail);
        mPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btn);
        progressBar = findViewById(R.id.progressBar);
        mForgotPass = findViewById(R.id.txtForgot);
        mForgotPass.setOnClickListener(this::forgot);
        mAuth = FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();

        //  getSupportActionBar().hide();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getEditText().getText().toString().trim();
                String password = mPassword.getEditText().getText().toString().trim();
                if(!validateEmail() | !validatePassword()) {
                    return;
                }
                mAuth.signInWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                       if (mAuth.getCurrentUser().isEmailVerified()) {
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            checkUserAccessLevel(authResult.getUser().getUid());
                        }
                        else {
                            mEmail.setError("Please Verify Your Email ");


                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }
        });

    }

    private boolean validateEmail() {
        String email = mEmail.getEditText().getText().toString().trim();
        if (email.isEmpty()) {
            mEmail.setError("Field Cannot Be Empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Invalid Email");
            return false;
        } else {
            mEmail.setError(null);
            return true;
        }

    }

    private boolean validatePassword() {
        String password = mPassword.getEditText().getText().toString().trim();
        if (password.isEmpty()) {
            mPassword.setError("Field Cannot Be Empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {
            mPassword.setError("Incorrect Password!");
            return false;
        } else {
            mPassword.setError(null);
            return true;
        }

    }

    public void signUp(View view) {

        startActivity(new Intent(getApplicationContext(), Registration.class));
        finish();
    }

    public void forgot(View view) {
        EditText resetEmail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        //  passwordResetDialog.setMessage(Html.fromHtml("<font color = '#FF6200EE'>"++"</font>"));
        passwordResetDialog.setTitle("Forgot Password?");
        passwordResetDialog.setMessage("Enter your email to reset password");
        passwordResetDialog.setView(resetEmail);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // extract the email
                String mail = resetEmail.getText().toString();
                mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Login.this, "Reset password link sent to your email", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login.this, "Failed to reset the password" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // closing the dialog

            }
        });
        passwordResetDialog.create().show();

//        AlertDialog alertDialog=passwordResetDialog.create();
//        alertDialog.show();


    }
    private void checkUserAccessLevel(String uid) {
        DocumentReference df = firestore.collection("Users").document(String.valueOf(uid));
        //extract the data from the document
        df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.d("TAG", "onSuccess: " + documentSnapshot.getData());
                //identify the user access level
                if (documentSnapshot.getString("isAdmin") != null) {
                    //user is admin

                    startActivity(new Intent(getApplicationContext(),admin.class));
                    finish();
                }

                if (documentSnapshot.getString("isUser") != null) {
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() != null){
            DocumentReference df = FirebaseFirestore.getInstance().collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid());
            df.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot.getString("isAdmin") != null) {
                        startActivity(new Intent(getApplicationContext(), admin.class));
                        finish();
                    }

                    if (documentSnapshot.getString("isUser") != null) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        finish();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(getApplicationContext(), Login.class));
                    finish();
                }
            });
        }
    }




//    @Override
//    protected void onStart() {
//        super.onStart();
//        updateUI();
//    }
}