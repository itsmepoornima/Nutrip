package com.example.nutrip_ahealthydietapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.forgetPassword;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class login extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText email = findViewById(R.id.email_text);
        EditText pass = findViewById(R.id.Password);
        AppCompatButton login = findViewById(R.id.login_done);
        TextView register = findViewById(R.id.go_to_reg);
        TextView forgetpassword=findViewById(R.id.forget_pass);

        auth = FirebaseAuth.getInstance();

        login.setOnClickListener(v -> {
            String emailTxt = Objects.requireNonNull(email.getText()).toString().trim();
            String passTxt = Objects.requireNonNull(pass.getText()).toString().trim();

            if (emailTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
            } else if (passTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Password", Toast.LENGTH_SHORT).show();
            } else {
                auth.signInWithEmailAndPassword(emailTxt, passTxt).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(login.this, MainActivity1.class);
                        startActivity(intent);
                        overridePendingTransition(0, 0);
                        finish();
                    } else {
                        Toast.makeText(login.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        forgetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(login.this, forgetPassword.class);
                startActivity(intent);
            }
        });

        register.setOnClickListener(v -> {
            Intent intent = new Intent(login.this, register.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        });
    }
}
