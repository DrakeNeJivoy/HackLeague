package com.example.hackleague;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton create_group_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        create_group_button = findViewById(R.id.create_group_button);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.rating) {
                Intent intent = new Intent(ProfileActivity.this, RatingActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.hackathon) {
                Intent intent = new Intent(ProfileActivity.this, HackathonActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.notifications) {
                Intent intent = new Intent(ProfileActivity.this, NotificationActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                return true;
            } else {
                return false;
            }
        });
        create_group_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, CreateGroupActivity.class);
                startActivity(intent);
            }
        });
    }
}