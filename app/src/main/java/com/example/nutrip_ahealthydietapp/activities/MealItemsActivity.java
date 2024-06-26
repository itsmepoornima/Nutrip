package com.example.nutrip_ahealthydietapp.activities;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.RecipeActivity;
import com.example.nutrip_ahealthydietapp.adapters.MealItemsAdapter;
import com.example.nutrip_ahealthydietapp.DatabaseHelper;
import com.example.nutrip_ahealthydietapp.models.MealItemModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MealItemsActivity extends AppCompatActivity implements MealItemsAdapter.OnItemClickListener {

    private static final int REQUEST_CODE_PERMISSIONS = 1001;

    RecyclerView recyclerView;
    MealItemsAdapter mealItemAdapter;
    List<MealItemModel> mealItemModelList;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_items);

        createNotificationChannel();

        recyclerView = findViewById(R.id.meal_items_rec);

        String mealCategory = getIntent().getStringExtra("mealCategory");
        setTitle(mealCategory);

        databaseHelper = new DatabaseHelper(this);
        try {
            databaseHelper.getReadableDatabase();
            Log.d("MealItemsActivity", "Database accessed");
        } catch (Exception e) {
            Log.e("MealItemsActivity", "Error accessing database", e);
        }

        mealItemModelList = new ArrayList<>();
        if (mealCategory.equals("Healthy Breakfast")) {
            mealItemModelList.add(new MealItemModel(R.drawable.ragidosa, "ragi dosa", "delicious ragi dosa with onions and chillies"));
            mealItemModelList.add(new MealItemModel(R.drawable.milletupma, "millet upma", "soft and tasty mixed veggies millet upma"));
            mealItemModelList.add(new MealItemModel(R.drawable.ragiupma, "ragi samiya Upma", "Soft and delicious ragi samiya upma"));
            mealItemModelList.add(new MealItemModel(R.drawable.pachaipayiridly, "green idly", "softy soft green gram idly"));
        } else if (mealCategory.equals("Lunch")) {
            mealItemModelList.add(new MealItemModel(R.drawable.kambukoozh, "kambu koozh", "Delicious gravy to drink"));
            mealItemModelList.add(new MealItemModel(R.drawable.ragikoozh, "ragi koozh", "Spicy rice with onions and chillies"));
            mealItemModelList.add(new MealItemModel(R.drawable.healthylunch, "meal", "Healthy lunch"));
            mealItemModelList.add(new MealItemModel(R.drawable.curdrice, "curd rice", "delicious curd rice with veggies"));
        } else if (mealCategory.equals("Dinner")) {
            mealItemModelList.add(new MealItemModel(R.drawable.idiyapam, "idiyapam", "Steamed rice noodles"));
            mealItemModelList.add(new MealItemModel(R.drawable.sambaravaupma, "millet upma", "Crispy fermented crepes"));
            mealItemModelList.add(new MealItemModel(R.drawable.puttu, "puttu", "Soft and delicious steamed puttu"));
        } else if (mealCategory.equals("Beverages")) {
            mealItemModelList.add(new MealItemModel(R.drawable.gingercoffe, "ginger coffee", "Delicious coffee"));
            mealItemModelList.add(new MealItemModel(R.drawable.sukkucoffee, "sukku coffee", "Refreshing coffee"));
            mealItemModelList.add(new MealItemModel(R.drawable.sukkutea, "sukku tea", "Refreshing tea"));
            mealItemModelList.add(new MealItemModel(R.drawable.turmilk, "turmeric milk", "healthy turmeric tea"));
            mealItemModelList.add(new MealItemModel(R.drawable.arugampuljuice, "grass juice", "energetic fresh juice "));
        } else if (mealCategory.equals("Snacks")) {
            mealItemModelList.add(new MealItemModel(R.drawable.panakizhangu, "panakizhangu", "Delicious panakizhangu"));
            mealItemModelList.add(new MealItemModel(R.drawable.coconutdryfruitladdu, "coconut laddu", "Healthy coconut laddu"));
            mealItemModelList.add(new MealItemModel(R.drawable.sweetcorn, "sweet corn", "boiled spicy corn"));
            mealItemModelList.add(new MealItemModel(R.drawable.snacks, "kuzhi paniyaram", "Crispy fermented crepes"));
            mealItemModelList.add(new MealItemModel(R.drawable.sweetpotato, "sweet potato", "sweet potato"));
        }

        mealItemAdapter = new MealItemsAdapter(this, mealItemModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mealItemAdapter);

        mealItemAdapter.setOnItemClickListener(this);
        mealItemAdapter.setOnFavoriteButtonClickListener(this::onFavoriteButtonClick);

        FloatingActionButton saveDataButton = findViewById(R.id.save_data_button);
        saveDataButton.setOnClickListener(v -> {
            String data = getMealDataAsString();
            checkAndRequestPermissions(data);
        });
    }

    private String getMealDataAsString() {
        StringBuilder data = new StringBuilder();
        for (MealItemModel item : mealItemModelList) {
            data.append(item.getName()).append(": ").append(item.getDescription()).append("\n");
        }
        return data.toString();
    }

    @Override
    public void onItemClick(MealItemModel item) {
        Intent intent = new Intent(MealItemsActivity.this, RecipeActivity.class);
        intent.putExtra("mealName", item.getName());
        intent.putExtra("mealDescription", item.getDescription());
        intent.putExtra("mealImage", item.getImage());

        // Add recipe instructions based on the selected item
        String instructions = getInstructionsForMeal(item.getName());
        intent.putExtra("mealInstructions", instructions);

        startActivity(intent);
    }
    private String getInstructionsForMeal(String mealName) {
        switch (mealName) {
            case "ragi dosa":
                return "1. Soak ragi and rice for 4 hours...\n2. Grind to a fine paste...\n3. Add chopped onions and chillies...\n4. Mix well...\n5. Serve";
            case "millet upma":
                return "1. Dry roast millet...\n2. Add vegetables and cook...\n3. Serve";
            case "ragi samiya Upma":
                return "1. Soak ragi samiya for 10 minutes...\n2. Cook with spices and vegitables...\n3. Serve";
            // Add more cases for other meals
            default:
                return "No instructions available.";
        }
    }

    public void onFavoriteButtonClick(MealItemModel item, boolean isFavorite) {
        if (isFavorite) {
            boolean success = databaseHelper.addFavorite(item.getName(), item.getDescription(), item.getImage());
            if (success) {
                Toast.makeText(this, "Added to favorites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to add to favorites", Toast.LENGTH_SHORT).show();
            }
        } else {
            boolean success = databaseHelper.removeFavorite(item.getName());
            if (success) {
                Toast.makeText(this, "Removed from favorites", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to remove from favorites", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void checkAndRequestPermissions(String data) {
        String[] permissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        List<String> permissionsToRequest = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsToRequest.add(permission);
            }
        }

        if (!permissionsToRequest.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionsToRequest.toArray(new String[0]), REQUEST_CODE_PERMISSIONS);
        } else {
            writeDataToSDCard(data);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with writing data
                FloatingActionButton saveDataButton = findViewById(R.id.save_data_button);
                saveDataButton.performClick();
            } else {
                Toast.makeText(this, "Permissions are required to use this feature", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "MealDataChannel";
            String description = "Channel for meal data notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("meal_data_channel", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void showNotification(String title, String content) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "meal_data_channel")
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE_PERMISSIONS);
            return;
        }
        notificationManager.notify(1, builder.build());
    }

    private void writeDataToSDCard(String data) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSIONS);
            return;
        }

        if (isExternalStorageWritable()) {
            File file = new File(getExternalFilesDir(null), "meal_data.txt");
            try (FileOutputStream fos = new FileOutputStream(file)) {
                fos.write(data.getBytes());
                showNotification("Data Saved", "Meal data saved to SD card successfully.");
            } catch (IOException e) {
                Log.e("MealItemsActivity", "Error writing to SD card", e);
                showNotification("Error", "Failed to save meal data to SD card.");
            }
        } else {
            Toast.makeText(this, "SD card not writable", Toast.LENGTH_SHORT).show();
        }
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public void onSaveDataButtonClick(View view) {
        String data = getMealDataAsString();
        checkAndRequestPermissions(data);
    }
}
