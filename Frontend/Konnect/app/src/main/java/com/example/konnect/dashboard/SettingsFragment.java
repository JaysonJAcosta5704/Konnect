package com.example.konnect.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.konnect.ChoosehobbiesActivity;
import com.example.konnect.ProfileEditActivity;
import com.example.konnect.R;
import com.example.konnect.ReportActivity;

public class SettingsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dashboard_fragment_settings, container, false);
        Button editProfileButton = view.findViewById(R.id.Profile_Edit_Button);
        Button chooseHobbiesButton = view.findViewById(R.id.ChooseHobbies_Button);
        Button reportButton = view.findViewById(R.id.Report_Button);

        editProfileButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));
        chooseHobbiesButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChoosehobbiesActivity.class)));
        reportButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ReportActivity.class)));

        return view;
    }
}
