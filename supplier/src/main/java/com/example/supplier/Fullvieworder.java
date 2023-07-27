package com.example.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Fullvieworder extends AppCompatActivity {
ImageView image;
TextView pnm,prc,pid,qty,total,ptype,cnamev,cmail,caddress;
FirebaseAuth auth=FirebaseAuth.getInstance();
String mauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullvieworder);
        image=findViewById(R.id.ppic1);
        pnm=findViewById(R.id.pnm1);
        prc=findViewById(R.id.prc12);
        pid=findViewById(R.id.pid1);
        qty=findViewById(R.id.quntity1);
        total=findViewById(R.id.tpaym1);
        ptype=findViewById(R.id.paytype123);
        cnamev=findViewById(R.id.cname1);
        cmail=findViewById(R.id.email123);
        caddress=findViewById(R.id.addr1);
       mauth= auth.getUid().toString();
        Bundle bundle=getIntent().getExtras();
        String totpay= bundle.getString("paym");
        String qttt= bundle.getString("qty");
        String typepay= bundle.getString("type");
        String Spid= bundle.getString("pid");
        String cid= bundle.getString("cid");

        total.setText("Total Payment: \u20B9 "+totpay);
        qty.setText("Qty: "+qttt);
        ptype.setText("Payment Type: "+typepay);
        pid.setText("pid: "+Spid);
        FirebaseDatabase.getInstance().getReference("user").child(cid).child("userdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cname=snapshot.child("name").getValue().toString();
                String cemail=snapshot.child("email").getValue().toString();
                String cadd=snapshot.child("address").getValue().toString();
                cnamev.setText("Name: "+cname);
                cmail.setText("Email: "+cemail);
                caddress.setText("Address: "+cadd);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        FirebaseDatabase.getInstance().getReference("Supplier").child(mauth).child("Productdetails").child(Spid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String simgu=snapshot.child("imgurl").getValue().toString();
                String sname=snapshot.child("name").getValue().toString();
                String sprice=snapshot.child("price").getValue().toString();
                Glide.with(getApplicationContext()).load(simgu).into(image);
                pnm.setText(sname);
                prc.setText("\u20B9 "+sprice);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}