package com.example.fariahuq.tori;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class HomeScreen extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    float timeactive = 0;
    private NotificationManager nm;
    int alarmMsgId = 1;

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

        ImageView imageView = (ImageView)findViewById(R.id.quote);
        int sendDelay = (new Random().nextInt(3) + 1);
        if(sendDelay==1)
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.images1));
        else if(sendDelay==2)
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.images2));
        else if(sendDelay==3)
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.images3));

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
            Intent intent = new Intent(this,PreDataCollection.class);
            startActivity(intent);
        }


        nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent notify = new Intent(this, Tracker.class);
        PendingIntent pi = PendingIntent.getActivity(this, (int)alarmMsgId, notify, 0);

        //@formatter:off
        Notification status = new NotificationCompat.Builder(this)
                .setContentTitle("Do you want to track your activities?")
                .setContentText("Tori")
                .setSmallIcon(R.drawable.tori)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setOngoing(true)
                .setDefaults(Notification.DEFAULT_LIGHTS)
                .build();
        //@formatter:on

        // Send the notification using the alarm id to easily identify the
        // correct notification.
        nm.notify((int)alarmMsgId, status);


        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setMessage("Do You Want To Chat With me ?")
                .setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // FIRE ZE MISSILES!
                Intent intent = new Intent(getApplicationContext(),Demo.class);
                startActivity(intent);
            }
        })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        // show it
        alertDialog.show();
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
            Intent intent = new Intent(this,SentimentTracking.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(this,Demo.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(this,Tracker.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (id == R.id.nav_send) {
            drawer.closeDrawer(GravityCompat.START);
        }
        return true;
    }
}
