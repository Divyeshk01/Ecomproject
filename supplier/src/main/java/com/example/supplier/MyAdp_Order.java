package com.example.supplier;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class MyAdp_Order  extends RecyclerView.Adapter<MyAdp_Order.ViewHolder>{
    Context context;
    ArrayList<ordermodel> Listordermodel;

    public MyAdp_Order(Context context, ArrayList<ordermodel> listordermodel) {
        this.context = context;
        Listordermodel = listordermodel;
    }


    @NonNull
    @Override
    public MyAdp_Order.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view=LayoutInflater.from(context).inflate(R.layout.order_look,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdp_Order.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.oid.setText("Order id: "+Listordermodel.get(position).getOrder_id());
        holder.payment.setText("Total Payment: "+Listordermodel.get(position).getPayment());
        holder.qty.setText("Qty: "+Listordermodel.get(position).getQty());
        holder.dates.setText(Listordermodel.get(position).getDate());
        holder.times.setText(Listordermodel.get(position).getTime());
        holder.rv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context,Fullvieworder.class);
                intent.putExtra("paym",Listordermodel.get(position).getPayment());
                intent.putExtra("type",Listordermodel.get(position).getType());
                intent.putExtra("qty",Listordermodel.get(position).getQty());
                intent.putExtra("pid",Listordermodel.get(position).getPid());
                intent.putExtra("cid",Listordermodel.get(position).getCid());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return Listordermodel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView oid,payment,qty,dates,times;
        RelativeLayout rv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            oid=itemView.findViewById(R.id.oid123);
            payment=itemView.findViewById(R.id.totp1);
            qty=itemView.findViewById(R.id.qt1);
            dates=itemView.findViewById(R.id.det1);
            times=itemView.findViewById(R.id.tim1);
            rv=itemView.findViewById(R.id.view123);
        }
    }
}
