package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Recording extends AppCompatActivity {
    private Button stop_tracker;
    private Chronometer timer;
    private long currentTime, timeInterval;
    private TextView clock;

    String wakeTime, totalDur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recording);

        stop_tracker = findViewById(R.id.stop_button);
        timer = findViewById(R.id.chronometer_txt);
        clock = findViewById(R.id.textClock);

        ShowTime();

        stop_tracker.setOnClickListener(v -> {
            timer.stop();

            Intent intent = getIntent();
            String text = intent.getStringExtra("sleepTime");

            Intent newIntent = new Intent(Recording.this, Analysis.class);
            newIntent.putExtra("wokeTime", wakeTime);
            newIntent.putExtra("sleepTime", text);
            newIntent.putExtra("totalDur", totalDur);

            startActivity(newIntent);
        });

    }

    private void ShowTime() {
        Date today = Calendar.getInstance().getTime();

        SimpleDateFormat dayFormat = new SimpleDateFormat("hh:mm a");
        String time = dayFormat.format(today);

        clock.setText(time);
        wakeTime = time;
    }

    @Override
    protected void onStop() {
        super.onStop();
        ShowTime();
        currentTime = SystemClock.elapsedRealtime();
        timeInterval = SystemClock.elapsedRealtime() - currentTime;
    }

    @Override
    protected void onStart() {
        super.onStart();
        ShowTime();

        if (currentTime != 0) {
            timer.setBase(timer.getBase() + timeInterval);
        } else {
            timer.setBase(SystemClock.elapsedRealtime());
        }

        timer.setOnChronometerTickListener(chronometer -> {
            long time = SystemClock.elapsedRealtime() - chronometer.getBase();
            int hr = (int) (time / 3600000);
            int min = (int) (time - hr * 3600000) / 60000;
            int sec = (int) (time - hr * 3600000 - min * 60000) / 1000;

            String hh = hr < 10 ? "0" + hr : hr + "";
            String mm = min < 10 ? "0" + min : min + "";
            String ss = sec < 10 ? "0" + sec : sec + "";
            totalDur = (hh + ":" + mm);

            // for checking only, will delete after
            chronometer.setText(hh + ":" + mm + ":" + ss);

        });
        timer.start();
    }

}