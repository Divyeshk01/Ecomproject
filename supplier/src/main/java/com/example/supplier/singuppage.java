package com.example.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
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

public class singuppage extends AppCompatActivity {
EditText sname,smail,saddress,sph,spass1,spass2;
Button signupbtn;
FirebaseAuth auth=FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singuppage);
        sname=findViewById(R.id.rname1);
        smail=findViewById(R.id.remail1);
        saddress=findViewById(R.id.raddrs1);
        sph=findViewById(R.id.rph1);
        spass1=findViewById(R.id.rpassword1);
        spass2=findViewById(R.id.rpassword2);
        signupbtn=findViewById(R.id.rsignup1);
        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s_name=sname.getText().toString().trim();
                String s_mail=smail.getText().toString().trim();
                String s_addrs=saddress.getText().toString().trim();
                String s_ph=sph.getText().toString().trim();
                String s_pass1=spass1.getText().toString().trim();
                String s_pass2=spass2.getText().toString().trim();

                if(!(s_name.isEmpty()||s_mail.isEmpty()||s_addrs.isEmpty()||s_ph.isEmpty()||s_pass1.isEmpty()||s_pass2.isEmpty()))
                {
                    if(s_ph.length() == 10)
                    {sph.setError(null);
                        if(s_pass1.equals(s_pass2))
                        {spass2.setError(null);
                            //dialog box
                            Dialog dialog=new Dialog(singuppage.this);
                            dialog.setContentView(R.layout.loading_event);
                            dialog.create();
                            dialog.show();
                            auth.createUserWithEmailAndPassword(s_mail,s_pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful())
                                    {
                                        String uid=auth.getUid();
                                        Sdetail sdetail=new Sdetail(s_name,s_mail,s_addrs,s_ph, s_pass1);

                                       FirebaseDatabase.getInstance().getReference("Supplier").child(uid).child("supplierdetails").setValue(sdetail).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               if (task.isSuccessful())
                                               {
                                                   Toast.makeText(singuppage.this, "sign up succesfully", Toast.LENGTH_SHORT).show();
                                                   finish();
                                               }

                                           }
                                       });


                                    }
                                    else
                                    {

                                        Toast.makeText(singuppage.this, "signup failed"+task.getException().toString(), Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }
                            });

                        }
                        else
                        {
                            spass2.setError("password must be same");
                        }
                    }
                    else
                    {
                        sph.setError("mobile number should be 10 digit");
                    }
                }
                else
                {
                    Toast.makeText(singuppage.this, "empty details", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}