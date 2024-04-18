package com.example.konnect.entry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.RequestJson;
import com.example.konnect.helper.ServerURLs;
import com.example.konnect.helper.User;


/**
 * This class represents the main activity of the application. This activity connects to LoginActivity and SignupActivity.
 *
 * @author Jayson Acosta
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity_main);

        Button loginButton = findViewById(R.id.LoginButton);
        TextView signupButton = findViewById(R.id.Signup);
        ProgressBar progressBar = findViewById(R.id.ProgressBar);

        loginButton.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);
            EditText loginUsername = findViewById(R.id.Login_Username);
            EditText loginPassword = findViewById(R.id.Login_Password);

            User.getInstance().setPassword(loginPassword.getText().toString());
            String input = loginUsername.getText().toString();

            if (input.contains("@")) {
                User.getInstance().setEmail(input);
                ServerURLs.setURL_EP();
            } else {
                User.getInstance().setUsername(input);
                ServerURLs.setURL_UP();
            }

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(RequestJson.login(this, this, queue, progressBar));
        });
        signupButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SignupActivity.class)));
    }

}
