package com.example.fariahuq.tori;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

public class SentimentTracking extends AppCompatActivity {


    List<BarEntry> entries = new ArrayList<>();
    RadioGroup option;
    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getSupportActionBar().setTitle("Emotion Tracking");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentiment_tracking);
        option = (RadioGroup)findViewById(R.id.option);
        barChart = (BarChart)findViewById(R.id.linechart);
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
        entries.add(new BarEntry(0f, 9f));
        entries.add(new BarEntry(1f, 6f));
        entries.add(new BarEntry(2f, 10f));
        entries.add(new BarEntry(3f, 5f));
        // gap of 2f
        entries.add(new BarEntry(5f, 2f));
        entries.add(new BarEntry(6f, 4f));

        entries.add(new BarEntry(7f, 4f));

        BarDataSet set = new BarDataSet(entries, "Emotion tracking");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width
        barChart.setData(data);
        barChart.setFitBars(true); // make the x-axis fit exactly all bars
        barChart.invalidate(); // refresh
    }
}
