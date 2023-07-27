package com.example.thedstore;

import static android.app.Activity.RESULT_OK;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.transition.Visibility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

public class account_fragment extends Fragment {

FirebaseAuth auth=FirebaseAuth.getInstance();
String cur_uid;
EditText uname,uemail,udeliv;
ShapeableImageView imageView;
Button edits,svbtn;
String unamess,uemailss,uaddress,prpic,getpiclink;
TextView tedit;
    Uri uri;
    public account_fragment() {

        cur_uid= auth.getUid();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_account_fragment,container,false);
        uname=view.findViewById(R.id.aname1);
        uemail=view.findViewById(R.id.aemail1);
        udeliv=view.findViewById(R.id.daddress1);
        edits=view.findViewById(R.id.editbtn1);
        imageView=view.findViewById(R.id.propic1);
        svbtn=view.findViewById(R.id.Save1);
        tedit=view.findViewById(R.id.tedit1);
        FirebaseDatabase.getInstance().getReference("user").child(cur_uid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("profilepic"))
                {
                    getpiclink=snapshot.child("profilepic").getValue().toString();
                    Glide.with(getContext()).load(getpiclink).into(imageView);
                }

               unamess=snapshot.child("userdetails").child("name").getValue().toString();
                 uemailss=snapshot.child("userdetails").child("email").getValue().toString();
                 uaddress=snapshot.child("userdetails").child("address").getValue().toString();

                uname.setText(unamess);
                uemail.setText(uemailss);
                udeliv.setText(uaddress);
                uname.setEnabled(false);
                uemail.setEnabled(false);
                udeliv.setEnabled(false);
                imageView.setEnabled(false);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        ActivityResultLauncher<Intent> activityResultLauncher=registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode()==Activity.RESULT_OK)
                        {
                            Intent dt=result.getData();
                            uri=dt.getData();
                            FirebaseStorage.getInstance().getReference("user").child(cur_uid).child("profilepic").putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    Task<Uri> task=taskSnapshot.getStorage().getDownloadUrl();
                                    while (!(task.isComplete()));
                                    Uri unik=task.getResult();
                                    prpic=unik.toString();
                                    picprofile();


                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(getContext(), "try Again", Toast.LENGTH_SHORT).show();
                                }
                            });



                        }
                        else
                        {
                            Toast.makeText(getContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                activityResultLauncher.launch(intent);
            }
        });
        edits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uname.setEnabled(true);
                udeliv.setEnabled(true);
                imageView.setEnabled(true);
                edits.setVisibility(View.INVISIBLE);
                svbtn.setVisibility(View.VISIBLE);
                tedit.setVisibility(VISIBLE);

            }
        });
        svbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String up_name =uname.getText().toString();
               String up_address=udeliv.getText().toString();
               DatabaseReference ref= FirebaseDatabase.getInstance().getReference("user").child(cur_uid).child("userdetails");
              if(!(up_name.isEmpty()||up_address.isEmpty()))
              {
                  ref.child("name").setValue(up_name).addOnCompleteListener(new OnCompleteListener<Void>() {
                      @Override
                      public void onComplete(@NonNull Task<Void> task) {
                          if (task.isComplete())
                          {
                              ref.child("address").setValue(up_address);
                              Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                              edits.setVisibility(View.VISIBLE);
                              svbtn.setVisibility(View.INVISIBLE);
                              tedit.setVisibility(INVISIBLE);
                              uname.setEnabled(false);
                              udeliv.setEnabled(false);
                              imageView.setEnabled(false);

                          }
                          else
                          {
                              Toast.makeText(getContext(), "try agian", Toast.LENGTH_SHORT).show();
                          }

                      }
                  });
              }
              else
              {
                  Toast.makeText(getContext(), "Data Required", Toast.LENGTH_SHORT).show();
              }

            }
        });

        return view;
    }
    public void picprofile()
    {
        FirebaseDatabase.getInstance().getReference("user").child(cur_uid).child("profilepic").setValue(prpic).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
            if (task.isComplete())
            {
                imageView.setImageURI(uri);
                Toast.makeText(getContext(), "set picture successfully  ", Toast.LENGTH_SHORT).show();
            }else
            {
                Toast.makeText(getContext(), "try again", Toast.LENGTH_SHORT).show();
            }
            }
        });
    }
}