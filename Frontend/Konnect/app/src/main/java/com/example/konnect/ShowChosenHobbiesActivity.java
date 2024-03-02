package com.example.konnect;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ShowChosenHobbiesActivity extends AppCompatActivity {
    private String username_hobby;
    private String url = "https://localhost/hobby";
    List<Hobby> hobbies = new ArrayList<>();

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_displayhobby);


        Intent intent = getIntent();
        username_hobby = intent.getStringExtra("USERNAME");



    }
}
