package com.example.nguye.capston1_dtu.Controller;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.example.nguye.capston1_dtu.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class viewThongtintaikhoan extends AppCompatActivity {
    private TextView txtFullname, txtSDT, txtDate, txtGT;
    private Button btnXem;
    private DatabaseReference mDatabase;
    NavigationView nvView;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    private FirebaseDatabase mFirebase;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_thongtintaikhoan);
        Anhxa();
        ReadWrite();

    }
    private void Anhxa(){
        txtFullname=findViewById(R.id.editTextFullName1);
        txtSDT=findViewById(R.id.editTextSDT);
        txtDate=findViewById(R.id.editTextDate);


    }
    private void ReadWrite(){

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();/*
        txtFullname=findViewById(R.id.editTextFullName1);*/
        NavigationView navigationView =  findViewById(R.id.nav_view);
/*
        nvView = findViewById(R.id.nav_view);
        View header = navigationView.getHeaderView(0);*/
        txtFullname =findViewById(R.id.editTextFullName1);
        txtFullname.setText(firebaseUser.getDisplayName()+"");


       /* userEmail = header.findViewById(R.id.textViewEmail);
        bmImage = header.findViewById(R.id.imageView);*/

        /*mDatabase=FirebaseDatabase.getInstance().getReference("UserID");
        mDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                txtFullname.append(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });*/

    }

}
