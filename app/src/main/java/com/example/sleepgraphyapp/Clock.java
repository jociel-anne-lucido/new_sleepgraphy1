package com.example.sleepgraphyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.util.Calendar;

public class Clock extends AppCompatActivity {
    TextView textView;

    private ImageView button_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        textView=findViewById(R.id.greetings);

        button_profile = findViewById(R.id.profile);

        button_profile.setOnClickListener(v -> startActivity(new Intent(Clock.this, UserSettings.class)));

        Calendar calendar = Calendar.getInstance();
        String currentDate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        TextView textViewDate = findViewById(R.id.date);
        textViewDate.setText(currentDate);

        int Hours=calendar.get(Calendar.HOUR_OF_DAY);

        if (Hours > 0 && Hours < 12) {
            textView.setText("Good Morning!");
        }
        else if(Hours>=12 && Hours<17) {
            textView.setText("Good Afternoon!");

        }
        else {
            textView.setText("Good Night!");
        }



        BottomNavigationView bottomNavigationView= (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {

                case R.id.home:

                    break;
                case R.id.tracker:
                    Intent intent2= new Intent(Clock.this,Tracker.class);
                    startActivity(intent2);
                    break;
                case R.id.analysis:
                    Intent intent3= new Intent(Clock.this,Analysis.class);
                    startActivity(intent3);
                    break;
            }
            return false;
        });


    }
}