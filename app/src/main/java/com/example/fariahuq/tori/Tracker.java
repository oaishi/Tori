package com.example.fariahuq.tori;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Tracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_tracker);
    }


    public void Onclick1(View view)
    {
        Toast.makeText(this, "Added 1h 23 min to Coding", Toast.LENGTH_LONG).show();
        super.onBackPressed();
        finish();
    }
    public void Onclick2(View view)
    {
        Toast.makeText(this, "Added 1h 23 min to Sleep", Toast.LENGTH_LONG).show();
        super.onBackPressed();
        finish();
    }
    public void Onclick3(View view)
    {
        Toast.makeText(this, "Added 1h 23 min to Family Time", Toast.LENGTH_LONG).show();
        super.onBackPressed();
        finish();
    }

    public void Onclick4(View view)
    {
        Toast.makeText(this, "Added 1h 23 min to Varsity", Toast.LENGTH_LONG).show();
        super.onBackPressed();
        finish();
    }

}
