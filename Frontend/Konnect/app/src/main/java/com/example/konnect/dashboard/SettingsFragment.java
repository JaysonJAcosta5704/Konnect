package com.example.konnect.dashboard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.konnect.ChoosehobbiesActivity;
import com.example.konnect.R;
import com.example.konnect.ReportActivity;
import com.example.konnect.entry.MainActivity;
import com.example.konnect.admin.AdminUserActivity;
import com.example.konnect.helper.User;
import com.example.konnect.admin.AdminFindActivity;

public class SettingsFragment extends Fragment {
    @SuppressLint("ApplySharedPref")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.dashboard_fragment_settings, container, false);
        Button editProfileButton = view.findViewById(R.id.Profile_Edit_Button);
        Button chooseHobbiesButton = view.findViewById(R.id.ChooseHobbies_Button);
        Button reportButton = view.findViewById(R.id.Report_Button);
        Button logoutButton = view.findViewById(R.id.Logout_Button);
        Button AdminButton = view.findViewById(R.id.Admin_Button);

        String userType = User.getInstance().getType();
        String userName = User.getInstance().getName();
        String userEmail = User.getInstance().getEmail();
        String userPw = User.getInstance().getPassword();

        chooseHobbiesButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChoosehobbiesActivity.class)));
        reportButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ReportActivity.class)));
        AdminButton.setOnClickListener(v -> {
            Intent intent;
            if ("n".equals(userType)) {
                intent = new Intent(v.getContext(), AdminUserActivity.class);

            } else if ("A".equals(userType)) {
                intent = new Intent(v.getContext(), AdminFindActivity.class);
                intent.putExtra("AD_USERNAME", userName);
                intent.putExtra("AD_EMAIL", userEmail);
                intent.putExtra("AD_PW", userPw);

            } else {
                // Handle other cases if necessary
                return;
            }
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            v.getContext().getSharedPreferences("USERDATA", 0).edit().clear().commit();
            startActivity(new Intent(v.getContext(), MainActivity.class));
            requireActivity().finish();
        });


        return view;
    }
}
