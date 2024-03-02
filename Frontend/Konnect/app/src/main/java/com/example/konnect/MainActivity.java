package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize Buttons used in activity_main.xml*/
        Button loginButton = findViewById(R.id.LoginButton);
        Button signupButton = findViewById(R.id.SignupButton);

        /* Set OnClick listeners for each button */
        loginButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), LoginActivity.class)));
        signupButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SignupActivity.class)));
    }
}