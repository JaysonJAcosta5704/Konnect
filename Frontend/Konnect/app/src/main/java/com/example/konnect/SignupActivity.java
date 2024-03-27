package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText;  // define username edittext variable
    private EditText passwordEditText;  // define password edittext variable
    private EditText confirmEditText;   // define confirm edittext variable
    private EditText emailAccountEditText;
    private EditText nameEditText;
    private EditText ageEditText;
    private Spinner genderSpinner;
    private Button DOBButton;
    private Button loginButton;         // define login button variable
    private Button signupButton;        // define signup button variable
    private Button homeButton;
    private Date birthday;
    private String gender;
    private String ageString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        /* initialize UI elements */
        usernameEditText = findViewById(R.id.signup_username_edt);  // link to username edtext in the Signup activity XML
        passwordEditText = findViewById(R.id.signup_password_edt);  // link to password edtext in the Signup activity XML
        confirmEditText = findViewById(R.id.signup_confirm_edt);    // link to confirm edtext in the Signup activity XML
        emailAccountEditText = findViewById(R.id.signup_email_edt);
        nameEditText = findViewById(R.id.signup_name_edt);
        ageEditText = findViewById(R.id.signup_age_edt);
        genderSpinner = findViewById(R.id.signup_gender_spinner);
        DOBButton = findViewById(R.id.signup_birthday_btn);
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


        // Create a date set listener
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()  {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                // Save the selected birthday
                birthday = calendar.getTime();

                // Format the date to a string and set it as the button text
                SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
                DOBButton.setText(df.format(birthday));
            }
        };

        // Show the date picker dialog when the birthday button is clicked
        DOBButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new DatePickerDialog(SignupActivity.this, dateSetListener,
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


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
                User newUser = new User();
                newUser.setUsername(usernameEditText.getText().toString());
                newUser.setName(nameEditText.getText().toString());
                newUser.setPassword(passwordEditText.getText().toString());
                String confirm = confirmEditText.getText().toString();
                newUser.setEmail(emailAccountEditText.getText().toString());
                newUser.setBirthday(birthday);
                newUser.setGender(gender);
                ageString = ageEditText.getText().toString();
                newUser.setAge(Integer.parseInt(ageString));

                //check if user didn't left the blank
                if(newUser.getUsername().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your username", Toast.LENGTH_SHORT).show();
                    return;

                }else if(newUser.getEmail().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your email.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(newUser.getPassword().isEmpty()){
                    Toast.makeText(SignupActivity.this, "Plesae, provide your password.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(confirm.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your confirm password.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(birthday == null){
                    Toast.makeText(SignupActivity.this, "Please, provide your date of birth.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(gender.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your gender.", Toast.LENGTH_SHORT).show();
                    return;

                }else if(ageString.isEmpty()){
                    Toast.makeText(SignupActivity.this, "Please, provide your age.", Toast.LENGTH_SHORT).show();
                    return;

                }
                /**
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                String currentDate = df.format(new Date());
                 **/
                if (newUser.getPassword().equals(confirm)){
                    Toast.makeText(getApplicationContext(), "Signing up", Toast.LENGTH_LONG).show();


                    String url = "http://coms-309-001.class.las.iastate.edu:8080/users";

                    JSONObject params = new JSONObject();
                    try {
                        params.put("name", newUser.getName());
                        params.put("userName", newUser.getUsername());
                        params.put("emailId", newUser.getEmail());
                        params.put("userPassword", newUser.getPassword());
                        params.put("birthday", newUser.getBirthday());
                        params.put("gender", newUser.getGender());
                        params.put("age", newUser.getAge());
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
                                            intent.putExtra("USERNAME", newUser.getUsername());
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