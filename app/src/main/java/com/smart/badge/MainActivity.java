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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;

import adapters.Profile_VAdapter;

import adapters.entity.Employe;
import adapters.entity.User;
import fragments.MainTabsFragments;
import service.SessionCore;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

        Toolbar tb = findViewById(R.id.toolbar);
        User currentUser = SessionCore.getCurrentUser(this);
        tb.setTitle("Smart Badge[" + currentUser.type  +"]");
        tb.setTitleTextColor(Color.parseColor("#FFFFFF"));
        this.setSupportActionBar(tb);

        FragmentManager fragmentManager = getSupportFragmentManager();
        MainTabsFragments fragment = new MainTabsFragments();
        fragment.setUserType(currentUser.type);
        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }


    public void pointer(View v)
    {
        startActivity(new Intent(MainActivity.this, NFCPointer.class));
    }


    public void identifier (View v)
    {
        startActivity(new Intent(MainActivity.this, NFCIdentification.class));
    }

    public void goDetailEleve(View v)
    {
        View icon =  v.findViewById(R.id.detail);
        Employe el = (Employe) v.getTag();
        if(icon != null){
             el = (Employe) v.findViewById(R.id.detail).getTag();
        }
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("eleve", el);
        startActivity(intent);
    }


    public void goStatistiqueEmploye(View v){
        startActivity(new Intent(MainActivity.this, StatistiqueEmploye.class));
    }

    public void goStatistiquePointage(View v){
        startActivity(new Intent(MainActivity.this, StatistiquePointage.class));
    }

    public void configurer(View v){
        startActivity(new Intent(MainActivity.this, ListEmployeConfiguration.class));
    }

    public void GotoPagePaiement(View v){
        startActivity(new Intent(MainActivity.this, PagePaiement.class));
    }

    public void reset(View v){
        startActivity(new Intent(MainActivity.this, NFCReset.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.logout);
        final MainActivity activity = this;
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                activity.Logout();
                return false;
            }
        });
        return true;
    }

    public void Logout(){
        SessionCore.logout(this);
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
    }
}
