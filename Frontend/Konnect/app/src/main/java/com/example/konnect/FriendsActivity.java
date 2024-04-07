package com.example.konnect;

import android.app.AlertDialog;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

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

        containerG.addView(createGLayout("ComS-309 Group", 906));
        containerG.addView(createGLayout("Admin Group", 101));


        User.getInstance().setURL_FR();




        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, User.getInstance().getURL_FR(), null, response -> {

            try {
                for (int i = 0; i < response.length(); i++) {
                    JSONObject item = response.getJSONObject(i);
                    int id = item.getInt("id");
                    String senderUsername = item.getString("senderUsername");
                    String status = item.getString("status");

                    switch (status){
                        case "DECLINED":
                            break;
                        case "PENDING":
                            containerFR.addView(createFRLayout(senderUsername, senderUsername, id));
                            break;
                        case "ACCEPTED":
                            containerF.addView(createFLayout(senderUsername, senderUsername, id));
                            break;
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }



        }, error -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage(error.toString())
                    .setTitle("Error:");

            AlertDialog dialog = builder.create();
            dialog.show();
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);

    }





    /**
     * This method creates a new constraint layout with an ImageView 2 TextViews and 2 more image views with onClickListeners.
     * @param userUsername username of the user
     * @param userName name of the user
     * @param num ID of the user friend request
     * @return Constraint layout to be added to screen
     */
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

        ImageView deny = new ImageView(this);
        deny.setId(500000 + num);
        deny.setImageResource(R.drawable.close);
        deny.setAdjustViewBounds(true);
        deny.setLayoutParams(new ViewGroup.LayoutParams(fortyDPtoPX, fortyDPtoPX));





        accept.setOnClickListener(view -> {
            try {
                JSONObject params = new JSONObject();
                params.put("requestId", num);
                JsonObjectRequest jsonObjectRequest = friendRequestStatusUpdate(params, "accept", num);
                RequestQueue queue = Volley.newRequestQueue(this);
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
                JsonObjectRequest jsonObjectRequest = friendRequestStatusUpdate(params, "decline", num);
                RequestQueue queue = Volley.newRequestQueue(this);
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
     * This method creates a new constraint layout with an ImageView and 2 TextViews
     * @param userUsername username of the user
     * @param userName name of the user
     * @param num ID of the user friend request
     * @return Constraint layout to be added to screen
     */
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

    /**
     * This method creates a new constraint layout with an ImageView 2 TextViews
     * @param groupName name of the group
     * @param num id of the group
     * @return Constraint layout to be added to screen
     */
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


    private JsonObjectRequest friendRequestStatusUpdate(JSONObject params, String path, int id){
        String url = String.format("http://coms-309-001.class.las.iastate.edu:8080/friend-requests/%s/%s", path, id);


        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
            try {
                Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {});
    }

}
