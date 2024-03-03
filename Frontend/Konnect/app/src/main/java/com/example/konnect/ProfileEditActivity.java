package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Objects;

public class ProfileEditActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_profile_edit);

        /* Initialize TextViews used in activity_login.xml */
        TextView profileEditName= findViewById(R.id.ProfileEditName);
        TextView profileEditUsername= findViewById(R.id.ProfileEditUsername);
        TextView profileEditEmail= findViewById(R.id.ProfileEditEmail);
//        TextView profileEditBio= findViewById(R.id.ProfileEditBio);
//        TextView profileEditGender= findViewById(R.id.ProfileEditGender);
//        TextView profileEditBirthday= findViewById(R.id.ProfileEditBirthday);

        /* Initialize Buttons used in activity_login.xml */
        Button profileEditSaveButton= findViewById(R.id.ProfileEditSaveButton);
        profileEditSaveButton.setOnClickListener(v -> {

            /* Initialize Strings to send to server */
            String name = profileEditName.getText().toString();
            String username = profileEditUsername.getText().toString();
            String emailId = profileEditEmail.getText().toString();
//            String bio = profileEditBio.getText().toString();
//            String gender = profileEditGender.getText().toString();
//            String birthday = profileEditBirthday.getText().toString();

            HashMap<String, String> profileStrings = new HashMap<>();
            profileStrings.put("name", name);
            profileStrings.put("username", username);
            profileStrings.put("emailId", emailId);
//            profileStrings.put("bio", bio);
//            profileStrings.put("gender", gender);
//            profileStrings.put("birthday", birthday);

            /* Verify strings to send to server */
            for (String s : profileStrings.keySet()) {
                if (Objects.requireNonNull(profileStrings.get(s)).isEmpty()) {
                    profileStrings.remove(s);
                }
            }

            /* Initialize urls for call to server */
            String url = "http://coms-309-001.class.las.iastate.edu:8080/users/3";

            JSONObject params = new JSONObject();
            try {
                for (String s : profileStrings.keySet()) {
                    params.put(s, profileStrings.get(s));
                }
            } catch (JSONException e) {
                Log.e("JSON Error", e.toString());
                Toast.makeText(this, "There was a JSON Exception", Toast.LENGTH_LONG).show();
                return;
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, params, response -> {
                Log.d("Volley Response", response.toString());
                Toast.makeText(this, "Saved Changes!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(v.getContext(), ProfileActivity.class));
            }, error -> {
                Log.e("Volley error", error.toString());
                Toast.makeText(this, "There was an error in the server", Toast.LENGTH_LONG).show();
            });


            RequestQueue queue = Volley.newRequestQueue(ProfileEditActivity.this);
            queue.add(jsonObjectRequest);
        });
    }
}
