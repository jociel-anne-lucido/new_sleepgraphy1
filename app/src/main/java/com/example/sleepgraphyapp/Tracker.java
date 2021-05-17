package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Tracker extends AppCompatActivity {
    private Button button;

    String sleepTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);

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
        Date today = Calendar.getInstance().getTime();

        SimpleDateFormat dayFormat = new SimpleDateFormat("hh:mm a");
        String time = dayFormat.format(today);

        sleepTime = time;

        Intent intent = new Intent (Tracker.this, Recording.class);
        intent.putExtra("sleepTime", sleepTime);
        startActivity(intent);

    }
}