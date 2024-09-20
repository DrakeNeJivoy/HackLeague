package com.example.hackleague;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                //TODO
                return true;
            } else if (item.getItemId() == R.id.rating) {
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.hackathon) {
                //TODO
                return true;
            } else if (item.getItemId() == R.id.notifications) {
                //TODO
                return true;
            } else if (item.getItemId() == R.id.profile) {
                //TODO
                return true;
            } else {
                return false;
            }
        });

    }
    private void initViews() {
        bottomNavigationView = findViewById(R.id.bottm_nav);
    }
}