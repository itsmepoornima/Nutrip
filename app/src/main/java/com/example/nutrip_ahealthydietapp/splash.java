package com.example.nutrip_ahealthydietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.nutrip_ahealthydietapp.activities.MainActivity1;
import com.example.nutrip_ahealthydietapp.activities.login;
import com.example.nutrip_ahealthydietapp.activities.register;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.splash);
        AppCompatButton reg_btn=findViewById(R.id.register_btn);
        TextView sign_up_txt=findViewById(R.id.sign_up_txt);
        ImageView guest_btn=findViewById(R.id.guest_btn);

        guest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(splash.this, MainActivity1.class);
                startActivity(intent);
            }
        });

        sign_up_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(splash.this, login.class);
                startActivity(intent);
            }
        });

        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(splash.this, register.class);
                startActivity(intent);
            }
        });



    }
    }
