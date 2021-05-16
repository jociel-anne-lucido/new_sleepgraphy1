package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Tracker extends AppCompatActivity {
    TextView time1, time2, time3,time4,time5;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);
        time1 =findViewById(R.id.time_1);
        time2 =findViewById(R.id.time_2);
        time3 =findViewById(R.id.time_3);
        time4 =findViewById(R.id.time_4);
        time5 =findViewById(R.id.time_5);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        String currenTimee = simpleDateFormat.format(calendar.getTime());

        try {
            Date date = simpleDateFormat.parse(currenTimee);
            calendar.setTime(date);
            calendar.add(Calendar.MINUTE,25);
            String result1 = simpleDateFormat.format(calendar.getTime());
            time1.setText(result1);

            calendar.add(Calendar.MINUTE,35);
            String result2 = simpleDateFormat.format(calendar.getTime());
            time2.setText(result2);

            calendar.add(Calendar.MINUTE,30);
            String result3 = simpleDateFormat.format(calendar.getTime());
            time3.setText(result3);

            calendar.add(Calendar.MINUTE,30);
            String result4 = simpleDateFormat.format(calendar.getTime());
            time4.setText(result4);

            calendar.add(Calendar.MINUTE,30);
            String result5 = simpleDateFormat.format(calendar.getTime());
            time5.setText(result5);

        }
        catch (Exception e) {

        }


        BottomNavigationView bottomNavigationView= (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.tracker);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:
                    Intent intent1= new Intent(Tracker.this,Clock.class);
                    startActivity(intent1);
                    break;
                case R.id.tracker:
                    break;
                case R.id.analysis:
                    Intent intent3= new Intent(Tracker.this,Analysis.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        });

        button = findViewById(R.id.start_button);
        button.setOnClickListener(v -> openRecording());
    }
    public void openRecording (){
        startActivity(new Intent (this,Recording.class));
    }
}