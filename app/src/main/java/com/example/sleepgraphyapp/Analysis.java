package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Analysis extends AppCompatActivity {
    String value;


    private Button mood_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);
        Intent intent=getIntent();
        value= intent.getStringExtra("key");

        mood_button = findViewById(R.id.add_mood);




        BottomNavigationView bottomNavigationView= (BottomNavigationView)findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.analysis);


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
            }
            return false;
        });

        mood_button.setOnClickListener(v -> startActivity(new Intent(Analysis.this, Mood.class)));
    }
}