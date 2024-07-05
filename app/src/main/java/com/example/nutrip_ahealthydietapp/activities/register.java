package com.example.nutrip_ahealthydietapp.activities;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.nutrip_ahealthydietapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class register extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        EditText email = findViewById(R.id.email);
        EditText pass = findViewById(R.id.password);
        AppCompatButton register = findViewById(R.id.reg_done);
        TextView login = findViewById(R.id.go_to_login);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(v -> {
            String emailTxt = Objects.requireNonNull(email.getText()).toString().trim();
            String passTxt = Objects.requireNonNull(pass.getText()).toString().trim();

            if (emailTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
            } else if (passTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            } else {
                auth.createUserWithEmailAndPassword(emailTxt, passTxt).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(register.this, "User Registered", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(register.this, login.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    } else {
                        Toast.makeText(register.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(register.this, login.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        });
    }
}
