package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konnect.helper.User;

public class AdminFindActivity extends AppCompatActivity {

    Intent intent = getIntent();
    String adm_username;
    String adm_email;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adfindusers);

        TextView adminName = findViewById(R.id.Admin_Name);
        TextView adminEmail = findViewById(R.id.Admin_Email);
        TextView adminID = findViewById(R.id.Admin_id);

        Button findAll = findViewById(R.id.findAll_btn);
        Button findOne = findViewById(R.id.find_user_btn);


        findAll.setOnClickListener(v->startActivity(new Intent(v.getContext(), AdminFindAllActivity.class)));
        findOne.setOnClickListener(v->startActivity(new Intent(v.getContext(), AdminfindbyidActivity.class)));

        adm_username = intent.getStringExtra("AD_USERNAME");
        adm_email = intent.getStringExtra("AD_EMAIL");
        adminName.setText(User.getInstance().getName());
        adminEmail.setText(adm_email);
        adminID.setText(String.format("ID: %s", adm_username));
    }

}
