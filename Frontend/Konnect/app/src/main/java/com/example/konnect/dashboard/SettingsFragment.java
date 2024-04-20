package com.example.konnect.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.konnect.ChoosehobbiesActivity;
import com.example.konnect.FindPeopleActivity;
import com.example.konnect.ProfileEditActivity;
import com.example.konnect.R;
import com.example.konnect.ReportActivity;
import com.example.konnect.helper.User;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.activity_settings, container, false);

        TextView settingsName = view.findViewById(R.id.Settings_Name);
        TextView settingsEmail = view.findViewById(R.id.Settings_Email);
        TextView settingsID = view.findViewById(R.id.Settings_ID);

        Button chatButton = view.findViewById(R.id.ChatButton);
        Button accountSettingsButton = view.findViewById(R.id.Profile_Edit_Button);
        Button profileButton = view.findViewById(R.id.Profile_Button);
        Button chooseHobbiesButton = view.findViewById(R.id.ChooseHobbies_Button);
        Button reportButton = view.findViewById(R.id.Report_Button);
        Button friendsButton = view.findViewById(R.id.Friends_Button);
        Button findpeopleButton = view.findViewById(R.id.GroupChatButton);
        Button minigameButton = view.findViewById(R.id.Minigame_Button);

        accountSettingsButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));
        chooseHobbiesButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChoosehobbiesActivity.class)));
        reportButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ReportActivity.class)));
        findpeopleButton.setOnClickListener(v->startActivity(new Intent(v.getContext(), FindPeopleActivity.class)));

        settingsName.setText(User.getInstance().getName());
        settingsEmail.setText(User.getInstance().getEmail());
        settingsID.setText(String.format("ID: %s", User.getInstance().getID()));

        return view;
    }
}
