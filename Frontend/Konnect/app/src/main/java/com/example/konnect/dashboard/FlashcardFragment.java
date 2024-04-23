package com.example.konnect.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class FlashcardFragment extends Fragment {
    private String userName;

    public FlashcardFragment(String userName) {
        this.userName = userName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dashboard_fragment_flashcard, container, false);

        TextView nameTextView = view.findViewById(R.id.nameTextView);
        nameTextView.setText(userName);

        Button likeButton = view.findViewById(R.id.likeButton);
        likeButton.setOnClickListener(v -> {
            // Handle the "like" action here
        });

        return view;
    }
}
