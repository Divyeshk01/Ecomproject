package com.example.thedstore;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class MyAdp extends RecyclerView.Adapter<MyAdp.ViewHolder>{
    ArrayList<Pmodel> d_Listdata;
    ArrayList<sellerdetails>  sellerlist;
    Context context;

    public MyAdp(ArrayList<Pmodel> d_Listdata, ArrayList<sellerdetails> sellerlist, Context context) {
        this.d_Listdata = d_Listdata;
        this.sellerlist=sellerlist;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view =LayoutInflater.from(context).inflate(R.layout.look_rcycle,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Glide.with(context).load(d_Listdata.get(position).getP_img()).into(holder.imageView_v);
        holder.name_v.setText(d_Listdata.get(position).getP_name());
        holder.prc_v.setText("\u20B9"+d_Listdata.get(position).getP_prc());
        holder.deli_v.setText(" delivery charge: "+d_Listdata.get(position).getP_delivery());
        holder.viewclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Product_fullview.class);
                intent.putExtra("i_img",d_Listdata.get(holder.getAdapterPosition()).getP_img());
                intent.putExtra("i_name",d_Listdata.get(holder.getAdapterPosition()).getP_name());
                intent.putExtra("i_prc",d_Listdata.get(holder.getAdapterPosition()).getP_prc());
                intent.putExtra("i_desc",d_Listdata.get(holder.getAdapterPosition()).getP_desc());
                intent.putExtra("i_deliv",d_Listdata.get(holder.getAdapterPosition()).getP_delivery());
                intent.putExtra("i_pid",d_Listdata.get(holder.getAdapterPosition()).getPid());
                intent.putExtra("i_sid",d_Listdata.get(holder.getAdapterPosition()).getSid());
                intent.putExtra("i_sname",sellerlist.get(holder.getAdapterPosition()).getSname());
                intent.putExtra("i_smail",sellerlist.get(holder.getAdapterPosition()).getSemail());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return
                d_Listdata.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView_v;
        TextView name_v,prc_v,deli_v;
        View viewclick;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            imageView_v=itemView.findViewById(R.id.l_rcv_img1);
            name_v=itemView.findViewById(R.id.l_rcv_name);
            prc_v=itemView.findViewById(R.id.l_rcv_prc);
            deli_v=itemView.findViewById(R.id.l_rcv_deli1);
            viewclick=itemView.findViewById(R.id.rcv_click);
        }
    }
}


