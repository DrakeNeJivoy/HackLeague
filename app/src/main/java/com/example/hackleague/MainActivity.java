package com.example.hackleague;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        webView = findViewById(R.id.web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Включаем JavaScript, если нужно
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://digitalbusiness.kz/tag/hakaton/");
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initViews();
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                return true;
            } else if (item.getItemId() == R.id.rating) {
                Intent intent = new Intent(MainActivity.this, RatingActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.hackathon) {
                Intent intent = new Intent(MainActivity.this, HackathonActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.notifications) {
                Intent intent = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
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