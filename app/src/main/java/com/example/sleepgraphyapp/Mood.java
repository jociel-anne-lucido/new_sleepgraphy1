package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Mood extends AppCompatActivity {

    private Button submit_btn;
    private ImageView moodView, back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        moodView = findViewById(R.id.moodView);

        submit_btn = findViewById(R.id.submit_button);
        back_btn = findViewById(R.id.back);

        back_btn.setOnClickListener(v -> startActivity(new Intent(Mood.this, Analysis.class)));
    }
}