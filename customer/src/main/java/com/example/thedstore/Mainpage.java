package com.example.thedstore;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;


public class Mainpage extends Fragment {
RecyclerView recyclerView;
ArrayList<Pmodel> Listdata=new ArrayList<>();
ArrayList<sellerdetails> sellerlist=new ArrayList<>();
DatabaseReference databaseReference;
ArrayList<SlideModel> imageList=new ArrayList<>();
ImageSlider imageSlider;
    MyAdp myAdapter;
    public Mainpage() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         View view=inflater.inflate(R.layout.fragment_mainpage,container,false);
         recyclerView=view.findViewById(R.id.rcy1);
         imageSlider=view.findViewById(R.id.isview1);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        myAdapter=new MyAdp(Listdata,sellerlist,getContext());
        recyclerView.setAdapter(myAdapter);
        imageList.add(new SlideModel(R.drawable.comtocart,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.fashionimg,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.offf2,ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.adsinommerce,ScaleTypes.FIT));
        imageSlider.setImageList(imageList);
        databaseReference = FirebaseDatabase.getInstance().getReference("Supplier");
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setCancelable(false);
        builder.setView(R.layout.loading_event);
        AlertDialog alertDialog=builder.create();
        alertDialog.show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
               Listdata.clear();
                for (DataSnapshot uid:snapshot.getChildren())
                {
                    if(uid.hasChildren())
                    {
                        String kname=uid.child("supplierdetails").child("name").getValue().toString();
                        String kemail=uid.child("supplierdetails").child("email").getValue().toString();

                        for (DataSnapshot ps :uid.getChildren())
                        {
                                if(ps.hasChildren())
                                {

                                    for (DataSnapshot item:ps.getChildren())
                                    {
                                        if (ps.getKey().toString().equals("Productdetails")) {
                                            String uniqueid = item.getKey().toString();
                                            String sid = uid.getKey().toString();
                                            String name = item.child("name").getValue().toString();
                                            String img = item.child("imgurl").getValue().toString();
                                            String price = item.child("price").getValue().toString();
                                            String deliv = item.child("delivery").getValue().toString();
                                            String descp = item.child("desc").getValue().toString();
                                            String type = item.child("type").getValue().toString();
                                            Pmodel pmodel = new Pmodel(img, name, price, deliv, descp, type, uniqueid, sid);
                                            Listdata.add(pmodel);
                                            sellerlist.add(new sellerdetails(kname, kemail));
                                            myAdapter.notifyDataSetChanged();
                                        }
                                    }

                                }




                        }

                    }



                }


                alertDialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                    alertDialog.dismiss();
            }
        });


        
        return view;
    }


}

