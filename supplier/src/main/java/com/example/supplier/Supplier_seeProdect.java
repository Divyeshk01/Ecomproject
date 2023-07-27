package com.example.supplier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

public class Supplier_seeProdect extends AppCompatActivity {
ImageView imageView,arrow;
TextView pname,prc,desc,type,pid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supplier_see_prodect);
        imageView=findViewById(R.id.fullimg1);
        pname=findViewById(R.id.titp1);
        prc=findViewById(R.id.fprc1);
        desc=findViewById(R.id.fdesc1);
        type=findViewById(R.id.typeee1);
        pid=findViewById(R.id.id123);
        arrow=findViewById(R.id.backarrow1);
        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Bundle bundle=getIntent().getExtras();
        String descs= bundle.getString("desc");
        String types= bundle.getString("type");
        String price= bundle.getString("prc");
        String name= bundle.getString("name");
        String pids= bundle.getString("pid");
        String img=bundle.getString("img");

        Glide.with(this).load(img).into(imageView);
        pname.setText(name);
        prc.setText("\u20B9 "+price);
        desc.setText(descs);
        type.setText("Type Of Product: "+types);
        pid.setText("Pid: "+pids);
    }
}