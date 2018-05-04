package com.example.fariahuq.tori;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class PreDataCollection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_pre_data_collection);
        Spinner age = findViewById(R.id.age);
        String[] ages = new String[]{"15", "16", "17" , "18" , "19" , "20" , "21" , "22" , "23" , "24" , "25" , "26"};
        ArrayAdapter<String> ageadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, ages);
        age.setAdapter(ageadapter);

        Spinner gender = findViewById(R.id.gender);
        String[] genders = new String[]{"Male", "Female", "Other"};
        ArrayAdapter<String> genderadapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        gender.setAdapter(genderadapter);

        Spinner level = findViewById(R.id.level);
        String[] levels = new String[]{"Intermediate", "UnderGraduate", "PostGraduate"};
        ArrayAdapter<String> leveladapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, levels);
        level.setAdapter(leveladapter);


    }


    public void Onclick(View view)
    {
        super.onBackPressed();
        finish();
    }

}
