package com.example.konnect.dashboard;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.konnect.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class DashboardActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnItemSelectedListener(navListener);

        if (savedInstanceState == null) {
            loadFragment(new Fragment());
        }
    }

    private final NavigationBarView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;

        if (item.getItemId() == R.id.flashcards) {
            selectedFragment = new Fragment();
        } else if (item.getItemId() == R.id.profile) {
            selectedFragment = new Fragment();
        } else if (item.getItemId() == R.id.friends) {
            selectedFragment = new FriendsFragment();
        } else if (item.getItemId() == R.id.chat) {
            selectedFragment = new Fragment();
        }else if (item.getItemId() == R.id.minigames) {
            selectedFragment = new Fragment();
        }

        return loadFragment(selectedFragment);
    };

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
