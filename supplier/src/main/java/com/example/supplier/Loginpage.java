package com.example.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Loginpage extends AppCompatActivity {
    EditText email,passw;
Button loginbtn,signupbtn;
FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage2);
        signupbtn=findViewById(R.id.singup1);
        email=findViewById(R.id.email1);
        passw=findViewById(R.id.password1);
        loginbtn=findViewById(R.id.login1);
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String semail=email.getText().toString().trim();
                String spassw=passw.getText().toString().trim();
                if(!(semail.isEmpty()||spassw.isEmpty()))
                {
                    //dialog box

                     auth.signInWithEmailAndPassword(semail, spassw).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                         @Override
                         public void onSuccess(AuthResult authResult) {
                             Toast.makeText(Loginpage.this, "Login successfuly", Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(Loginpage.this,Homepage.class));
                             finish();
                         }
                     }).addOnFailureListener(new OnFailureListener() {
                         @Override
                         public void onFailure(@NonNull Exception e) {

                             Toast.makeText(Loginpage.this, "invalid details"+e.getMessage(), Toast.LENGTH_SHORT).show();
                         }
                     });
                }
                else
                {
                    Toast.makeText(Loginpage.this, "empty details", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Loginpage.this,singuppage.class));
            }
        });

    }
}