package com.example.thedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class category_details extends AppCompatActivity {
    ArrayList<PSmodel> psListdata=new ArrayList<>();
    String strcat;
    RecyclerView recyclerView;
    D_adpter dadpter;
    ImageView backbtn1;
    public category_details() {

    }

    @Override
    protected void onStart() {
        super.onStart();
        backbtn1.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_details);
        Intent intent1=getIntent();
        strcat= intent1.getStringExtra("catss");
        recyclerView=findViewById(R.id.recyclevv1);
        backbtn1=findViewById(R.id.bkbk2);

        FirebaseDatabase.getInstance().getReference("Supplier").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot sid:snapshot.getChildren())
                {
                    for (DataSnapshot snapshot1: sid.getChildren())
                    {

                        if(snapshot1.getKey().toString().equals("Productdetails"))
                        {
                            if(snapshot1.hasChildren())
                            {
                                for(DataSnapshot prod:snapshot1.getChildren())
                                {
                                    if(prod.child("type").getValue().toString().equals(strcat))
                                    {
                                        String uniqueid = prod.getKey().toString();
                                        String s_id = sid.getKey().toString();
                                        String sname=sid.child("supplierdetails").child("name").getValue().toString();
                                        String semail=sid.child("supplierdetails").child("email").getValue().toString();
                                        String name = prod.child("name").getValue().toString();
                                        String img = prod.child("imgurl").getValue().toString();
                                        String price = prod.child("price").getValue().toString();
                                        String deliv = prod.child("delivery").getValue().toString();
                                        String descp = prod.child("desc").getValue().toString();
                                        String type = prod.child("type").getValue().toString();
                                        PSmodel psmodel=new PSmodel(img,name, price,deliv,descp,type,uniqueid,s_id,sname,semail);
                                        psListdata.add(psmodel);
                                        dadpter.notifyDataSetChanged();

                                  }

                                }
                            }
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        dadpter=new D_adpter(psListdata,category_details.this);
        recyclerView.setAdapter(dadpter);
    }


}
class D_adpter extends RecyclerView.Adapter<D_adpter.ViewHolder>
{
    ArrayList<PSmodel> pmodelArrayList;
Context context;

    public D_adpter(ArrayList<PSmodel> pmodelArrayList, Context context) {
        this.pmodelArrayList = pmodelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.designprodeuctlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(pmodelArrayList.get(position).getImg()).into(holder.iv);
        holder.title.setText(pmodelArrayList.get(position).getPname());
        holder.ppp.setText("\u20B9 "+pmodelArrayList.get(position).getPrice());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(context,Product_fullview.class);
                intent1.putExtra("i_img",pmodelArrayList.get(holder.getAdapterPosition()).getImg());
                intent1.putExtra("i_name",pmodelArrayList.get(holder.getAdapterPosition()).getPname());
                intent1.putExtra("i_prc",pmodelArrayList.get(holder.getAdapterPosition()).getPrice());
                intent1.putExtra("i_desc",pmodelArrayList.get(holder.getAdapterPosition()).getDescp());
                intent1.putExtra("i_deliv",pmodelArrayList.get(holder.getAdapterPosition()).getDeliv());
                intent1.putExtra("i_pid",pmodelArrayList.get(holder.getAdapterPosition()).getUniqueid());
                intent1.putExtra("i_sid",pmodelArrayList.get(holder.getAdapterPosition()).getS_id());
                intent1.putExtra("i_sname",pmodelArrayList.get(holder.getAdapterPosition()).getSname());
                intent1.putExtra("i_smail",pmodelArrayList.get(holder.getAdapterPosition()).getSemail());
                context.startActivity(intent1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return pmodelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView title,ppp;
        RelativeLayout rl;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.al_recy1);
            title=itemView.findViewById(R.id.al_name);
            ppp=itemView.findViewById(R.id.al_prc);
            rl=itemView.findViewById(R.id.rel123);
        }
    }
}