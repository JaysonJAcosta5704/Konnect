package com.example.konnect;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.graphics.Color;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

public class FriendsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        LinearLayout containerFR = findViewById(R.id.Container_FR);
        LinearLayout containerF = findViewById(R.id.Container_F);
        LinearLayout containerG = findViewById(R.id.Container_G);

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

        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(v -> finish());


        containerFR.addView(createFRLayout("Nishi", "Nishi Kant", 3));
        containerFR.addView(createFRLayout("Daniel", "Daniel P", 4));
        containerFR.addView(createFRLayout("Chanho", "Chanho Yang", 5));

        containerF.addView(createFLayout("Jayson04", "Jayson Acosta", 1));

        containerG.addView(createGLayout("ComS-309 Group", 906));



    }

    private ConstraintLayout createFRLayout(String userUsername, String userName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int fortyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40 , getResources().getDisplayMetrics());
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(this);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView username = new TextView(this);
        username.setId(200000 + num);
        username.setText(userUsername);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        username.setLayoutParams(layoutParams2);
        username.setTextColor(Color.BLACK);
        username.setTextSize(16);
        constraintLayout.addView(username);

        TextView name = new TextView(this);
        name.setId(300000 + num);
        name.setText(userName);
        name.setTextColor(Color.DKGRAY);
        name.setTextSize(14);
        constraintLayout.addView(name);

        ImageView accept = new ImageView(this);
        accept.setId(400000 + num);
        accept.setImageResource(R.drawable.check);
        accept.setAdjustViewBounds(true);
        accept.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));
        accept.setOnClickListener(view -> {});
        constraintLayout.addView(accept);

        ImageView deny = new ImageView(this);
        deny.setId(500000 + num);
        deny.setImageResource(R.drawable.close);
        deny.setAdjustViewBounds(true);
        deny.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));
        deny.setOnClickListener(view -> {});
        constraintLayout.addView(deny);

        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(username.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(username.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);

        constraintSet.connect(name.getId(), ConstraintSet.START, username.getId(), ConstraintSet.START);
        constraintSet.connect(name.getId(), ConstraintSet.TOP, username.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(deny.getId(),ConstraintSet.END, constraintLayout.getId(), ConstraintSet.END);
        constraintSet.connect(deny.getId(),ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(deny.getId(),ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(accept.getId(), ConstraintSet.END ,deny.getId(),ConstraintSet.START);
        constraintSet.connect(accept.getId(), ConstraintSet.TOP, deny.getId(),ConstraintSet.TOP);
        constraintSet.connect(accept.getId(), ConstraintSet.BOTTOM, deny.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayout);

        return constraintLayout;
    }

    private ConstraintLayout createFLayout(String userUsername, String userName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(this);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView username = new TextView(this);
        username.setId(200000 + num);
        username.setText(userUsername);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        username.setLayoutParams(layoutParams2);
        username.setTextColor(Color.BLACK);
        username.setTextSize(16);
        constraintLayout.addView(username);

        TextView name = new TextView(this);
        name.setId(300000 + num);
        name.setText(userName);
        name.setTextColor(Color.DKGRAY);
        name.setTextSize(14);
        constraintLayout.addView(name);

        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(username.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(username.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);

        constraintSet.connect(name.getId(), ConstraintSet.START, username.getId(), ConstraintSet.START);
        constraintSet.connect(name.getId(), ConstraintSet.TOP, username.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayout);

        return constraintLayout;
    }

    private ConstraintLayout createGLayout(String groupName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(this);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(this);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView group = new TextView(this);
        group.setId(200000 + num);
        group.setText(groupName);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        group.setLayoutParams(layoutParams2);
        group.setTextColor(Color.BLACK);
        group.setTextSize(16);
        constraintLayout.addView(group);

        /* Set Constraints */
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        constraintSet.connect(pfp.getId(), ConstraintSet.START, constraintLayout.getId(), ConstraintSet.START);
        constraintSet.connect(pfp.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(pfp.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.connect(group.getId(), ConstraintSet.START, pfp.getId(), ConstraintSet.END);
        constraintSet.connect(group.getId(), ConstraintSet.TOP, constraintLayout.getId(), ConstraintSet.TOP);
        constraintSet.connect(group.getId(), ConstraintSet.BOTTOM, constraintLayout.getId(), ConstraintSet.BOTTOM);

        constraintSet.applyTo(constraintLayout);

        return constraintLayout;
    }

}