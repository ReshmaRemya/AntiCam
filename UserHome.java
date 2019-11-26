package com.example.anticam.User;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import com.example.anticam.R;
import com.example.anticam.login;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.Html;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.widget.Toast;

public class UserHome extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.setTitle(Html.fromHtml("<h2><font family= 'comic sans ms' color='#FFFFFFFF'>Anti Cam</font></h2>"));
//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        fragment=new Spot_list();
        loadFragment(fragment);


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
        navigationView.setItemTextColor(ColorStateList.valueOf(Color.parseColor("#3F51B5")));

    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Toast.makeText(getApplicationContext(), "Please Log Out", Toast.LENGTH_SHORT).show();
            //super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.user_action_logout) {

            Intent intent = new Intent(getApplicationContext(), login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("EXIT", true);
            startActivity(intent);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new Bookings()).commit();
        if (id == R.id.user_nav_spotlist) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Spot_list()).commit();
        } else if (id == R.id.user_nav_search_by_district) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Search_ByDistrict()).commit();
        } else if (id == R.id.user_nav_spot_near_me) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Spot_NearMe()).commit();
            //startActivity(new Intent(getApplicationContext(),SpotNearMe.class));
        } else if (id == R.id.user_nav_link_bank) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new LinkBanks()).commit();
        } else if (id == R.id.user_nav_add_feedback) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Send_Feedback()).commit();
        } else if (id == R.id.user_nav_add_complaint) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Add_Complaint()).commit();
        } else if (id == R.id.user_nav_booking) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new Bookings()).commit();
        }else if (id == R.id.user_nav_get_complaint_reply) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ComplaintReply()).commit();
        }else if (id == R.id.nav_manual_cam_block) {
           startActivity(new Intent(getApplicationContext(),ManualCamBlock.class ));
        }



        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
