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
import com.example.konnect.helper.User;

import org.json.JSONException;

/**
 * This class represents the LoginActivity and handles the user login process. This activity connects to SignupActivity and SettingsActivity.
 *
 * @author Jayson Acosta
 */
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

            /* Set input and password */
            String input = loginUsername.getText().toString();
            User.getInstance().setPassword(loginPassword.getText().toString());

            /* Check if the input is a username or an email */
            if (input.contains("@")) {
                User.getInstance().setEmail(input);
                User.getInstance().setURL_EP();
            } else {
                User.getInstance().setUsername(input);
                User.getInstance().setURL_UP();
            }

            /* Request ID from server */
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, User.getInstance().getURL_USERLOGIN(), null, response -> {

                try {
                    User.getInstance().setID(response.getString("id"));
                    User.getInstance().setEmail(response.getString("email"));
                    User.getInstance().setUsername(response.getString("userName"));
                    User.getInstance().setURL_USERINFO();
                    startActivity(new Intent(v.getContext(), SettingsActivity.class));
                }
                catch (JSONException e) { User.toastError(this,0,e.toString());}

            }, error -> User.toastError(this,0,error.toString()));

            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(jsonObjectRequest);
        });
        /* End of onCreate */
    }
} //end of class
