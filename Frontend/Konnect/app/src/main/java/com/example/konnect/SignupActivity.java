package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText;  // define username edittext variable
    private EditText passwordEditText;  // define password edittext variable
    private EditText confirmEditText;   // define confirm edittext variable
    private EditText emailAccountEditText;
    private Button loginButton;         // define login button variable
    private Button signupButton;        // define signup button variable
    private Button homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /* initialize UI elements */
        usernameEditText = findViewById(R.id.signup_username_edt);  // link to username edtext in the Signup activity XML
        passwordEditText = findViewById(R.id.signup_password_edt);  // link to password edtext in the Signup activity XML
        confirmEditText = findViewById(R.id.signup_confirm_edt);    // link to confirm edtext in the Signup activity XML
        emailAccountEditText = findViewById(R.id.signup_email_edt);
        loginButton = findViewById(R.id.signup_login_btn);    // link to login button in the Signup activity XML
        signupButton = findViewById(R.id.signup_signup_btn);  // link to signup button in the Signup activity XML
        homeButton = findViewById(R.id.signup_home_btn);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* when login button is pressed, use intent to switch to Login Activity */
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);  // go to LoginActivity
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* grab strings from user inputs */
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirm = confirmEditText.getText().toString();
                String email = emailAccountEditText.getText().toString();

                //check if user didn't left the blank
                if(username.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your username", Toast.LENGTH_SHORT).show();
                    return;

                }else if(email.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your email.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(password.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Plesae, provide your password.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(confirm.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your confirm password.", Toast.LENGTH_SHORT).show();
                    return;

                }
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String currentDate = df.format(new Date());

                if (password.equals(confirm)){
                    Toast.makeText(getApplicationContext(), "Signing up", Toast.LENGTH_LONG).show();


                    String url = "http://coms-309-001.class.las.iastate.edu:8080/users";

                    JSONObject params = new JSONObject();
                    try {
                        params.put("name", username);
                        params.put("emailId", email);
                        params.put("joiningDate", currentDate);
                        params.put("UserPassword", password);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest

                            (Request.Method.POST, url, params, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    try {
                                        String status = response.getString("message"); // Get the status from the response

                                        if (status.equals("success")) {
                                            // Display success message
                                            Toast.makeText(SignupActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();

                                            // Start ChoosehobbiesActivity
                                            Intent intent = new Intent(SignupActivity.this, ChoosehobbiesActivity.class);
                                            intent.putExtra("USERNAME", username);
                                            startActivity(intent);
                                        } else {
                                            // Display failure message
                                            Toast.makeText(SignupActivity.this, "Signup failed!", Toast.LENGTH_SHORT).show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }, new Response.ErrorListener() {

                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_LONG).show();
                                }
                            });



                    RequestQueue queue = Volley.newRequestQueue(SignupActivity.this);
                    queue.add(jsonObjectRequest);

                }
                else {
                    Toast.makeText(getApplicationContext(), "Password don't match", Toast.LENGTH_LONG).show();
                }
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}