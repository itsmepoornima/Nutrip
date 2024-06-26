package com.example.nutrip_ahealthydietapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrip_ahealthydietapp.R;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        TextView login_back=findViewById(R.id.go_to_login);
        login_back.setOnClickListener(v -> {
            Intent intent=new Intent(register.this, login.class);
            startActivity(intent);
        });

    }
}