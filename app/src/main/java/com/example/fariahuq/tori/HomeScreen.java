package com.example.fariahuq.tori;

import android.app.ActivityManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    float timeactive = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = activityManager.getRunningServices(Integer.MAX_VALUE);

        long currentMillis = Calendar.getInstance().getTimeInMillis();
        Calendar cal = Calendar.getInstance();


        for (ActivityManager.RunningServiceInfo info : services) {
            cal.setTimeInMillis(currentMillis-info.activeSince);

            if(info.process.contains("facebook")) {
                timeactive += info.activeSince;
                Log.i("Running", String.format("Process %s with component %s has been running since %s (%d milliseconds)",
                        info.process, info.service.getClassName(), cal.getTime().toString(), info.activeSince));
            }
        }
        boolean mboolean = false;
        SharedPreferences settings = getSharedPreferences("com.example.fariahuq.tori", 0);
        mboolean = settings.getBoolean("FIRST_RUN", false);
        if (!mboolean) {
            //SharedPreferences.Editor editor = settings.edit();
            //editor.putBoolean("FIRST_RUN", true);
            //editor.commit();
            Intent intent = new Intent(this,Demo.class);
            startActivity(intent);
        }
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            // Handle the camera action
            Intent intent = new Intent(this,ProductivityTracking.class);
            intent.putExtra("data",timeactive);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,Demo.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_share) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_send) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
