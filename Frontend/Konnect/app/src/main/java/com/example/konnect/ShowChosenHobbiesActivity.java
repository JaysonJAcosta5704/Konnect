package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.PixelCopy;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;

public class ShowChosenHobbiesActivity extends AppCompatActivity {
    private String username_hobby;
    private String url = "https://localhost/hobby";
    List<Hobby> hobbies = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayhobby);


        Intent intent = getIntent();
        username_hobby = intent.getStringExtra("USERNAME");

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(username_hobby + ", Choose your hobby!");



    }

    public void sendGetRequest(String url_send, String username){

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                url_send + "?username=" + username, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray hobbiesArray = response.getJSONArray("HOBBY");\
                    LinearLayout hobbiesLayout = findViewById(R.id.hobbiesLayout);
                    for (int i = 0; i < hobbiesArray.length(); i++) {
                        JSONObject hobby = hobbiesArray.getJSONObject(i);
                        String name = hobby.getString("name");
                        String type = hobby.getString("type");

                        TextView hobbyView = new TextView(ShowChosenHobbiesActivity.this);
                        hobbyView.setText(name + ", " + type);
                        hobbiesLayout.addView(hobbyView);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(jsonObjectRequest);


    }
}
