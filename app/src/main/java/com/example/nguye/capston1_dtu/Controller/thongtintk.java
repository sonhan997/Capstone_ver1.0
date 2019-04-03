package com.example.nguye.capston1_dtu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.Controller.Share;
import com.example.nguye.capston1_dtu.Controller.viewThongtintaikhoan;
import com.example.nguye.capston1_dtu.R;
import com.example.nguye.capston1_dtu.common.setOnItemClick;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.Executor;

public class thongtintk extends Fragment implements setOnItemClick {

    private TextView txtFullname, txtSDT, txtDate, txtGT;
    private Button btnXem,btnShare,btnLogout;
    private DatabaseReference mDatabase;
    private FirebaseDatabase mFirebase;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogle;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_thongtintk,null);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        btnXem = view.findViewById(R.id.btnXemTTTK);
        btnShare= view.findViewById(R.id.button2SHARE);
        btnLogout=view.findViewById(R.id.btnLogout);
        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), viewThongtintaikhoan.class));

            }
        });
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), Share.class));
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();

                signOutFB();
                startActivity(new Intent(getActivity(), LoginActivity.class));
            }
        });
    }
    private void signOut() {
        // Firebase sign out


        // Google sign out
        mGoogle.signOut().addOnCompleteListener((Executor) this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        updateUI(null);
                    }
                });
    }
    public void signOutFB() {

        LoginManager.getInstance().logOut();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), viewThongtintaikhoan.class);
        Intent ABC = new Intent(getActivity(), Share.class);
        startActivity(intent);
        startActivity(ABC);
    }

    private void updateUI(FirebaseUser user) {

        if (user != null) {

        } else {

        }
    }
   /* public void onItemClick(int position) {
        Intent intent = new Intent(getActivity(), viewThongtintaikhoan.class);
        startActivity(intent);
    }*/




}