package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class AdminfindbyidActivity extends AppCompatActivity {

    private EditText editTextId;
    private TextView textViewName, textViewUsername;
    Intent intent = getIntent();
    String adm_email;
    String adm_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_find_by_id);

        editTextId = findViewById(R.id.edit_text_id);
        textViewName = findViewById(R.id.text_view_name);
        textViewUsername = findViewById(R.id.text_view_username);

        adm_email = intent.getStringExtra("AD_EMAIL");
        adm_pw = intent.getStringExtra("AD_PW");

        Button buttonSearch = findViewById(R.id.button_search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString();
                String url = "http://coms-309-001.class.las.iastate.edu:8080/adminUser/getUser/" + id + "/" + adm_email + "/" + adm_pw + "/";
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String name = response.getString("name");
                                    String username = response.getString("userName");
                                    textViewName.setText(name);
                                    textViewUsername.setText(username);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestQueue queue = Volley.newRequestQueue(AdminfindbyidActivity.this);
                queue.add(jsonObjectRequest);
            }
        });
    }
}

