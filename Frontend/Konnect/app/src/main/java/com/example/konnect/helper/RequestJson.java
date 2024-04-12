package com.example.konnect.helper;

import static com.example.konnect.FriendsActivity.createFLayout;
import static com.example.konnect.FriendsActivity.createFRLayout;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestJson {

    public static JsonObjectRequest login(Context context){
        return new JsonObjectRequest(Request.Method.GET, User.getInstance().getURL_USERLOGIN(), null, response -> {
            try {
                User.getInstance().setID(response.getString("id"));
                User.getInstance().setEmail(response.getString("email"));
                User.getInstance().setUsername(response.getString("userName"));
                User.getInstance().setURL_USERINFO();
            }
            catch (JSONException error) { User.dialogError(context, error.toString());}
        }, error -> User.dialogError(context, error.toString()));
    }

    public static JsonObjectRequest viewProfile(Context context){
        return new JsonObjectRequest(Request.Method.GET, User.getInstance().getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"));
                User.getInstance().setBio(response.getString("bio"));
                User.getInstance().setGender(response.getString("gender"));
                User.getInstance().setBirthday(response.getString("birthday"));
                User.getInstance().setAge(response.getString("age"));
            } catch (JSONException error) { User.dialogError(context, error.toString());}
        }, error -> User.dialogError(context, error.toString()));
    }

    public static JsonArrayRequest friendRequests(Context context, LinearLayout containerFR, LinearLayout containerF){
        User.getInstance().setURL_FR();
        return new JsonArrayRequest(Request.Method.GET, User.getInstance().getURL_FR(), null, response -> {

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
                            containerFR.addView(createFRLayout(context, containerFR, containerF, senderUsername, senderUsername, id));
                            break;
                        case "ACCEPTED":
                            containerF.addView(createFLayout(context, senderUsername, senderUsername, id));
                            break;
                    }
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {
            User.dialogError(context, error.toString());
        });
    }

    public static JsonObjectRequest friendRequestStatusUpdate(Context context, JSONObject params, String path, int id){
        String url = String.format("%s/friend-requests/%s/%s",User.getServerUrl(), path, id);

        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
            try {
                Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {});
    }
}
