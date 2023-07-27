package com.example.thedstore;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class categrory_fragment extends Fragment {
LinearLayout fashion,electronic,toy,homekichen,decoration,beauty;
String stype;
    public categrory_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_categrory_fragment,container,false);
        fashion=view.findViewById(R.id.fashion1);
        electronic=view.findViewById(R.id.electro1);
        toy=view.findViewById(R.id.toys1);
        homekichen=view.findViewById(R.id.homek1);
        decoration=view.findViewById(R.id.decor1);
        beauty=view.findViewById(R.id.beuty1);


        fashion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                senddata("Fashion");
            }
        });
        electronic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               stype="Electronics";
                senddata(stype);
            }
        });
        toy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              stype="Toys";
                senddata(stype);
            }
        });
        homekichen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stype="Home & Kitchen";
                senddata(stype);
            }
        });
        beauty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stype="Beauty";
                senddata(stype);
            }
        });
        decoration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stype="Decoration";
                senddata(stype);
            }
        });
        return view;
    }
    public void senddata(String stype)
    {
        Intent intent=new Intent(getActivity(),category_details.class);
        intent.putExtra("catss",stype);
        startActivity(intent);
    }
}