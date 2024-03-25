package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

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

        /* Calls to the server to set Profile Information */
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, User.getInstance().getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"));
                User.getInstance().setBio(response.getString("bio"));
                User.getInstance().setGender(response.getString("gender"));
                User.getInstance().setBirthday(response.getString("birthday"));
                User.getInstance().setAge(response.getString("age"));

                profileName.setText(User.getInstance().getName());
                profileUsername.setText(User.getInstance().getUsername());
                profileEmail.setText(User.getInstance().getEmail());
                profileBio.setText(User.getInstance().getBio());
                profileGender.setText(User.getInstance().getGender());
                profileBirthday.setText(User.getInstance().getBirthday());

            } catch (JSONException e) { User.toastError(this, 1, e.toString()); Log.e("JSON Error", e.toString());}
        }, error -> User.toastError(this, 1, error.toString()));


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);



    }

}
