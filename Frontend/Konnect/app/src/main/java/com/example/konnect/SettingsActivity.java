package com.example.konnect;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

/**
 * This class represents the SettingsActivity and handles the display of user settings and profile information. This Activity connects to ChatActivity and ProfileActivity.
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


        chatButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChatActivity.class)));
        profileButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileActivity.class)));
        accountSettingsButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ProfileEditActivity.class)));
        chooseHobbiesButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ChoosehobbiesActivity.class)));
        reportButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), ReportActivity.class)));

        /* Calls to the server to set Profile Information */
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, User.getInstance().getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"));
                User.getInstance().setBio(response.getString("bio"));
                User.getInstance().setGender(response.getString("gender"));
                User.getInstance().setBirthday(response.getString("birthday"));
                User.getInstance().setAge(response.getString("age"));

                settingsName.setText(User.getInstance().getName());
                settingsEmail.setText(User.getInstance().getEmail());
                settingsID.setText(String.format("ID: %s", User.getInstance().getID()));
            } catch (JSONException e) { User.toastError(this, 1, e.toString()); Log.e("JSON Error", e.toString());}
        }, error -> User.toastError(this, 1, error.toString()));


        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);
    }
}
