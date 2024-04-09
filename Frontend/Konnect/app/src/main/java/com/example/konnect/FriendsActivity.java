package com.example.konnect;

import static com.example.konnect.helper.RequestJson.friendRequestStatusUpdate;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.helper.RequestJson;
import com.example.konnect.helper.User;

import org.json.JSONException;
import org.json.JSONObject;

public class FriendsActivity extends AppCompatActivity {
    LinearLayout containerFR, containerF, containerG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

         containerFR = findViewById(R.id.Container_FR);
         containerF = findViewById(R.id.Container_F);
         containerG = findViewById(R.id.Container_G);

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
        imageButton.setOnClickListener(v -> startActivity(new Intent(v.getContext(), FriendsActivity.class)));

        containerG.addView(createGLayout(this, "ComS-309 Group", 906));
        containerG.addView(createGLayout(this, "Admin Group", 101));

        User.getInstance().setURL_FR();

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(RequestJson.friendRequests(this, containerFR, containerF));

    }





    /**
     * context method creates a new constraint layout with an ImageView 2 TextViews and 2 more image views with onClickListeners.
     * @param userUsername username of the user
     * @param userName name of the user
     * @param num ID of the user friend request
     * @return Constraint layout to be added to screen
     */
    public static ConstraintLayout createFRLayout(Context context, LinearLayout containerFR, LinearLayout containerF, String userUsername, String userName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int fortyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40 , context.getResources().getDisplayMetrics());
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , context.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(context);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView username = new TextView(context);
        username.setId(200000 + num);
        username.setText(userUsername);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        username.setLayoutParams(layoutParams2);
        username.setTextColor(Color.BLACK);
        username.setTextSize(16);
        constraintLayout.addView(username);

        TextView name = new TextView(context);
        name.setId(300000 + num);
        name.setText(userName);
        name.setTextColor(Color.DKGRAY);
        name.setTextSize(14);
        constraintLayout.addView(name);

        ImageView accept = new ImageView(context);
        accept.setId(400000 + num);
        accept.setImageResource(R.drawable.check);
        accept.setAdjustViewBounds(true);
        accept.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));

        ImageView deny = new ImageView(context);
        deny.setId(500000 + num);
        deny.setImageResource(R.drawable.close);
        deny.setAdjustViewBounds(true);
        deny.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));

        accept.setOnClickListener(view -> {
            try {
                JSONObject params = new JSONObject();
                params.put("requestId", num);
                JsonObjectRequest jsonObjectRequest = friendRequestStatusUpdate(context, params, "accept", num);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(jsonObjectRequest);
                accept.setVisibility(View.GONE);
                deny.setVisibility(View.GONE);
                containerFR.removeView(constraintLayout);
                containerF.addView(constraintLayout);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        deny.setOnClickListener(view -> {
            try {
                JSONObject params = new JSONObject();
                params.put("requestId", num);
                JsonObjectRequest jsonObjectRequest = friendRequestStatusUpdate(context, params, "decline", num);
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(jsonObjectRequest);
                accept.setVisibility(View.GONE);
                deny.setVisibility(View.GONE);
                containerFR.removeView(constraintLayout);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });

        constraintLayout.addView(deny);
        constraintLayout.addView(accept);

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

    /**
     * context method creates a new constraint layout with an ImageView and 2 TextViews
     * @param userUsername username of the user
     * @param userName name of the user
     * @param num ID of the user friend request
     * @return Constraint layout to be added to screen
     */
    public static ConstraintLayout createFLayout(Context context, String userUsername, String userName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , context.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(context);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView username = new TextView(context);
        username.setId(200000 + num);
        username.setText(userUsername);
        ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams2.setMargins(20,0,0,0);
        username.setLayoutParams(layoutParams2);
        username.setTextColor(Color.BLACK);
        username.setTextSize(16);
        constraintLayout.addView(username);

        TextView name = new TextView(context);
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

    /**
     * context method creates a new constraint layout with an ImageView 2 TextViews
     * @param groupName name of the group
     * @param num id of the group
     * @return Constraint layout to be added to screen
     */
    public static ConstraintLayout createGLayout(Context context, String groupName, int num){

        /* Set Layout */
        ConstraintLayout constraintLayout = new ConstraintLayout(context);
        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0,10,0,10);
        constraintLayout.setLayoutParams(layoutParams);
        constraintLayout.setId(num);

        /* Create Pixel equivalent to DP */
        int sixtyDPtoPX = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 60 , context.getResources().getDisplayMetrics());

        /* Set Views */
        ImageView pfp = new ImageView(context);
        pfp.setId(100000 + num);
        pfp.setImageResource(R.drawable.default_pfp);
        pfp.setBackgroundResource(R.drawable.circle_bg);
        pfp.setAdjustViewBounds(true);
        pfp.setLayoutParams(new ViewGroup.LayoutParams(sixtyDPtoPX, sixtyDPtoPX));
        constraintLayout.addView(pfp);

        TextView group = new TextView(context);
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
