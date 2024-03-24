package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Initialize Buttons used in activity_login.xml*/
        Button loginButton = findViewById(R.id.L_LoginButton);
        Button signupButton = findViewById(R.id.L_SignupButton);
        Button homeButton = findViewById(R.id.L_HomeButton);

        /* Initialize EditTexts used in activity_login.xml */
        EditText loginUsername = findViewById(R.id.LoginUsername);
        EditText loginPassword = findViewById(R.id.LoginPassword);

        /* Set OnClick listeners for each button */
        signupButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SignupActivity.class)));
        homeButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MainActivity.class)));
        loginButton.setOnClickListener(v -> {
            /* Start of OnClickListener for loginButton */

            User.getInstance().setUsername(loginUsername.getText().toString());
            User.getInstance().setPassword(loginPassword.getText().toString());
            User.getInstance().setURL_UP();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, User.getInstance().getURL(), null, response -> {

                try {
                    User.getInstance().setID(response.getString("id"));
                    startActivity(new Intent(v.getContext(), ProfileActivity.class));
                }
                catch (JSONException e) { User.toastError(LoginActivity.this,0);}

            }, error -> User.toastError(LoginActivity.this,0));

            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(jsonObjectRequest);
        });
        /* End of onCreate */
    }
} //end of class
