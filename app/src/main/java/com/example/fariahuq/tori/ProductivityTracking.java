package com.example.fariahuq.tori;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ProductivityTracking extends AppCompatActivity {

    float acitivityscale[] = {14f , 28f , 63f , 70f};
    String activityname[] = {"Coding" , "Others" , "Personal Space" , "Academic Work"};

    float acitivityscalemonth[] = {30f , 180f , 270f , 240f};

    float acitivityscaleday[] = {2f , 9f , 8f , 5f};

    List<PieEntry> pieEntries;
    //List<PieEntry> pieEntriesmonth;
    //List<PieEntry> pieEntriesday;
    RadioGroup option;
    PieDataSet pieDataSet;
    PieData pieData;
    PieChart pieChart;
    float timeactive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Productivity Tracking");
        Bundle bundle = getIntent().getExtras();
        timeactive = bundle.getFloat("data");
        timeactive = timeactive/(float)(60*60*1000);
        Log.i("Running",String.valueOf(timeactive));
        setContentView(R.layout.activity_productivity_tracking);
        option = (RadioGroup)findViewById(R.id.option);
        pieChart = (PieChart)findViewById(R.id.chart);
        setUpPieChart();
        option.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.button:
                       /* pieDataSet = new PieDataSet(pieEntries,"Demo");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieData = new PieData(pieDataSet);

                        pieChart.setData(pieData);
                        pieChart.setCenterText("Time Spent On Activities");
                        pieChart.invalidate();*/
                        break;
                    case R.id.button1:
                       /* pieDataSet = new PieDataSet(pieEntriesmonth,"Demo");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieData = new PieData(pieDataSet);

                        pieChart.setData(pieData);
                        pieChart.setCenterText("Time Spent On Activities");
                        pieChart.invalidate();*/
                        break;
                    case R.id.button2:
                        /*pieDataSet = new PieDataSet(pieEntriesday,"Demo");
                        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                        pieData = new PieData(pieDataSet);

                        pieChart.setData(pieData);
                        pieChart.setCenterText("Time Spent On Activities");
                        pieChart.invalidate();*/
                        break;
                }
            }
        });
    }

    private void setUpPieChart() {
        pieEntries = new ArrayList<>();
      //  pieEntriesmonth = new ArrayList<>();
        //pieEntriesday = new ArrayList<>();
        for(int i = 0; i< acitivityscale.length; i++)
        {
            pieEntries.add(new PieEntry(acitivityscale[i], activityname[i]));
            //pieEntriesmonth.add(new PieEntry(acitivityscalemonth[i], activityname[i]));
            //pieEntriesday.add(new PieEntry(acitivityscaleday[i], activityname[i]));
        }

        pieEntries.add(new PieEntry(timeactive, "Social Media"));
        pieDataSet = new PieDataSet(pieEntries,"");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setCenterText("Time Spent On Activities");
        pieChart.invalidate();
        // pieChart.animate().rotationX(360);

    }
}
