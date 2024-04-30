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

    public static synchronized JsonObjectRequest login(Activity activity, Context context, RequestQueue queue, ProgressBar progressBar){
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERLOGIN(), null, response -> {
            try {
                User.getInstance().setID(response.getString("id"))
                                  .setEmail(response.getString("email"))
                                  .setUsername(response.getString("userName"));
            }
            catch (JSONException error) { User.dialogError(context, error.toString());}
                queue.add(viewProfile(context));
                queue.add(friendRequests(activity, context, progressBar));
        }, error -> {User.dialogError(context, error.toString()); progressBar.setVisibility(View.GONE);});
    }

    public static synchronized JsonObjectRequest viewProfile(Context context){
        ServerURLs.setURL_USERINFO();
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"))
                                  .setBio(response.getString("userBio"))
                                  .setGender(response.getString("gender"))
                                  .setBirthday(response.getString("birthday"))
                                  .setJoinDate(response.getString("joiningDate"))
                                  .setAge(response.getString("age"));
            } catch (JSONException error) { User.dialogError(context, error.toString()); }
        }, error -> User.dialogError(context, error.toString()));
    }

    public static synchronized JsonArrayRequest friendRequests(Activity activity, Context context, ProgressBar progressBar){
        ServerURLs.setURL_FR();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, ServerURLs.getURL_FR(), null, response -> User.getInstance().setFriendRequests(response), error -> {User.dialogError(context, error.toString()); progressBar.setVisibility(View.GONE);});
        progressBar.setVisibility(View.GONE);
        activity.startActivity(new Intent(context, DashboardActivity.class));
        activity.finish();
        return jsonArrayRequest;}

    /*---------------------------------------------- POST REQUESTS ---------------------------------------------*/

    public static synchronized JsonObjectRequest friendRequestStatusUpdate(Context context, JSONObject params, String path, int id){
        String url = String.format("%sfriend-requests/%s/%s", ServerURLs.getServerUrl(), path, id);

        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
            try { Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show(); }
            catch (JSONException e) { throw new RuntimeException(e); }
        }, error -> {});
    }

    public static synchronized JsonObjectRequest sendFriendRequest(Context context, JSONObject params){
        String url = String.format("%sfriend-requests/send", ServerURLs.getServerUrl());

        return new JsonObjectRequest(Request.Method.POST, url, params, response -> {
            try { Toast.makeText(context, response.getString("message"), Toast.LENGTH_SHORT).show(); }
            catch (JSONException e) { throw new RuntimeException(e); }
        }, error -> Log.e("Volley", error.toString()));
    }

    public static synchronized JsonObjectRequest updateScoreboard(Activity activity, Context context, JSONObject params, String game){
        String url = String.format("%s%s/", ServerURLs.getServerUrl(), game);

        return new JsonObjectRequest(Request.Method.POST, url, params, response -> { activity.finish(); }, error -> { Log.e("Volley", error.toString()); activity.finish(); });
    }
}
