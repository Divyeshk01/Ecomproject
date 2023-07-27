package com.example.thedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Firstscreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_firstscreen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                FirebaseAuth user = FirebaseAuth.getInstance();
               FirebaseUser curr=user.getCurrentUser();

                Intent intent;
                if(curr != null) {
                    intent = new Intent(Firstscreen.this, Home.class);
                }
                else {

                    intent = new Intent(Firstscreen.this, LoginActivity.class);
                }
                startActivity(intent);
                finish();

            }
        },1111);
    }
}