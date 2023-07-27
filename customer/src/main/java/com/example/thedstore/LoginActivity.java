package com.example.thedstore;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText email, passw;
    Button loginbtn, singupbtn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        email = findViewById(R.id.email1);
        passw = findViewById(R.id.password1);
        loginbtn = findViewById(R.id.login1);
        singupbtn = findViewById(R.id.singup1);
        singupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String semail = email.getText().toString().trim();
                String spassw = passw.getText().toString().trim();

                if (!(semail.isEmpty() || spassw.isEmpty())) {
                    Dialog builder = new Dialog(LoginActivity.this);
                    builder.setCancelable(false);
                    builder.setContentView(R.layout.loading_event);
                    builder.show();
                    auth = FirebaseAuth.getInstance();
                    auth.signInWithEmailAndPassword(semail, spassw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(LoginActivity.this, "Login successfuly", Toast.LENGTH_SHORT).show();
                            builder.dismiss();
                            startActivity(new Intent(LoginActivity.this, Home.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            builder.dismiss();
                            Toast.makeText(LoginActivity.this, "try again " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(LoginActivity.this, "Invalid details", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

}