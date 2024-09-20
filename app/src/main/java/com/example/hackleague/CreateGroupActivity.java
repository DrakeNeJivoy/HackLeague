package com.example.hackleague;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CreateGroupActivity extends AppCompatActivity {
    EditText editTextGroupName;
    Button buttonCreateGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);
        initViews();

        buttonCreateGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String groupName = editTextGroupName.getText().toString().trim();
                String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                String user = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

                if (groupName.isEmpty()) {
                    Toast.makeText(CreateGroupActivity.this, "Введите имя группы", Toast.LENGTH_SHORT).show();
                } else {
                    createGroup(groupName, userId, user);
                }
            }
        });
    }

    private void initViews() {
        editTextGroupName = findViewById(R.id.editTextGroupName);
        buttonCreateGroup = findViewById(R.id.buttonCreateGroup);
    }

    private void createGroup(String groupName, String userId, String user) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> groupMap = new HashMap<>();
        groupMap.put("name", groupName);
        groupMap.put("creatorId", userId);
        groupMap.put("creatorName", user);
        groupMap.put("members", Arrays.asList(userId));

        db.collection("groups").add(groupMap)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(CreateGroupActivity.this, "Группа создана!", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(CreateGroupActivity.this, "Ошибка создания группы: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }
}
