package com.example.thedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ktx.Firebase;

public class RegisterActivity extends AppCompatActivity {
FirebaseAuth auth=FirebaseAuth.getInstance();;
EditText r_name,r_email,r_address,r_passw,r_passw2;
Button r_signupbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        r_name=findViewById(R.id.rname1);
        r_email=findViewById(R.id.remail1);
        r_address=findViewById(R.id.raddrs1);
        r_passw=findViewById(R.id.rpassword1);
        r_passw2=findViewById(R.id.rpassword2);
        r_signupbtn=findViewById(R.id.rsignup1);

        r_signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sname=r_name.getText().toString().trim();
                String semail=r_email.getText().toString().trim();
                String saddress=r_address.getText().toString().trim();
                String spassw=r_passw.getText().toString().trim();
                String spassw2=r_passw2.getText().toString().trim();

                if(!(sname.isEmpty()||semail.isEmpty()||saddress.isEmpty()||spassw.isEmpty()||spassw2.isEmpty()))
                {
                    if(spassw.equals(spassw2))
                    {
                        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                        builder.setCancelable(false);
                        builder.setView(R.layout.loading_event);
                        AlertDialog alertDialog=builder.create();
                        alertDialog.show();
                        auth.createUserWithEmailAndPassword(semail,spassw2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    String uid=auth.getUid();
                                    Datamodel datamodel=new Datamodel(sname,semail,saddress,spassw2);
                                   FirebaseDatabase.getInstance().getReference("user").child(uid).child("userdetails").setValue(datamodel).addOnSuccessListener(new OnSuccessListener<Void>() {
                                       @Override
                                       public void onSuccess(Void unused) {
                                           alertDialog.dismiss();
                                           Toast.makeText(RegisterActivity.this, "singup successfuly", Toast.LENGTH_SHORT).show();
                                           finish();
                                       }
                                   }).addOnFailureListener(new OnFailureListener() {
                                       @Override
                                       public void onFailure(@NonNull Exception e) {
                                           alertDialog.dismiss();
                                       }
                                   });

                                }
                                else
                                {
                                    Toast.makeText(RegisterActivity.this, "signup failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(RegisterActivity.this, "password match failed ", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "please fill details", Toast.LENGTH_SHORT).show();
                }




            }
        });
    }
}