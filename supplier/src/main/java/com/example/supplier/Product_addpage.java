package com.example.supplier;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;

import java.util.Locale;
import java.util.UUID;

public class Product_addpage extends AppCompatActivity {
Button addproductbtn;
    Spinner sp;
    String item_id;
    String[] typelist={"None of these","Home & Kitchen","Fashion","Electronics","Toys","Beauty","Decoration"};
    ImageView pro_img;
    EditText pname,pprice,pdelivery,pdesc;
    Uri uri;
    FirebaseAuth auth=FirebaseAuth.getInstance();
    String cur_uid,str_img,str_name,str_prc,str_deliv,str_desc,str_type;
    int itemcount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_addpage);
        sp=findViewById(R.id.spiner1);
        pro_img=findViewById(R.id.pimg1);
        pname=findViewById(R.id.pname1);
        pprice=findViewById(R.id.pprc1);
        pdelivery=findViewById(R.id.pdelivery1);
        pdesc=findViewById(R.id.pdesc1);
        addproductbtn=findViewById(R.id.pro_addbtn1);
        ArrayAdapter adapter=new ArrayAdapter(this, android.R.layout.simple_list_item_checked, typelist);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(!(i ==0))
                {
                    str_type=typelist[i];
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
                        pro_img.setImageURI(uri);
                    }
                    else
                    {
                        Toast.makeText(Product_addpage.this, "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    );
    pro_img.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            activityResultLauncher.launch(intent);
        }
    });


        addproductbtn.setOnClickListener(new View.OnClickListener() {
          @Override
             public void onClick(View view) {
                 cur_uid=auth.getUid();
              str_name=pname.getText().toString().trim();
              str_prc=pprice.getText().toString().trim();
              str_deliv=pdelivery.getText().toString().trim();
              str_desc=pdesc.getText().toString().trim();
              if(!(str_name.isEmpty()||str_prc.isEmpty()||str_deliv.isEmpty()||str_desc.isEmpty()||str_type.isEmpty()))
              {
                  if(str_deliv.equals("free"))
                  {
                      item_id = UUID.randomUUID().toString().replace("-", "");

                      img_store();
                  }
                  else
                  {
                      Toast.makeText(Product_addpage.this, "delivery must be 'free'", Toast.LENGTH_SHORT).show();
                  }


              }
              else
              {
                  Toast.makeText(Product_addpage.this, "Empty details", Toast.LENGTH_SHORT).show();
              }



             }
         });



    }

    public  void img_store()
    {


        Dialog dialog =new Dialog(Product_addpage.this);
        dialog.setContentView(R.layout.loading_event);
        dialog.create();
        dialog.show();
        FirebaseStorage.getInstance().getReference("supplier storage").child(cur_uid).child("product image")
                .child(item_id).child(str_name).putFile(uri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                       Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                       while (!(uriTask.isComplete()));
                       Uri iuri=uriTask.getResult();
                      str_img=iuri.toString();
                      data_store();


                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(Product_addpage.this, "try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void data_store()
    {

        Pro_model p_data=new Pro_model(str_img, str_name,str_prc,str_deliv,str_desc,str_type);
        FirebaseDatabase.getInstance().getReference("Supplier").child(cur_uid).child("Productdetails")
                .child(item_id)
                .setValue(p_data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {

                            Toast.makeText(Product_addpage.this, "uploaded successfuly", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(Product_addpage.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}