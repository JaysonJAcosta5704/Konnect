package com.example.konnect;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class FriendsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ConstraintLayout constraintLayoutVFR = findViewById(R.id.ConstraintLayout_VFR);
        ConstraintLayout constraintLayoutVF = findViewById(R.id.ConstraintLayout_VF);
        ConstraintLayout constraintLayoutG = findViewById(R.id.ConstraintLayout_G);

        ImageView imageViewVFR = findViewById(R.id.ImageView_VFR);
        ImageView imageViewVF = findViewById(R.id.ImageView_VF);
        ImageView imageViewG = findViewById(R.id.ImageView_G);

        imageViewVFR.setOnClickListener(v -> { constraintLayoutVFR.setVisibility(View.INVISIBLE); });
        imageViewVF.setOnClickListener(v -> { constraintLayoutVF.setVisibility(View.INVISIBLE); });
        imageViewG.setOnClickListener(v -> { constraintLayoutG.setVisibility(View.INVISIBLE); });
    }

}