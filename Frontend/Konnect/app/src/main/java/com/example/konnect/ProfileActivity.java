package com.example.konnect;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        /* Initialize Buttons used in activity_login.xml */
        ImageView profilePicture= findViewById(R.id.ProfilePicture);

        /* Initialize TextViews used in activity_login.xml */
        TextView profileName= findViewById(R.id.ProfileName);
        TextView profileUsername= findViewById(R.id.ProfileUsername);
        TextView profileEmail= findViewById(R.id.ProfileEmail);
        TextView profileBio= findViewById(R.id.ProfileBio);
        TextView profileGender= findViewById(R.id.ProfileGender);
        TextView profileBirthday= findViewById(R.id.ProfileBirthday);

        /* Initialize Buttons used in activity_login.xml */
        Button  profileEditButton= findViewById(R.id.ProfileEditButton);

        /* Initialize Other for call to server */
        String url = "http://coms-309-001.class.las.iastate.edu:8080/users";

    }

}
