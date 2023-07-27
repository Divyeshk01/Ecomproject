package com.example.thedstore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Product_fullview extends AppCompatActivity {
TextView pro_name,pro_prc,pro_deliv,pro_desc,uniqe;
TextView sname,semail;
ImageView full_img,bkarrow;
Button ordernow_btn,addtocart_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_fullview);
        pro_name=findViewById(R.id.titp1);
        pro_prc=findViewById(R.id.fprc1);
        pro_deliv=findViewById(R.id.fdeliv1);
        pro_desc=findViewById(R.id.fdesc1);
        full_img=findViewById(R.id.fullimg1);
        sname=findViewById(R.id.sn1);
        semail=findViewById(R.id.sadd1);
        uniqe=findViewById(R.id.id123);
        bkarrow=findViewById(R.id.backarrow1);
        ordernow_btn=findViewById(R.id.ordernow1);
        bkarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

            Bundle bundle=getIntent().getExtras();
            String odimg=bundle.getString("i_img");
            String odname=bundle.getString("i_name");
            String odprc=bundle.getString("i_prc");
            String oddeliv=bundle.getString("i_deliv");
            String oddesc=bundle.getString("i_desc");
            String odsname=bundle.getString("i_sname");
            String odsemail=bundle.getString("i_smail");
            String odpid=bundle.getString("i_pid");
            String odsid=bundle.getString("i_sid");

        Glide.with(getApplicationContext()).load(odimg).into(full_img);
            pro_name.setText(odname);
            pro_prc.setText("\u20B9"+odprc);
            pro_deliv.setText("delivery charge: "+oddeliv);
            pro_desc.setText(oddesc);
            sname.setText(odsname);
            semail.setText(odsemail);
            uniqe.setText("Unique Id: "+odpid);





        ordernow_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Product_fullview.this,Orderpage.class);
                intent.putExtra("oimg", odimg);
                intent.putExtra("oname", odname);
                intent.putExtra("oprc", odprc);
                intent.putExtra("osemail", odsemail);
                intent.putExtra("osname",odsname);
                intent.putExtra("opid",odpid);
                intent.putExtra("osid",odsid);

                startActivity(intent);

            }
        });

    }
}
