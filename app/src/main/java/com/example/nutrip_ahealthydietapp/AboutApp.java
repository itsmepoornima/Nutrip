package com.example.nutrip_ahealthydietapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class AboutApp extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about_app, container, false);

        ImageView eat = root.findViewById(R.id.eat);
        Animation fadeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_animation);
        eat.startAnimation(fadeAnimation);

        eat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation fadeAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.fade_animation);
                eat.startAnimation(fadeAnimation);
            }
        });

        ImageView run = root.findViewById(R.id.run);
        Animation moveAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.move_animation);
        run.startAnimation(moveAnimation);

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation moveAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.move_animation);
                run.startAnimation(moveAnimation);
            }
        });

        ImageView cal = root.findViewById(R.id.cal);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation slideAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.slide_animation);
                cal.startAnimation(slideAnimation);
            }
        });

        ImageView calender = root.findViewById(R.id.calender);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), calender.class);
                startActivity(intent);
            }
        });

        return root;
    }
}
