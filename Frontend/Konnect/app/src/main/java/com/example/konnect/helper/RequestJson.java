package com.example.konnect.helper;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestJson {

    public static JsonObjectRequest login(Context context){
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERLOGIN(), null, response -> {
            try {
                User.getInstance().setID(response.getString("id"));
                User.getInstance().setEmail(response.getString("email"));
                User.getInstance().setUsername(response.getString("userName"));
                User.getInstance().dataValid = true;
            }
            catch (JSONException error) { User.dialogError(context, error.toString());}
        }, error -> User.getInstance().dataValid = false);
    }

    public static JsonObjectRequest viewProfile(Context context){
        ServerURLs.setURL_USERINFO();
        return new JsonObjectRequest(Request.Method.GET, ServerURLs.getURL_USERINFO(), null, response -> {
            try {
                User.getInstance().setName(response.getString("name"));
                User.getInstance().setBio(response.getString("userBio"));
                User.getInstance().setGender(response.getString("gender"));
                User.getInstance().setBirthday(response.getString("birthday"));
                User.getInstance().setAge(response.getString("age"));
                User.getInstance().dataValid = true;
            } catch (JSONException error) { User.dialogError(context, error.toString()); User.getInstance().dataValid = false;}
        }, error -> {
            User.getInstance().dataValid = false;
        });
    }

    public static JsonArrayRequest friendRequests(Context context){
        ServerURLs.setURL_FR();
        return new JsonArrayRequest(Request.Method.GET, ServerURLs.getURL_FR(), null, response -> {
            User.getInstance().setFriendRequests(response);
            User.getInstance().dataValid = true;
        }, error -> {
            User.getInstance().dataValid = false;
        }); }

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

    public static void makeAllRequest(Context context) throws InterruptedException {
        RequestQueue queue = Volley.newRequestQueue(context);
        synchronized (queue){
            queue.add(login(context));
            queue.wait(300);
            queue.add(viewProfile(context));
            queue.add(friendRequests(context));
        }
    }
}
