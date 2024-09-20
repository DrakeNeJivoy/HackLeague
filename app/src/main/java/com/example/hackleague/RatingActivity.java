package com.example.hackleague;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class RatingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RatingAdapter ratingAdapter;
    private List<String> ratingList;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        // Инициализация BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottm_nav);
        bottomNavigationView.setSelectedItemId(R.id.rating);

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Пример данных
        ratingList = new ArrayList<>();
        ratingList.add("Команда 1");
        ratingList.add("Команда 2");
        ratingList.add("Команда 3");

        ratingAdapter = new RatingAdapter(ratingList);
        recyclerView.setAdapter(ratingAdapter);

        // Обработчик нажатий для BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                Intent intent = new Intent(RatingActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.rating) {
                // Уже на странице рейтинга, ничего не делаем
                return true;
            } else if (item.getItemId() == R.id.hackathon) {
                // TODO:
                return true;
            } else if (item.getItemId() == R.id.notifications) {
                // TODO:
                return true;
            } else if (item.getItemId() == R.id.profile) {
                // TODO:
                return true;
            } else {
                return false;
            }
        });
    }
}
