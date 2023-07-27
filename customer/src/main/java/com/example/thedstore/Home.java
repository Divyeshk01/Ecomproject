package com.example.thedstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.StartupTime;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.security.PublicKey;

public class Home extends AppCompatActivity {
BottomNavigationView bottomNavigationView;
public NavigationView nview;
DrawerLayout dlayout;
Toolbar toolbar;
FirebaseAuth auth=FirebaseAuth.getInstance();
    FirebaseUser authCurrentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        bottomNavigationView=findViewById(R.id.bnav123);
        nview=findViewById(R.id.nview1);
        nview.bringToFront();
        dlayout=findViewById(R.id.dlay123);
        toolbar=findViewById(R.id.ttt123);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        frags_method(new Mainpage(),0);
        authCurrentUser = auth.getCurrentUser();
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,dlayout,toolbar,R.string.open_dr,R.string.close_dr);
       toggle.setDrawerSlideAnimationEnabled(true);
        dlayout.addDrawerListener(toggle);
        toggle.syncState();
        nview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int sid=item.getItemId();

                if(sid==R.id.con1)
                {
                    Toast.makeText(Home.this, "contact us click", Toast.LENGTH_SHORT).show();
                }
                else if(sid==R.id.logout1)
                {
                  auth.signOut();
                  startActivity(new Intent(Home.this,LoginActivity.class));
                  finish();

                }
                return true;
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();

                if(id==R.id.home1)
                {
                   frags_method(new Mainpage(),1);
                }
                else if(id==R.id.categry1)
                {
                    frags_method(new categrory_fragment(),1);
                }
                else if(id==R.id.order1)
                {
                    frags_method(new order_fragment(),1);
                }
                else{
                    frags_method(new account_fragment(),1);
                }

                return true;
            }
        });



    }
    public void frags_method(Fragment fragment,int n)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        if (n==0){
            ft.add(R.id.fram1,fragment);


        }
        else
        {
            ft.replace(R.id.fram1,fragment);
            ft.addToBackStack("dstack");

        }

        ft.commit();
    }



    public void aleretsss()
        {

            Dialog builder=new Dialog(this);

            builder.setCancelable(false);
            builder.setContentView(R.layout.dialog_exit);


            Button btnyes = builder.findViewById(R.id.e_yes);
            Button btnno=builder.findViewById(R.id.e_no);

            btnyes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            btnno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    builder.dismiss();
                }
            });
            builder.show();

        }


}