package com.example.konnect;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /* All the object used in activity_main.xml */
//    private TextView welcomeTextView;
    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Initialize objects used in activity_main.xml*/
        loginButton = findViewById(R.id.LoginButton);
        signupButton = findViewById(R.id.SignupButton);

        /* Set OnClick listeners for each button */
        //TODO: Create OnClick listeners for buttons after creating login and signup pages

    }
}