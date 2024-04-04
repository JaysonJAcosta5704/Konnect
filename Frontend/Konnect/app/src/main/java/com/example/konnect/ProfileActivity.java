package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This class represents the ProfileActivity and displays the user's profile information. This activity connects to ProfileEditActivity.
 *
 * @author Jayson Acosta
 */
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
        TextView profileAge = findViewById(R.id.ProfileAge);
        TextView profileBirthday= findViewById(R.id.ProfileBirthday);
        TextView profileJoinDate= findViewById(R.id.ProfileJoinDate);

        /* Set text inside of TextViews */
        profileName.setText(User.getInstance().getName());
        profileUsername.setText(User.getInstance().getUsername());
        profileEmail.setText(User.getInstance().getEmail());
        profileBio.setText(User.getInstance().getBio());
        profileGender.setText(String.format("%s%s", profileGender.getText(), User.getInstance().getGender()));
        profileAge.setText(String.format("%s%s", profileAge.getText(), User.getInstance().getAge()));
        profileBirthday.setText(String.format("%s%s", profileBirthday.getText(), User.getInstance().getBirthday()));
        profileJoinDate.setText(String.format("%s%s", profileJoinDate.getText(), User.getInstance().getJoinDate()));

        /* Initialize and set onClick listener for edit button*/
        Button editButton = findViewById(R.id.Edit_Button);
        editButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));


    }

}
