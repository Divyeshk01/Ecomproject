package com.example.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.airbnb.lottie.L;
import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Order_cust_details extends AppCompatActivity {
FirebaseAuth auth=FirebaseAuth.getInstance();
ArrayList<ordermodel> orderList=new ArrayList<ordermodel>();
RecyclerView recyclerView;
LottieAnimationView lottieAN;
ImageView arrobk;
    MyAdp_Order myorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_cust_details);
        String curr_uid= auth.getUid();
        arrobk=findViewById(R.id.backa1);
        recyclerView=findViewById(R.id.rcy11);
        lottieAN=findViewById(R.id.lottnoorder1);
        FirebaseDatabase.getInstance().getReference("Supplier").child(curr_uid).child("Orderdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              int  count= (int) snapshot.getChildrenCount();
                if(count==0)
                {
                    lottieAN.setVisibility(View.VISIBLE);

                }
               for(DataSnapshot snapshot1:snapshot.getChildren())
               {
                   String ord_id=snapshot1.getKey().toString();

                   String cid= snapshot1.child("customer_uid").getValue().toString();
                   String date= snapshot1.child("date").getValue().toString().replace("-","/");
                   String payment_type= snapshot1.child("payment_type").getValue().toString();
                   String pid= snapshot1.child("product_id").getValue().toString();
                  String qty= snapshot1.child("qty").getValue().toString();
                   String time= snapshot1.child("time").getValue().toString();
                   String totalpay= snapshot1.child("total_payment").getValue().toString();

                   ordermodel modle1=new ordermodel(pid, cid, qty,totalpay,payment_type,date,time,ord_id);
                   orderList.add(modle1);
                  myorder.notifyDataSetChanged();



               }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myorder=new MyAdp_Order(Order_cust_details.this,orderList);
        recyclerView.setAdapter(myorder);

        arrobk.setOnClickListener(view -> {
            finish();
        });





    }
}