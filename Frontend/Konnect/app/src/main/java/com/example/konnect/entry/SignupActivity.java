package com.example.konnect.entry;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.ChoosehobbiesActivity;
import com.example.konnect.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * This class represents the SignupActivity and handles the signup process for new users. This activity connects to LoginActivity and ChooseHobbiesActivity.
 *
 * @author Chanho Yang
 */
public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText;  // define username edittext variable
    private EditText passwordEditText;  // define password edittext variable
    private EditText confirmEditText;   // define confirm edittext variable
    private EditText emailAccountEditText; // define emailAccount edittext variable
    private EditText nameEditText;
    private EditText ageEditText;
    private EditText dobEditText;
    private Spinner genderSpinner;
    private Button loginButton;         // define login button variable
    private Button signupButton;        // define signup button variable
    private Button homeButton;
    private String gender;
    private Date birthday;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity_signup);

        /* initialize UI elements */
        usernameEditText = findViewById(R.id.signup_username_edt);  // link to username edtext in the Signup activity XML
        passwordEditText = findViewById(R.id.signup_password_edt);  // link to password edtext in the Signup activity XML
        confirmEditText = findViewById(R.id.signup_confirm_edt);    // link to confirm edtext in the Signup activity XML
        emailAccountEditText = findViewById(R.id.signup_email_edt);
        nameEditText = findViewById(R.id.signup_name_edt);
        ageEditText = findViewById(R.id.signup_age_edt);
        genderSpinner = findViewById(R.id.signup_gender_spinner);
        dobEditText = findViewById(R.id.signup_dob_edt);
        loginButton = findViewById(R.id.signup_login_btn);    // link to login button in the Signup activity XML
        signupButton = findViewById(R.id.signup_signup_btn);  // link to signup button in the Signup activity XML
        homeButton = findViewById(R.id.signup_home_btn);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        genderSpinner.setAdapter(adapter);

        // Set the selected gender when an item is selected in the spinner
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               gender = (String) parent.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* when login button is pressed, use intent to switch to Login Activity */
                Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                startActivity(intent);  // go to LoginActivity
            }
        });

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                /* grab strings from user inputs */

                String username = usernameEditText.getText().toString();
                String name = nameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String confirm = confirmEditText.getText().toString();
                String email = emailAccountEditText.getText().toString();
                String ageString = ageEditText.getText().toString();
                String dob = dobEditText.getText().toString();
                int age = Integer.parseInt(ageString);

                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                try {
                    birthday = df.parse(dob);
                }catch (ParseException e) {
                    e.printStackTrace();
                    // Handle the exception
                    Toast.makeText(SignupActivity.this, "Plesase, provide the correct form of DOB", Toast.LENGTH_SHORT).show();

                }
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

                }else if(dob.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your date of birth.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(gender.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your gender.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(ageEditText.getText().toString().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your age.", Toast.LENGTH_SHORT).show();
                    return;

                }
                /**
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String currentDate = df.format(new Date());
                 **/
                if (password.equals(confirm)){
                    Toast.makeText(getApplicationContext(), "Signing up", Toast.LENGTH_LONG).show();


                    String url = "http://coms-309-001.class.las.iastate.edu:8080/users/";
                    JSONObject params = new JSONObject();
                    try {
                        params.put("name", name);
                        params.put("userName", username);
                        params.put("emailId", email);
                        params.put("userPassword", password);
                        params.put("birthday", birthday);
                        params.put("gender", gender);
                        params.put("age", age);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e("Error",e.toString());
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
