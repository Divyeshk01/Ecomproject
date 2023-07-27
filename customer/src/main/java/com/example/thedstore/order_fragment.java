package com.example.thedstore;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class order_fragment extends Fragment {
FirebaseAuth auth=FirebaseAuth.getInstance();
    RecyclerView recyclerView;
    Mygetadp adp;
    ArrayList<Mycustorder> mycustorderArrayList=new ArrayList<>();
    ArrayList<Of_model> productlist=new ArrayList<>();
    int cc;
    TextView tno;
    LottieAnimationView lottie_no;
    public order_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String userid=auth.getUid();
        View view=inflater.inflate(R.layout.fragment_order_fragment,container,false);
        recyclerView=view.findViewById(R.id.recyclev123);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lottie_no=view.findViewById(R.id.lotti_no_order1);
        tno=view.findViewById(R.id.txtNo1);
        FirebaseDatabase.getInstance().getReference("user").child(userid).child("myorder").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                for (DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String orderid = snapshot1.getKey().toString();
                    String sid = snapshot1.child("s_uid").getValue().toString();
                    String totpaym = snapshot1.child("totalpay").getValue().toString();
                    String qty = snapshot1.child("qty").getValue().toString();
                    String pid = snapshot1.child("pro_id").getValue().toString();
                    String ptype = snapshot1.child("paymenttype").getValue().toString();
                    String time = snapshot1.child("ctime").getValue().toString();
                    String date = snapshot1.child("cdate").getValue().toString();
                    cc = (int) snapshot.getChildrenCount();



                    FirebaseDatabase.getInstance().getReference("Supplier").child(sid).child("Productdetails").child(pid).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot shot) {

                            Of_model of_model = shot.getValue(Of_model.class);
                            productlist.add(of_model);
                            mycustorderArrayList.add(new Mycustorder(pid, sid, totpaym, qty, ptype, date, time, orderid));
                            adp.notifyDataSetChanged();

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
                else
                {
                    lottie_no.setVisibility(View.VISIBLE);
                    tno.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        adp=new Mygetadp(getContext(),mycustorderArrayList,productlist);
        recyclerView.setAdapter(adp);


        return view;
    }
}
//adapter class
class Mygetadp extends RecyclerView.Adapter<Mygetadp.ViewHolder>
{
    Context  context;
    ArrayList<Mycustorder> arrayListformyorder;
    ArrayList<Of_model> productarraylist;

    public Mygetadp(Context context, ArrayList<Mycustorder> arrayListformyorder, ArrayList<Of_model> productarraylist) {
        this.context = context;
        this.arrayListformyorder = arrayListformyorder;
        this.productarraylist = productarraylist;
    }

    @NonNull
    @Override
    public Mygetadp.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view=LayoutInflater.from(context).inflate(R.layout.designrecycle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Mygetadp.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        holder.pname.setText(productarraylist.get(position).getName());
        holder.q1.setText("Qty: "+arrayListformyorder.get(position).getQty());
        Glide.with(context).load(productarraylist.get(position).getImgurl()).into(holder.orderimg);
        holder.calview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,detailmyorder.class);
                intent.putExtra("sid",arrayListformyorder.get(position).getSid());
                intent.putExtra("pid",arrayListformyorder.get(position).getPid());
                intent.putExtra("total",arrayListformyorder.get(position).getPaym());
                intent.putExtra("qty",arrayListformyorder.get(position).getQty());
                intent.putExtra("type",arrayListformyorder.get(position).getPtype());
                intent.putExtra("date",arrayListformyorder.get(position).getDate());
                intent.putExtra("time",arrayListformyorder.get(position).getTime());
                intent.putExtra("oid",arrayListformyorder.get(position).getOrderid());
                intent.putExtra("imgurl",productarraylist.get(position).getImgurl());
                intent.putExtra("pname",productarraylist.get(position).getName());
                intent.putExtra("price",productarraylist.get(position).getPrice());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayListformyorder.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
    TextView pname,q1;
    ImageView orderimg;
    RelativeLayout calview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            pname=itemView.findViewById(R.id.tname1);
            q1=itemView.findViewById(R.id.qty3);
            orderimg=itemView.findViewById(R.id.img123);
            calview=itemView.findViewById(R.id.consv1);


        }
    }
}