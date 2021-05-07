package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

public class Recording extends AppCompatActivity {
    private Button stop_tracker;
    private Chronometer timer;
    private long currentTime, timeInterval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        stop_tracker = findViewById(R.id.stop_button);
        timer = findViewById(R.id.chronometer_txt);

        stop_tracker.setOnClickListener(v -> {
            timer.stop();
            startActivity(new Intent(Recording.this, Analysis.class));
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        currentTime = SystemClock.elapsedRealtime();
        timeInterval = SystemClock.elapsedRealtime() - currentTime;
    }

    @Override
    protected void onStart() {
        super.onStart();

        if (currentTime != 0) {
            timer.setBase(timer.getBase() + timeInterval);
        } else {
            timer.setBase(SystemClock.elapsedRealtime());
        }
        timer.start();
    }

}