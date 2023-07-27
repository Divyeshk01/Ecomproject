package com.example.supplier;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Homepage extends AppCompatActivity {
Button product_add,customer_order;
RecyclerView recyclerView;
FirebaseAuth auth=FirebaseAuth.getInstance();
ArrayList<Pro_model> arrayList=new ArrayList<>();
ArrayList<String> strpid=new ArrayList<>();
SADP myadp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        String muser=auth.getUid();
        product_add=findViewById(R.id.addpro1);
        customer_order=findViewById(R.id.cust_order1);
        recyclerView=findViewById(R.id.see_rcy1);
        product_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Homepage.this,Product_addpage.class));
            }
        });
        customer_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            startActivity(new Intent(Homepage.this,Order_cust_details.class));
            }
        });
        FirebaseDatabase.getInstance().getReference("Supplier").child(muser).child("Productdetails").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot shot:snapshot.getChildren())
                {
                  String pid=shot.getKey().toString();
                 Pro_model pro_model=shot.getValue(Pro_model.class);
                 strpid.add(pid);
                  arrayList.add(pro_model);
                  myadp.notifyDataSetChanged();



                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        myadp=new SADP(arrayList,strpid,this);
        recyclerView.setAdapter(myadp);

    }
}
class SADP extends RecyclerView.Adapter<SADP.ViewHolder>
{
ArrayList<Pro_model> arrayList;
ArrayList<String> strings;
Context context;

    public SADP(ArrayList<Pro_model> arrayList,ArrayList<String> strings ,Context context) {
        this.arrayList = arrayList;
        this.strings=strings;
        this.context = context;
    }

    @NonNull
    @Override
    public SADP.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view= LayoutInflater.from(context).inflate(R.layout.supplier_product_about,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SADP.ViewHolder holder, int position) {
        Glide.with(context).load(arrayList.get(position).getImgurl()).into(holder.iv);
        holder.t1.setText(arrayList.get(position).getName());
        holder.lview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Supplier_seeProdect.class);
                intent.putExtra("deliv",arrayList.get(position).getDelivery());
                intent.putExtra("img",arrayList.get(position).getImgurl());
                intent.putExtra("name",arrayList.get(position).getName());
                intent.putExtra("prc",arrayList.get(position).getPrice());
                intent.putExtra("type",arrayList.get(position).getType());
                intent.putExtra("desc",arrayList.get(position).getDesc());
                intent.putExtra("pid",strings.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView t1;
        LinearLayout lview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iview1);
            t1=itemView.findViewById(R.id.txtname1);
            lview=itemView.findViewById(R.id.layoutid1);
        }
    }
}