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

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /* Initialize Buttons used in activity_login.xml */
//        ImageView profilePicture= findViewById(R.id.ProfilePicture);

        /* Initialize TextViews used in activity_login.xml */
        TextView profileName= findViewById(R.id.ProfileName);
        TextView profileUsername= findViewById(R.id.ProfileUsername);
        TextView profileEmail= findViewById(R.id.ProfileEmail);
        TextView profileBio= findViewById(R.id.ProfileBio);
        TextView profileGender= findViewById(R.id.ProfileGender);
        TextView profileBirthday= findViewById(R.id.ProfileBirthday);

        /* Initialize Buttons used in activity_login.xml */
        Button  profileEditButton= findViewById(R.id.ProfileEditButton);

        /* OnClick Listener for button*/
        profileEditButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));

        /* Initialize urls for call to server */
        String urlJSON = "http://coms-309-001.class.las.iastate.edu:8080/users/3";
//        String urlIMAGE = "";

        /* Calls to the server to set TextViews and ImageView*/
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, urlJSON, null, response -> {
            /* Beginning of response */
            Log.d("Volley Response", response.toString());
            try {
                profileName.setText(response.getString("name"));
                profileUsername.setText(response.getString("username"));
                profileEmail.setText(response.getString("emailId"));
                profileBio.setText(response.getString("bio"));
                profileGender.setText(response.getString("gender"));
                profileBirthday.setText(response.getString("birthday"));

            } catch (JSONException e) {
                Log.e("Volley error", e.toString());
                Toast.makeText(this, "There was an error in the server", Toast.LENGTH_LONG).show();
            }
        }, error -> {
            Log.e("Volley error", error.toString());
            Toast.makeText(this, "There was an error in the server", Toast.LENGTH_LONG).show();
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);

    }

}
