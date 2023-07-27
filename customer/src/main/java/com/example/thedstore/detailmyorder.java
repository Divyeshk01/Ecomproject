package com.example.thedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class detailmyorder extends AppCompatActivity {
TextView oid,p_name,p_prc,p_id,qtt,tot_pay,p_typp,tdate,ttime;
ImageView iv1,backarraow1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmyorder);
        oid=findViewById(R.id.oid1);
        p_name=findViewById(R.id.pnm1);
        p_prc=findViewById(R.id.prc12);
        p_id=findViewById(R.id.pid1);
        qtt=findViewById(R.id.quntity1);
        tot_pay=findViewById(R.id.tpaym1);
        p_typp=findViewById(R.id.paytype123);
        tdate=findViewById(R.id.dt123);
        ttime=findViewById(R.id.time123);

        iv1=findViewById(R.id.ppic1);
        backarraow1=findViewById(R.id.bk123);
        Bundle bundle=getIntent().getExtras();
        String odr_id= bundle.getString("oid");
        String pname= bundle.getString("pname");
        String price= bundle.getString("price");
        String pid= bundle.getString("pid");
        String quntty= bundle.getString("qty");
        String total= bundle.getString("total");
        String type= bundle.getString("type");
        String date= bundle.getString("date");
        String time= bundle.getString("time");
        String sid= bundle.getString("sid");
        String imagess= bundle.getString("imgurl");

        oid.setText("Order Id: "+odr_id);
        p_name.setText(pname);
        p_prc.setText("\u20B9 "+price);
        p_id.setText("ProductId: "+pid);
        qtt.setText("Qty: "+quntty);
        tot_pay.setText("Total Payment: "+total);
        p_typp.setText("Payment Methods: "+type);
        tdate.setText("Order Date: "+date);
        ttime.setText("Order Time: "+time);
        Glide.with(getApplicationContext()).load(imagess).into(iv1);



        backarraow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}