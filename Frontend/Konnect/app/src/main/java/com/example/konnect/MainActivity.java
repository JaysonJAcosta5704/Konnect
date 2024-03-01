package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.content.Intent;
import android.view.View;

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
        loginButton.setOnClickListener(v -> setContentView(R.layout.activity_login));
        signupButton.setOnClickListener(v -> setContentView(R.layout.activity_signup));


        /* click listener on signup button pressed */
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* when signup button is pressed, use intent to switch to Signup Activity */
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });



    }
}