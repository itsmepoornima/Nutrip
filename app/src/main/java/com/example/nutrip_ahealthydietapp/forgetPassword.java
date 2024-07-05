package com.example.nutrip_ahealthydietapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrip_ahealthydietapp.activities.login;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class forgetPassword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);
        EditText email_forget =findViewById(R.id.email_forget);
        Button reset =findViewById(R.id.reset);
        TextView go_back_to_login =findViewById(R.id.go_to_login_again);
        FirebaseAuth auth = FirebaseAuth.getInstance();

        reset.setOnClickListener(v -> {
            String emailTxt = Objects.requireNonNull(email_forget.getText()).toString().trim();
            if (emailTxt.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter Email Address", Toast.LENGTH_SHORT).show();
            }else{
                auth.sendPasswordResetEmail(emailTxt).addOnCompleteListener(n -> {
                    if (n.isSuccessful()) {
                        Toast.makeText(forgetPassword.this, "Password Reset Email Sent", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(forgetPassword.this, login.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(forgetPassword.this, Objects.requireNonNull(n.getException()).getMessage(), Toast.LENGTH_SHORT).show();
                        reset.setEnabled(true);
                    }
                });
            }
        });

        go_back_to_login.setOnClickListener(v -> {
            Intent intent = new Intent(forgetPassword.this, login.class);
            startActivity(intent);
            finish();
        });

    }
}