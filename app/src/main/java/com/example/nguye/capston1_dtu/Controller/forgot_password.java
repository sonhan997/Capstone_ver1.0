package com.example.nguye.capston1_dtu.Controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.Validate;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgot_password extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    ProgressBar progressBar;
    TextInputLayout userEmail;
    Button userPass;
    TextView loginforgot;
    FirebaseAuth mAuth;
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        progressBar = findViewById(R.id.progressBar);
        userEmail = findViewById(R.id.etUserEmail);
        userPass = findViewById(R.id.btnForgotPass);
        loginforgot= findViewById(R.id.btnForgotLogin);
        userPass.setOnClickListener(this);
        loginforgot.setOnClickListener(this);



        mAuth = FirebaseAuth.getInstance();
        mAuth.setLanguageCode("en");



    }
    private void QuenMk(){
                progressBar.setVisibility(View.VISIBLE);
                mAuth.sendPasswordResetEmail(userEmail.getEditText().getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    Toast.makeText(forgot_password.this,
                                            "KIỂM TRA EMAIL VÀ CẬP NHẬT LẠI MẬT KHẨU", Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(forgot_password.this, LoginActivity.class));
                                } else {
                                    Toast.makeText(getApplicationContext(), "THÔNG TIN EMAIL KHÔNG TỒN TẠI, NHẬP LẠI\n", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

    @Override
    public void onClick(View v) {
        Validate validate = new Validate();
        String email = userEmail.getEditText().getText().toString().trim();
        if(v==userPass){
            if(validate.validateEmail(email,userEmail)){
                QuenMk();
                return;
            }
        }
        if(v==loginforgot){
            startActivity(new Intent(forgot_password.this, LoginActivity.class));
        }

    }
}

