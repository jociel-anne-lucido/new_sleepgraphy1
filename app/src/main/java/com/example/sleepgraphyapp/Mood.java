package com.example.sleepgraphyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Mood extends AppCompatActivity {

    private Button submit_btn;
    private ImageView moodView, back_btn;
    private RadioGroup radioButton;

    private Integer[] mood = {R.drawable.good, R.drawable.relaxing, R.drawable.soso, R.drawable.bad, R.drawable.worst};

    private String answer = "", uid;

    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood);

        submit_btn = findViewById(R.id.submit_button);
        radioButton = findViewById(R.id.radioGroup);
        moodView = findViewById(R.id.moodView);
        back_btn = findViewById(R.id.back);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        back_btn.setOnClickListener(v -> startActivity(new Intent(Mood.this, Analysis.class)));

        // checks user input and displays mood pic
        radioButton.setOnCheckedChangeListener((group, checkedId) -> {
            RadioButton rb = findViewById(checkedId);
            answer = rb.getText().toString();
            int index = group.indexOfChild(rb);
            moodView.setImageResource(mood[index]);
        });

        submit_btn.setOnClickListener(v -> {
            DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                    child(uid).child("-SleepData").child("moodQual");

            userRef.setValue(answer).addOnCompleteListener(task -> {
                Intent intent = new Intent(Mood.this, Analysis.class);
                intent.putExtra("moodQual", answer);
                startActivity(intent);
            });
        });
    }

}


