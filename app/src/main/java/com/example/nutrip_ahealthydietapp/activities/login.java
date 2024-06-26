package com.example.nutrip_ahealthydietapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrip_ahealthydietapp.R;

public class login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        TextView back_to_reg=findViewById(R.id.go_to_reg);
        back_to_reg.setOnClickListener(v -> {
            startActivity(new Intent(login.this, register.class));
        });
    }
}