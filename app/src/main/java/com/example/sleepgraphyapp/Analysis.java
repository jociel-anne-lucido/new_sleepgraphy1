package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Analysis extends AppCompatActivity {

    private ImageView mood_button;
    private TextView wokeUpTime, sleepTime, totalDurTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        mood_button = findViewById(R.id.add_mood);
        wokeUpTime = findViewById(R.id.wokeupTime);
        sleepTime = findViewById(R.id.sleepTime);
        totalDurTime = findViewById(R.id.sleepdurTime);

        BottomNavigationView bottomNavigationView= (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.analysis);

        DisplayText();

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:
                    Intent intent1= new Intent(Analysis.this,Clock.class);
                    startActivity(intent1);
                    break;
                case R.id.tracker:
                    Intent intent2= new Intent(Analysis.this,Tracker.class);
                    startActivity(intent2);
                    break;
                case R.id.analysis:
                    break;
            } return false;
        });

        mood_button.setOnClickListener(v -> startActivity(new Intent(Analysis.this, Mood.class)));
    }

    private void DisplayText() {
        Intent intent = getIntent();
        String wokeText = intent.getStringExtra("wokeTime");
        String sleepText = intent.getStringExtra("sleepTime");
        String durationText = intent.getStringExtra("totalDur");

        wokeUpTime.setText(wokeText);
        sleepTime.setText(sleepText);
        totalDurTime.setText(durationText);
    }
}