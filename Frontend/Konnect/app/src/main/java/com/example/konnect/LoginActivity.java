package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
            String url = "https://jsonplaceholder.typicode.com/users/1";

            String inputUsername = loginUsername.getText().toString();
            String inputPassword = loginPassword.getText().toString();

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, response -> {
            /* Beginning of response */
                Log.d("Volley Response", response.toString());
                String responseUsername, responsePassword;
                try {
                     responseUsername = response.getString("username");
                     responsePassword = response.getString("id");
                } catch (JSONException e) {
                    Log.e("Volley error", e.toString());
                    toastLoginError(0);
                    return;
                }

                if(inputUsername.equals(responseUsername) && inputPassword.equals(responsePassword)){
                    Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                } else if (!inputUsername.equals(responseUsername)){
                    toastLoginError(1);
                } else {
                    toastLoginError(2);
                }
                /* End of response */
            }, error -> toastLoginError(0));

            RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
            queue.add(jsonObjectRequest);
            /* End of OnClickListener for loginButton */
        });
        /* End of onCreate */
    }

    public void toastLoginError(int errorCode){
        switch (errorCode){
            case 0:
                Toast.makeText(LoginActivity.this, "There was an error while attempting to login, please try again", Toast.LENGTH_LONG).show();
                break;
            case 1:
                Toast.makeText(LoginActivity.this, "There is no user with your username. Please try again.", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(LoginActivity.this, "Your password is incorrect. Please try again.", Toast.LENGTH_LONG).show();
                break;
        }
    }


} //end of class
