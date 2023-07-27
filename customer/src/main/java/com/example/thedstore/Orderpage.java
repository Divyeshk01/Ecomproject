package com.example.thedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.EventListener;
import java.util.UUID;

public class Orderpage extends AppCompatActivity {
TextView Pname,Pprc,Sname,deliveryadd,countitem,prc,item,total;
ImageView Pimg,iplus,iminus,bkbk;
Button placeorderbtn;
FirebaseAuth auth=FirebaseAuth.getInstance();
String curuid,uname,uaddress,uemail,paymenttype="Cash On Delivery(COD)",tsum;
RadioGroup radioGroup;
LottieAnimationView lottieview;
int count=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderpage);
        Pname=findViewById(R.id.titp1);
        Pprc=findViewById(R.id.titprc1);
        Sname=findViewById(R.id.sold1);
        Pimg=findViewById(R.id.orderimg1);
        deliveryadd=findViewById(R.id.delivaddress1);
        iplus=findViewById(R.id.plus1);
        iminus=findViewById(R.id.mines1);
        countitem=findViewById(R.id.i1);
        prc=findViewById(R.id.price_od1);
        item=findViewById(R.id.item_od1);
        total=findViewById(R.id.tot_od1);
        bkbk=findViewById(R.id.backback2);
        radioGroup=findViewById(R.id.rdio1);
        placeorderbtn=findViewById(R.id.placeorder_button1);
        lottieview=findViewById(R.id.lott123);
        Bundle bundle=getIntent().getExtras();
        String oimg=bundle.getString("oimg");
        String oname=bundle.getString("oname");
        String oprc=bundle.getString("oprc");
        String osmail=bundle.getString("osmail");

        String sid=bundle.getString("osid");
        String pid=bundle.getString("opid");
        String osname=bundle.getString("osname");
        Pname.setText(oname);
        tsum=oprc;
        Pprc.setText("\u20B9 "+oprc);
        Sname.setText("Sold by : "+osname);
        Glide.with(Orderpage.this).load(oimg).into(Pimg);
        curuid=auth.getUid().toString();
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setView(R.layout.loading_event);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
       FirebaseDatabase.getInstance().getReference("user").child(curuid).child("userdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                uname= snapshot.child("name").getValue().toString();
                uaddress=snapshot.child("address").getValue().toString();
                uemail=snapshot.child("email").getValue().toString();
                deliveryadd.setText("   "+uaddress);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Orderpage.this, "try agin", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.dismiss();
        total.setText("\u20B9 "+oprc);
        prc.setText("+"+oprc);
        bkbk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        iplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count<=9){
                    count++;
                    countitem.setText(Integer.toString(count));
                    item.setText("x "+Integer.toString(count));
                     tsum = Integer.toString(count * Integer.parseInt(oprc));
                  total.setText("\u20B9 "+tsum);


                }
                else {
                    Toast.makeText(Orderpage.this, "maximum 10 item", Toast.LENGTH_SHORT).show();
                }

            }
        });
        iminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(count>1)
                {
                    count--;
                    countitem.setText(Integer.toString(count));
                    item.setText("x "+Integer.toString(count));
                    tsum = Integer.toString(count * Integer.parseInt(oprc));
                    total.setText("\u20B9 "+tsum);
                }
                else
                {
                    Toast.makeText(Orderpage.this, "minimum 1 item", Toast.LENGTH_SHORT).show();
                }

            }
        });
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int rid) {
                RadioButton rb=findViewById(rid);
                paymenttype=rb.getText().toString();
            }
        });
        placeorderbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(paymenttype.equals("Cash On Delivery(COD)"))
                {   AlertDialog.Builder builder=new AlertDialog.Builder(Orderpage.this);
                    builder.setCancelable(false);
                    builder.setView(R.layout.loading_event);
                    AlertDialog alertDialog=builder.create();
                    alertDialog.show();
                    Calendar calendar=Calendar.getInstance();
                    String cdate= new SimpleDateFormat("dd-MM-yyyy ").format(calendar.getTime());
                    String ctime= new SimpleDateFormat("hh:mm:ss").format(calendar.getTime());
                    ordermodel order1=new ordermodel(pid,curuid,count,tsum, paymenttype,cdate,ctime);
                    String orderid=UUID.randomUUID().toString().replace("-", "")+ctime;
                    FirebaseDatabase.getInstance().getReference("Supplier").child(sid).child("Orderdetails").child(orderid).setValue(order1).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            order_my myo=new order_my(pid,sid,count,tsum,paymenttype,cdate,ctime);
                            FirebaseDatabase.getInstance().getReference("user").child(curuid).child("myorder").child(orderid).setValue(myo).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    alertDialog.dismiss();
                                    lottieview.setVisibility(View.VISIBLE);
                                    Toast.makeText(Orderpage.this, "place order successfully", Toast.LENGTH_SHORT).show();

                                   new Handler().postDelayed(new Runnable() {
                                       @Override
                                       public void run() {
                                           finish();
                                       }
                                   }, 3000);
                                }
                            });

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            alertDialog.dismiss();
                            Toast.makeText(Orderpage.this, "Try Again", Toast.LENGTH_SHORT).show();
                        }
                    });


                }else
                {
                    Toast.makeText(Orderpage.this, "Only COD service available", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }
}
class order_my
{
    String pro_id;
    String s_uid;
    int qty;
    String totalpay;
    String paymenttype;
    String cdate;
    String ctime;

    public order_my(String pro_id, String s_uid, int qty, String totalpay, String paymenttype, String cdate, String ctime) {
        this.pro_id = pro_id;
        this.s_uid = s_uid;
        this.qty = qty;
        this.totalpay = totalpay;
        this.paymenttype = paymenttype;
        this.cdate = cdate;
        this.ctime = ctime;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getS_uid() {
        return s_uid;
    }

    public void setS_uid(String s_uid) {
        this.s_uid = s_uid;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getTotalpay() {
        return totalpay;
    }

    public void setTotalpay(String totalpay) {
        this.totalpay = totalpay;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }
}