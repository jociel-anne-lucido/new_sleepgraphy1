package com.example.sleepgraphyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class UserSettings extends AppCompatActivity {

    private ImageView button_back, button_logout;
    private Button button_update;
    private EditText new_name, new_email, new_pass;

    String name, email, pass, uid, oldName, oldEmail, oldPass;

    FirebaseAuth auth;
    FirebaseUser user;
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
        ref = FirebaseDatabase.getInstance().getReference().child("UserData");
        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();

        button_logout.setOnClickListener(v -> {
            auth.signOut();
            Toast.makeText(UserSettings.this, "Logout successful.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(UserSettings.this, LoginActivity.class));
        });

        button_back.setOnClickListener(v -> startActivity(new Intent(UserSettings.this, Clock.class)));

        button_update.setOnClickListener(v -> UpdateData());
    }


    private void UpdateData() {
        if (!ValidateName() && !ValidateEmail() && !ValidatePass()) {
            Toast.makeText(UserSettings.this, "No changes made.",Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(UserSettings.this, "Data has been updated.",Toast.LENGTH_SHORT).show();
        }
    }

    private boolean ValidateName() {
        name = new_name.getText().toString();
        DatabaseReference nameRef = ref.child(uid).child("Fullname");

        nameRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                oldName = snapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        if (name.isEmpty()) { return false; }

        else if (name.equals(oldName)) {
            new_name.setError("No changes detected.");
            new_name.requestFocus();
            return false;

        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("Fullname", name);
            ref.child(uid).updateChildren(hashMap);
            return true;
        }
    }

    private boolean ValidateEmail() {
        email = new_email.getText().toString();
        DatabaseReference emailRef = ref.child(uid).child("EmailId");

        emailRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                oldEmail = snapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        if (email.isEmpty()) { return false; }

        else if (email.equals(oldEmail)) {
            new_email.setError("No changes detected.");
            new_email.requestFocus();
            return false;

        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("EmailId", email);
            ref.child(uid).updateChildren(hashMap);
            return true;
        }
    }

    private boolean ValidatePass() {
        pass = new_pass.getText().toString();
        DatabaseReference passRef = ref.child(uid).child("Password");

        passRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                oldPass = snapshot.getValue(String.class);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });

        if (pass.isEmpty()) { return false; }

        else if (pass.equals(oldPass)) {
            new_pass.setError("No changes detected.");
            new_pass.requestFocus();
            return false;

        } else {
            HashMap hashMap = new HashMap();
            hashMap.put("Password", pass);
            ref.child(uid).updateChildren(hashMap);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }

}