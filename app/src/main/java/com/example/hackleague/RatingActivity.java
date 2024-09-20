package com.example.hackleague;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RatingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RatingAdapter ratingAdapter;
    private List<Team> teamList;
    BottomNavigationView bottomNavigationView;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        // Инициализация RecyclerView
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Пример данных команд
        teamList = new ArrayList<>();
        teamList.add(new Team("Команда 1", 150));
        teamList.add(new Team("Команда 2", 300));
        teamList.add(new Team("Команда 3", 200));

        // Сортировка команд по очкам
        Collections.sort(teamList, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return Integer.compare(t2.getPoints(), t1.getPoints()); // Сортировка по убыванию
            }
        });

        // Установка адаптера для RecyclerView
        ratingAdapter = new RatingAdapter(teamList);
        recyclerView.setAdapter(ratingAdapter);

        // Инициализация BottomNavigationView
        bottomNavigationView = findViewById(R.id.bottm_nav);
        bottomNavigationView.setSelectedItemId(R.id.rating);

        // Обработчик нажатий для BottomNavigationView
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.news) {
                Intent intent = new Intent(RatingActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.rating) {
                return true;
            } else if (item.getItemId() == R.id.hackathon) {
                Intent intent = new Intent(RatingActivity.this, HackathonActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.notifications) {
                Intent intent = new Intent(RatingActivity.this, NotificationActivity.class);
                startActivity(intent);
                return true;
            } else if (item.getItemId() == R.id.profile) {
                Intent intent = new Intent(RatingActivity.this, ProfileActivity.class);
                startActivity(intent);
                return true;
            } else {
                return false;
            }
        });
    }

    // Метод для обновления очков команды
    public void updateTeamPoints(String teamName, int newPoints) {
        for (Team team : teamList) {
            if (team.getName().equals(teamName)) {
                team.setPoints(newPoints);
                break;
            }
        }
        // Сортировка и обновление списка
        Collections.sort(teamList, (t1, t2) -> Integer.compare(t2.getPoints(), t1.getPoints()));
        ratingAdapter.notifyDataSetChanged();
    }
}
