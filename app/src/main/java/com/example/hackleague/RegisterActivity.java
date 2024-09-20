package com.example.hackleague;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText editTextTextEmailAddress;
    EditText editTextTextPassword;
    EditText editTextTextPassword2;
    EditText editTextTextName;
    Button buttonRegister;
    TextView textViewLogin;
    RadioButton radioButtonUser;
    RadioButton radioButtonOrganiser;
    ImageButton imageButtonViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        imageButtonViewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
                    editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    editTextTextPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    editTextTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    editTextTextPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                editTextTextPassword.setSelection(editTextTextPassword.getText().length());
                editTextTextPassword2.setSelection(editTextTextPassword.getText().length());
            }
        });

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextTextEmailAddress.getText().toString().trim();
                String password = editTextTextPassword.getText().toString();
                String password2 = editTextTextPassword2.getText().toString();
                String name = editTextTextName.getText().toString();
                int score = 0;

                if (email.isEmpty() || password.isEmpty() || password2.isEmpty() || name.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                } else if (!password.equals(password2)) {
                    Toast.makeText(RegisterActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                } else {
                    registerUser(email, password, name, score);
                }
            }
        });
    }

    private void initViews() {
        imageButtonViewPassword = findViewById(R.id.imageButtonViewPassword);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        editTextTextPassword2 = findViewById(R.id.editTextTextPassword2);
        editTextTextName = findViewById(R.id.editTextName);
        buttonRegister = findViewById(R.id.buttonRegister);
        radioButtonUser = findViewById(R.id.radioButtonUser);
        radioButtonOrganiser = findViewById(R.id.radioButtonOwner);
        textViewLogin = findViewById(R.id.textViewLogin);
    }

    private void registerUser(String email, String password, String name, int score) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        // Регистрация успешна, сохраняем данные в Firestore
                        String role;
                        boolean isOrganizer = radioButtonOrganiser.isChecked();
                        if (radioButtonUser.isChecked()) {
                            role = "user"; // Участник
                        } else if (isOrganizer) {
                            role = "organizer";
                        } else {
                            role = "admin"; // Если нужно
                        }

                        FirebaseFirestore db = FirebaseFirestore.getInstance();
                        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();

                        Map<String, Object> userMap = new HashMap<>();
                        userMap.put("email", email);
                        userMap.put("role", role);
                        userMap.put("name", name);
                        userMap.put("scores", score); // Используйте переданное значение
                        userMap.put("groups", new ArrayList<>()); // Инициализация пустого массива групп

                        if (isOrganizer) {
                            userMap.put("isConfirmed", false); // Ожидает подтверждения
                        }

                        db.collection("users").document(uid)
                                .set(userMap)
                                .addOnSuccessListener(aVoid -> {
                                    if (isOrganizer) {
                                        Toast.makeText(RegisterActivity.this, "Организатор зарегистрирован. Ожидайте подтверждения.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                                    }
                                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                    finish();
                                })
                                .addOnFailureListener(e -> {
                                    Toast.makeText(RegisterActivity.this, "Ошибка записи в Firestore: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                });
                    } else {
                        Toast.makeText(RegisterActivity.this, "Ошибка регистрации: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
