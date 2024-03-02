package com.example.konnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChoosehobbiesActivity extends AppCompatActivity {

    private String username_hobby;
    private List<CheckBox> checkBoxes = new ArrayList<>();
    private String url = "https://localhost/hobby";

    List<Hobby> hobbies = new ArrayList<>();




    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_hobby);

        Intent intent = getIntent();
        username_hobby = intent.getStringExtra("USERNAME");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username_hobby + ", Choose your hobby!");

        hobbies.add(new Hobby("Soccer", "OUTDOOR"));
        hobbies.add(new Hobby("MOVIE", "INDIVIDUAL"));
        hobbies.add(new Hobby("COOKING", "INDOOR"));
        hobbies.add(new Hobby("READING", "INDOOR"));
        hobbies.add(new Hobby("VIDEOGAME", "INDOOR"));
        hobbies.add(new Hobby("Fishing", "OUTDOOR"));
        hobbies.add(new Hobby("DRAWING", "INDIVIDUAL"));
        hobbies.add(new Hobby("HIKING", "OUTDOOR"));
        hobbies.add(new Hobby("TRAVELING", "INDIVIDUAL"));
        hobbies.add(new Hobby("WORKING OUT", "INDIVIDUAL"));




        checkBoxes.add((CheckBox) findViewById(R.id.checkBox1));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox2));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox3));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox4));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox5));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox6));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox7));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox8));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox9));
        checkBoxes.add((CheckBox) findViewById(R.id.checkBox10));


        //assign Hobby value to each corresponding checkbox
        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            Hobby hobby = hobbies.get(i);
            checkBox.setText(hobby.getName() + ", " + hobby.getType());
        }

        Button submitButton = findViewById(R.id.hobby_choose_btn);

        //to check if user choose more than 5 hobbies
        for (CheckBox checkBox : checkBoxes) {
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int checkedCount = 0;
                    for (CheckBox cb : checkBoxes) {
                        if (cb.isChecked()) {
                            checkedCount++;
                        }
                    }

                    if (checkedCount > 5) {
                        checkBox.setChecked(false);
                        Toast.makeText(ChoosehobbiesActivity.this, "You cannot choose more than 5 hobbies", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                submitHobbies();
            }
        });



    }

    private void submitHobbies(){
        //JSON array for storing hobbies
        JSONArray hobbiesArray = new JSONArray();

        for (int i = 0; i < checkBoxes.size(); i++) {
            CheckBox checkBox = checkBoxes.get(i);
            if (checkBox.isChecked()) {
                Hobby hobby = hobbies.get(i);
                JSONObject hobbyJson = new JSONObject();
                try {
                    hobbyJson.put("name", hobby.getName());
                    hobbyJson.put("type", hobby.getType());
                    hobbiesArray.put(hobbyJson);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        //checking if user choose at least one hobby from the list
        if (hobbiesArray.length() == 0) {
            Toast.makeText(this, "Choose at least one hobby", Toast.LENGTH_SHORT).show();
            return;
        }

        JSONObject postBody = new JSONObject();
        try{
            postBody.put("HOBBY", hobbiesArray);
            postBody.put("USERNAME", username_hobby);
        }catch (JSONException e){
            e.printStackTrace();
        }

        sendPostRequest(url, postBody);

        Intent intent = new Intent(ChoosehobbiesActivity.this, ShowChosenHobbiesActivity.class);
        intent.putExtra("USERNAME", username_hobby);  // key-value to pass to the MainActivity
        startActivity(intent);  // go to ChoosehobbiesActivity with the key-value data



    }

    public void sendPostRequest(String url_post, JSONObject postBody){

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url_post,
                postBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ChoosehobbiesActivity.this, "Data submitted", Toast.LENGTH_SHORT).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ChoosehobbiesActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            /**for later use
             *
             * @return
             */
            public Map<String, String> getParams(){
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };

        requestQueue.add(request);



    }


}
