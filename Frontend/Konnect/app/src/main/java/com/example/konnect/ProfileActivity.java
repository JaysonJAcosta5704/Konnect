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
        TextView profileBirthday= findViewById(R.id.ProfileBirthday);

        profileName.setText(User.getInstance().getName());
        profileUsername.setText(User.getInstance().getUsername());
        profileEmail.setText(User.getInstance().getEmail());
        profileBio.setText(User.getInstance().getBio());
        profileGender.setText(User.getInstance().getGender());
        profileBirthday.setText(User.getInstance().getBirthday());

        /* Initialize Buttons used in activity_login.xml */
        Button  profileEditButton= findViewById(R.id.ProfileEditButton);

        /* OnClick Listener for button*/
        profileEditButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));
    }

}
