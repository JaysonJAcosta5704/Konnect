package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.konnect.helper.User;

public class AdminFindActivity extends AppCompatActivity {

    Intent intent = getIntent();
    String adm_username;
    String adm_email;
    String adm_pw;
    private EditText confirmpw;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adfindusers);

        TextView adminName = findViewById(R.id.Admin_Name);
        TextView adminEmail = findViewById(R.id.Admin_Email);
        TextView adminID = findViewById(R.id.Admin_id);

        Button findAll = findViewById(R.id.findAll_btn);
        Button findOne = findViewById(R.id.find_user_btn);
        confirmpw = findViewById(R.id.type_pw_edt);

        adm_pw = intent.getStringExtra("AD_PW");
        adm_username = intent.getStringExtra("AD_USERNAME");
        adm_email = intent.getStringExtra("AD_EMAIL");
        String pw = confirmpw.getText().toString();

        findAll.setOnClickListener(v -> {
            if (pw.equals(adm_pw)) {
                //startActivity(new Intent(v.getContext(), AdminFindAllActivity.class));
                // Start AdminFindAllActivity
                Intent intent = new Intent(AdminFindActivity.this, AdminFindAllActivity.class);
                intent.putExtra("AD_USERNAME", adm_username);
                intent.putExtra("AD_EMAIL", adm_email);
                intent.putExtra("AD_PW", adm_pw);
                startActivity(intent);
            } else {
                Toast.makeText(AdminFindActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        });

        findOne.setOnClickListener(v -> {
            if (pw.equals(adm_pw)) {
                //startActivity(new Intent(v.getContext(), AdminfindbyidActivity.class));
                Intent intent = new Intent(AdminFindActivity.this, AdminfindbyidActivity.class);
                intent.putExtra("AD_USERNAME", adm_username);
                intent.putExtra("AD_EMAIL", adm_email);
                intent.putExtra("AD_PW", adm_pw);
                startActivity(intent);
            } else {
                Toast.makeText(AdminFindActivity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
            }
        });



        adminName.setText(User.getInstance().getName());
        adminEmail.setText(adm_email);
        adminID.setText(String.format("ID: %s", adm_username));
    }

}
