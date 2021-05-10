package com.example.sleepgraphyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserSettings extends AppCompatActivity {

    private ImageView button_back, button_logout;
    private Button button_update;
    private EditText new_name, new_email, new_pass;

    FirebaseAuth auth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_settings);

        button_logout = findViewById(R.id.logout_button);
        button_back = findViewById(R.id.back_button);
        button_update = findViewById(R.id.update_button);
        new_name = findViewById(R.id.upd_name);
        new_email = findViewById(R.id.upd_email);
        new_pass = findViewById(R.id.upd_pass);

        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference("UserData");

        button_logout.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(UserSettings.this, "Logout successful.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserSettings.this, LoginActivity.class));
        });

        button_back.setOnClickListener(v -> startActivity(new Intent(UserSettings.this, Clock.class)));

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}