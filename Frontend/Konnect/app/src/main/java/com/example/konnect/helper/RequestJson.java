package com.example.konnect.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.konnect.dashboard.DashboardActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestJson {

    /*---------------------------------------------- GET REQUESTS ----------------------------------------------*/

    public static JsonObjectRequest login(Activity activity, Context context, RequestQueue queue, ProgressBar progressBar){
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERLOGIN(), null, response -> {
            try {
                User.getInstance().setID(response.getString("id"))
                                  .setEmail(response.getString("email"))
                                  .setUsername(response.getString("userName"));
            }
            catch (JSONException error) { User.dialogError(context, error.toString());}
                queue.add(viewProfile(context));
                queue.add(friendRequests(activity, context, progressBar));
        }, error -> Log.e("error", error.toString()));
    }

    public static JsonObjectRequest viewProfile(Context context){
        ServerURLs.setURL_USERINFO();
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"))
                                  .setBio(response.getString("userBio"))
                                  .setGender(response.getString("gender"))
                                  .setBirthday(response.getString("birthday"))
                                  .setAge(response.getString("age"));
            } catch (JSONException error) { User.dialogError(context, error.toString()); }
        }, error -> Log.e("error", error.toString()));
    }

    public static JsonArrayRequest friendRequests(Activity activity, Context context, ProgressBar progressBar){
        ServerURLs.setURL_FR();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ServerURLs.getURL_FR(), null, response -> User.getInstance().setFriendRequests(response), error -> Log.e("error", error.toString()));
        progressBar.setVisibility(View.GONE);
        activity.startActivity(new Intent(context, DashboardActivity.class));
        activity.finish();
        return jsonArrayRequest;}

    /*---------------------------------------------- POST REQUESTS ---------------------------------------------*/

    public static JsonObjectRequest friendRequestStatusUpdate(Context context, JSONObject params, String path, int id){
        String url = String.format("%s/friend-requests/%s/%s", ServerURLs.getServerUrl(), path, id);

        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
            try {
                Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show();
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, error -> {});
    }

}
