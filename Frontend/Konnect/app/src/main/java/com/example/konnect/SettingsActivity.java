package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konnect.helper.User;

/**
 * This class represents the SettingsActivity and handles the display of user settings and profile information.
 *
 * @author Jayson Acosta
 */
public class SettingsActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView settingsName = findViewById(R.id.Settings_Name);
        TextView settingsEmail = findViewById(R.id.Settings_Email);
        TextView settingsID = findViewById(R.id.Settings_ID);

        Button chatButton = findViewById(R.id.ChatButton);
        Button accountSettingsButton = findViewById(R.id.Profile_Edit_Button);
        Button profileButton = findViewById(R.id.Profile_Button);
        Button chooseHobbiesButton = findViewById(R.id.ChooseHobbies_Button);
        Button reportButton = findViewById(R.id.Report_Button);
        Button friendsButton = findViewById(R.id.Friends_Button);
        Button findpeopleButton = findViewById(R.id.GroupChatButton);
        Button minigameButton = findViewById(R.id.Minigame_Button);

        chatButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChatActivity.class)));
        accountSettingsButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));
        chooseHobbiesButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChoosehobbiesActivity.class)));
        reportButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ReportActivity.class)));
        minigameButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), MinigamesActivity.class)));
        findpeopleButton.setOnClickListener(v->startActivity(new Intent(v.getContext(), FindPeopleActivity.class)));

        settingsName.setText(User.getInstance().getName());
        settingsEmail.setText(User.getInstance().getEmail());
        settingsID.setText(String.format("ID: %s", User.getInstance().getID()));
    }

}
