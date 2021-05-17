package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Mood extends AppCompatActivity {
    TextView textView;

    private Button submit_btn;
    private Button good_btn;
    private Button relax_btn;
    private Button bad_btn;
    private Button soso_btn;
    private Button worst_btn;
    private ImageView moodView, back_btn;
    private String st;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);
        submit_btn = findViewById(R.id.submit_button);
        textView = findViewById(R.id.moodText);

        moodView = findViewById(R.id.moodView);
        good_btn = findViewById(R.id.good_button);
        relax_btn = findViewById(R.id.relaxing_button);
        bad_btn = findViewById(R.id.bad_button);
        soso_btn = findViewById(R.id.soso_button);
        worst_btn = findViewById(R.id.worst_button);
        back_btn = findViewById(R.id.back);


        back_btn.setOnClickListener(v -> startActivity(new Intent(Mood.this, Analysis.class)));

        submit_btn.setOnClickListener(v -> {
            if(good_btn.isSelected()) {
                String value="Good";
                Intent intent = new Intent(Mood.this, Analysis.class);
                intent.putExtra("key",value);
                startActivity(intent);

            }else {
                startActivity(new Intent(Mood.this, Analysis.class));

            }






        });


    }


}


