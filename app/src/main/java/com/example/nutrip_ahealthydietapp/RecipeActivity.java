package com.example.nutrip_ahealthydietapp;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RecipeActivity extends AppCompatActivity {

    private ImageView mealImageView;
    private TextView mealNameTextView;
    private TextView mealDescriptionTextView;
    private TextView mealInstructionsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        mealImageView = findViewById(R.id.meal_image);
        mealNameTextView = findViewById(R.id.meal_name);
        mealDescriptionTextView = findViewById(R.id.meal_description);
        mealInstructionsTextView = findViewById(R.id.meal_instructions);

        // Get data from Intent
        if (getIntent().hasExtra("mealName") && getIntent().hasExtra("mealDescription") &&
                getIntent().hasExtra("mealImage") && getIntent().hasExtra("mealInstructions")) {

            String mealName = getIntent().getStringExtra("mealName");
            String mealDescription = getIntent().getStringExtra("mealDescription");
            int mealImage = getIntent().getIntExtra("mealImage", -1);
            String mealInstructions = getIntent().getStringExtra("mealInstructions");

            // Set the data to the views
            mealNameTextView.setText(mealName);
            mealDescriptionTextView.setText(mealDescription);
            mealInstructionsTextView.setText(mealInstructions);
            if (mealImage != -1) {
                mealImageView.setImageResource(mealImage);
            }
        }
    }
}
