package com.example.konnect.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.konnect.R;
import com.example.konnect.helper.FlashUser;

import java.util.List;

public class FlashcardFragment extends Fragment {
    private int currentUserIndex = 0;
    private List<FlashUser> users;

    public FlashcardFragment(List<FlashUser> users) {
        this.users = users;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment_flashcard, container, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        TextView dobTextView = view.findViewById(R.id.dobTextView);
        TextView genderTextView = view.findViewById(R.id.genderTextView);
        TextView hobbiesTextView = view.findViewById(R.id.hobbiesTextView);

        FlashUser currentUser = users.get(currentUserIndex);
        updateViews(currentUser, nameTextView, dobTextView, genderTextView, hobbiesTextView);

        Button nextButton = view.findViewById(R.id.nextButton);
        nextButton.setOnClickListener(v -> {
            currentUserIndex++;
            if (currentUserIndex >= users.size()) {
                currentUserIndex = 0; // Loop back to the first user
            }
            FlashUser nextUser = users.get(currentUserIndex);
            updateViews(nextUser, nameTextView, dobTextView, genderTextView, hobbiesTextView);
        });

        return view;
    }

    private void updateViews(FlashUser user, TextView nameTextView, TextView dobTextView, TextView genderTextView, TextView hobbiesTextView) {
        nameTextView.setText(user.getName());
        dobTextView.setText(user.getDateOfBirth().toString());
        genderTextView.setText(user.getGender());
        hobbiesTextView.setText(user.getHobbies().toString());
    }
}
