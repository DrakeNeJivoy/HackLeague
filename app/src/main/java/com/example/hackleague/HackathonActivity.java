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

public class HackathonActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    ImageButton create_group_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hackathon);
<<<<<<< HEAD

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setSelectedItemId(R.id.hackathon);
        create_group_button = findViewById(R.id.create_group_button);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                Intent intent = new Intent( HackathonActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.rating) {
                Intent intent = new Intent(HackathonActivity.this, RatingActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.hackathon) {
                return true;
            } else if (item.getItemId() == R.id.notifications) {
                Intent intent = new Intent(HackathonActivity.this, NotificationActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                Intent intent = new Intent(HackathonActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
=======
>>>>>>> b9bed462a58be1d285a1cbf06ee9cc36ee92284f
    }
}