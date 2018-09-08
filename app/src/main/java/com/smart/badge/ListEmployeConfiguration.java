package com.smart.badge;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;

import adapters.Profile_VAdapter;
import adapters.entity.Eleve;
import fragments.EmployeConfiguration;
import fragments.MainTabsFragments;
import fragments.Satistique;

public class ListEmployeConfiguration extends AppCompatActivity {

    private Profile_VAdapter profile_vAdapter;
    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    /*This one is for eusing the option: swipe down to refresh the list*/
    private SwipeRefreshLayout swipeRefreshLayout;


    //for animating a view
    private Animation fadein, fadeout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listemploye_configuration);

        Toolbar tb = findViewById(R.id.toolbar);
        tb.setTitle("Smart Badge");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));


        FragmentManager fragmentManager = getSupportFragmentManager();
        EmployeConfiguration fragment = new EmployeConfiguration();
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }


    public void pointer(View v)
    {
        startActivity(new Intent(ListEmployeConfiguration.this, NFCPointer.class));
    }


    public void identifier (View v)
    {
        startActivity(new Intent(ListEmployeConfiguration.this, NFCIdentification.class));
    }

    public void goDetailEleve(View v)
    {
        Eleve el = (Eleve) v.findViewById(R.id.detail).getTag();
        Intent intent = new Intent(ListEmployeConfiguration.this, NFCConfiguration.class);
        intent.putExtra("eleve", el);
        startActivity(intent);
    }


    public void goStatistiqueEmploye(View v){
        startActivity(new Intent(ListEmployeConfiguration.this, StatistiqueEmploye.class));
    }

    public void goStatistiquePointage(View v){
        startActivity(new Intent(ListEmployeConfiguration.this, StatistiquePointage.class));
    }

    public void configurer(View v){
        startActivity(new Intent(ListEmployeConfiguration.this, NFCConfiguration.class));
    }

    public void reset(View v){
        startActivity(new Intent(ListEmployeConfiguration.this, NFCReset.class));
    }
}
