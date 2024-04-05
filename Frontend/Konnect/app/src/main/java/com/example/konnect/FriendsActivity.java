package com.example.konnect;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class FriendsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        ConstraintLayout containerFR = findViewById(R.id.Container_FR);
        ConstraintLayout containerF = findViewById(R.id.Container_F);
        ConstraintLayout containerG = findViewById(R.id.Container_G);

        ImageView imageViewVFR = findViewById(R.id.ImageView_VFR);
        ImageView imageViewVF = findViewById(R.id.ImageView_VF);
        ImageView imageViewG = findViewById(R.id.ImageView_G);

        imageViewVFR.setOnClickListener(v -> {
            if(containerFR.isShown()){
                containerFR.setVisibility(View.GONE);
                imageViewVFR.setImageResource(R.drawable.expand_more);
            } else {
                containerFR.setVisibility(View.VISIBLE);
                imageViewVFR.setImageResource(R.drawable.expand_less);
            }
        });
        imageViewVF.setOnClickListener(v -> {
            if(containerF.isShown()){
                containerF.setVisibility(View.GONE);
                imageViewVF.setImageResource(R.drawable.expand_more);
            } else {
                containerF.setVisibility(View.VISIBLE);
                imageViewVF.setImageResource(R.drawable.expand_less);
            }
        });
        imageViewG.setOnClickListener(v -> {
            if(containerG.isShown()){
                containerG.setVisibility(View.GONE);
                imageViewG.setImageResource(R.drawable.expand_more);
            } else {
                containerG.setVisibility(View.VISIBLE);
                imageViewG.setImageResource(R.drawable.expand_less);
            }
        });

        containerFR.addView(createFRLayout("Jayson04", "Jayson Acosta", 1));
        containerFR.addView(createFRLayout("TKPhoenix", "Tram Kieu Hoang", 2));
        containerFR.addView(createFRLayout("tracylxn", "Tracy Lin", 3));

    }

    private ConstraintLayout createFRLayout(String username, String name, int num){
        ConstraintLayout constraintLayout = new ConstraintLayout(this);

        ImageView friendRequestPFP = new ImageView(this);
        friendRequestPFP.setId(100000 + num);
        friendRequestPFP.setImageResource(R.drawable.default_pfp);
        friendRequestPFP.setBackgroundResource(R.drawable.circle_bg);
        friendRequestPFP.setMaxWidth(60);
        friendRequestPFP.setMaxHeight(60);
        constraintLayout.addView(friendRequestPFP);

        TextView friendRequestUsername = new TextView(this);
        friendRequestUsername.setId(200000 + num);
        friendRequestUsername.setText(username);
        friendRequestUsername.setTextColor(0);
        friendRequestUsername.setTextSize(16);
        constraintLayout.addView(friendRequestUsername);

        TextView friendRequestName = new TextView(this);
        friendRequestName.setId(300000 + num);
        friendRequestName.setText(name);
        friendRequestName.setTextColor(0);
        friendRequestName.setTextSize(14);
        constraintLayout.addView(friendRequestName);

        ImageView accept = new ImageView(this);
        accept.setId(400000 + num);
        accept.setImageResource(R.drawable.check);
        accept.setMaxWidth(40);
        accept.setMaxHeight(40);
        accept.setOnClickListener(view -> {});
        constraintLayout.addView(accept);

        ImageView deny = new ImageView(this);
        deny.setId(500000 + num);
        deny.setImageResource(R.drawable.close);
        deny.setMaxWidth(40);
        deny.setMaxHeight(40);
        deny.setOnClickListener(view -> {});
        constraintLayout.addView(deny);

        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(friendRequestPFP.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(friendRequestPFP.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(friendRequestPFP.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(friendRequestUsername.getId(), ConstraintSet.START, friendRequestPFP.getId(), ConstraintSet.END);
        constraintSet.connect(friendRequestUsername.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);

        constraintSet.connect(friendRequestName.getId(), ConstraintSet.START, friendRequestUsername.getId(), ConstraintSet.START);
        constraintSet.connect(friendRequestName.getId(), ConstraintSet.TOP, friendRequestUsername.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(deny.getId(),ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
        constraintSet.connect(deny.getId(),ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(deny.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);




        return constraintLayout;
    }


}