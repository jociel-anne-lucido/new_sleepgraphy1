package com.example.sleepgraphyapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Analysis extends AppCompatActivity {

    private TextView wokeUpText, sleepText, totalDuration, newDuration, oldDuration, moodText, dateText;

    String wake, sleep, totaldur, newdur, olddur, mood, uid;

    SleepCycleData scd;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analysis);

        ImageView mood_button = findViewById(R.id.editMood);
        wokeUpText = findViewById(R.id.wokeupTime);
        sleepText = findViewById(R.id.sleepTime);
        totalDuration = findViewById(R.id.sleepdurTime);
        newDuration = findViewById(R.id.new_duration);
        oldDuration = findViewById(R.id.old_duration);
        moodText = findViewById(R.id.moodText);
        dateText = findViewById(R.id.date_text);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        ShowDate();
        GetIntentData();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
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
            } return false;
        });

        mood_button.setOnClickListener(v -> startActivity(new Intent(Analysis.this, Mood.class)));
    }

    private void ShowDate() {
        Date today = Calendar.getInstance().getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");

        String date = dateFormat.format(today);
        dateText.setText(date);
    }

    private void GetIntentData() {
        scd = new SleepCycleData();

        // from recording class
        Intent newIntent = getIntent();
        wake = newIntent.getStringExtra("wakeTime");
        sleep = newIntent.getStringExtra("sleepTime");
        totaldur = newIntent.getStringExtra("totalDur");

        // from mood class
        Intent intent = getIntent();
        mood = intent.getStringExtra("moodQual");

        // handles null value from intent
        if (wake == null && sleep == null && totaldur == null) {
            wake = "---";
            sleep = "---";
            totaldur = "---";
            mood = "---";
            newdur = "---";
            olddur = "---";
        }

        // formats totaldur string for sleep duration data
        if (totaldur != null && !totaldur.equals("---")) {
            String text = totaldur;
            String[] sep = text.split(":");
            newdur = (sep[0] + "hr" + sep[1] + "min");
        }

        // stores sleep data attributes to sleepcycledata class
        scd.setLastRecorded(olddur);
        scd.setNewRecorded(newdur);
        scd.setTotalDur(totaldur);
        scd.setMoodQual(mood);
        scd.setSleepTime(sleep);
        scd.setWakeTime(wake);

        DisplayText();
        StoreData();
    }

    private void StoreData() {
        // push sleep data attributes to fb
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").child(uid).child("-SleepData");

        SleepCycleData data = new SleepCycleData(olddur, newdur, totaldur, mood, sleep, wake);

        if (!wake.equals("---")) {
            userRef.setValue(data);
        }
        DisplayText();
    }

    private void DisplayText() {
        if (wake == null) {
            wokeUpText.setText("---");
            sleepText.setText("---");
            totalDuration.setText("---");
            moodText.setText("---");
            oldDuration.setText("---");
            newDuration.setText("---");
        }  else {
            GetSleepTimeData();
            GetWokeTimeData();
            GetTotalDurData();
            GetMoodQualData();
            GetNewRecordData();
            GetOldRecordData();
        }
        ShowDate();
    }

    // retrieves data from fb
    private void GetSleepTimeData() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                child(uid).child("-SleepData").child("sleepTime");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sleep = snapshot.getValue(String.class);
                sleepText.setText(sleep);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void GetWokeTimeData() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                child(uid).child("-SleepData").child("wakeTime");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                wake = snapshot.getValue(String.class);
                wokeUpText.setText(wake);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void GetTotalDurData() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                child(uid).child("-SleepData").child("totalDur");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                totaldur = snapshot.getValue(String.class);
                totalDuration.setText(totaldur);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void GetMoodQualData() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                child(uid).child("-SleepData").child("moodQual");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mood = snapshot.getValue(String.class);
                moodText.setText(mood);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void GetNewRecordData() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                child(uid).child("-SleepData").child("newRecorded");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                newdur = snapshot.getValue(String.class);
                newDuration.setText(newdur);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    private void GetOldRecordData() {
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("UserData").
                child(uid).child("-SleepData").child("lastRecorded");

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                olddur = snapshot.getValue(String.class);
                oldDuration.setText(olddur);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }


}