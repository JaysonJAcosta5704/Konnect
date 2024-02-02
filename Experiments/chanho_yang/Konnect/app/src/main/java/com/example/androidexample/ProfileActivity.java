package com.example.androidexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private EditText phoneNumber;
    private EditText dob;
    private EditText hobby;
    private EditText address;

    private Button homeButton;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);            // link to profile activity XML

        /* initialize UI elements */
        phoneNumber = findViewById(R.id.profile_phoneNumber_edt);
        dob = findViewById(R.id.profile_dob_edt);
        hobby = findViewById(R.id.profile_hobby_edt);
        address = findViewById(R.id.profile_address_edt);
        homeButton = findViewById(R.id.profile_home_btn);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}
