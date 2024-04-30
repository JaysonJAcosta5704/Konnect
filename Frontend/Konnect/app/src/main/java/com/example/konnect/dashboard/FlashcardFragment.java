package com.example.konnect.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.konnect.R;
import com.example.konnect.helper.FlashUser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.example.konnect.helper.User;

import java.lang.reflect.Type;
import java.util.List;

public class FlashcardFragment extends Fragment {
    private int currentUserIndex = 0;
    private List<FlashUser> users;

    String idString = User.getInstance().getID();
    int id = Integer.parseInt(idString);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment_flashcard, container, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView dobTextView = view.findViewById(R.id.dobTextView);
        TextView genderTextView = view.findViewById(R.id.genderTextView);
        TextView hobbiesTextView = view.findViewById(R.id.hobbiesTextView);

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            currentUserIndex++;
            if (currentUserIndex >= users.size()) {
                currentUserIndex = 0; // Loop back to the first user
            }
            FlashUser nextUser = users.get(currentUserIndex);
            updateViews(nextUser, nameTextView, dobTextView, genderTextView, hobbiesTextView);
        });

        // Call the method to fetch users
        fetchUsers(nameTextView, dobTextView, genderTextView, hobbiesTextView);

        return view;
    }

    private void fetchUsers(TextView nameTextView, TextView dobTextView, TextView genderTextView, TextView hobbiesTextView) {
        String url = "http://coms-309-001.class.las.iastate.edu:8080/users/"+ id + "/getFlashcards";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Parse the response and update your list of users
                        Gson gson = new Gson();
                        Type listType = new TypeToken<List<FlashUser>>(){}.getType();
                        users = gson.fromJson(response, listType);

                        // Then update your UI (TextViews, etc.) with the new data
                        if (!users.isEmpty()) {
                            FlashUser firstUser = users.get(0);
                            updateViews(firstUser, nameTextView, dobTextView, genderTextView, hobbiesTextView);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle error
            }
        });

        // Add the request to the RequestQueue.
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    private void updateViews(FlashUser user, TextView nameTextView, TextView dobTextView, TextView genderTextView, TextView hobbiesTextView) {
        nameTextView.setText(user.getName());
        dobTextView.setText(user.getDateOfBirth().toString());
        genderTextView.setText(user.getGender());
        hobbiesTextView.setText(user.getHobbies().toString());
    }
}
