package com.gcit.Linuxcommand;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView Forgetpassword;
    EditText inputEmail,inputPassword;
    Button btnSignup, mSignUp;
    CheckBox mCheckBox;
    private FirebaseAuth mAuth;
    ProgressDialog mLoadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSignUp=findViewById(R.id.btnSignup);
        inputEmail=findViewById(R.id.inputEmail);
        mCheckBox = findViewById(R.id.checkbox);
        inputPassword=findViewById(R.id.inputPassword);
        btnSignup=findViewById(R.id.Loginbtn);
        Forgetpassword=findViewById(R.id.Forgotpassword);



        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    inputPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else{
                    inputPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });


        Forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Login.this,ForgotPassword.class);
                startActivity(intent);
            }
        });
        btnSignup.setOnClickListener(v -> {checkCrededentials();});
        mAuth= FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(Login.this);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registration.class));
            }
        });
    }
    private void checkCrededentials() {
        String email = inputEmail.getText().toString();

        String password = inputPassword.getText().toString();
        if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "email.is not valid");
        }
        else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Password is not valid");
        }
        mLoadingBar.setTitle("Login");
        mLoadingBar.setMessage("Waiting for login");
        mLoadingBar.setCanceledOnTouchOutside(false);
        mLoadingBar.show();
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(Login.this,new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    if(mAuth.getCurrentUser().isEmailVerified()){
                        mLoadingBar.dismiss();
                        String email = inputEmail.getText().toString().trim();
                        Intent intent= new Intent(Login.this, MainActivity.class);
                        intent.putExtra("Email", email);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(Login.this, "Please Verify your email address", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(Login.this,task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void showError(EditText input, String s) {
        input.setError(s);
        input.requestFocus();
    }
    public void BackMenu(View view){
        Intent back= new Intent(this, Home.class);
        startActivity(back);
    }
}