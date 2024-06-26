package com.example.nutrip_ahealthydietapp.ui.dailymeal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.adapters.DailyMealAdapter;
import com.example.nutrip_ahealthydietapp.models.DailyMealModel;
import com.example.nutrip_ahealthydietapp.activities.MealItemsActivity;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {
    RecyclerView recyclerView;
    DailyMealAdapter dailyMealAdapter;
    List<DailyMealModel> dailyMealModelList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.daily_meal_fragment, container, false);
        recyclerView = root.findViewById(R.id.daily_meal_rec);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        dailyMealModelList = new ArrayList<>();
        dailyMealModelList.add(new DailyMealModel(R.drawable.healthybreakfast, "Healthy Breakfast", "Fast and healthy breakfast"));
        dailyMealModelList.add(new DailyMealModel(R.drawable.healthylunch, "Lunch", "Mouth-watering lunch"));
        dailyMealModelList.add(new DailyMealModel(R.drawable.healthydinner, "Dinner", "Light and satisfying dinner"));
        dailyMealModelList.add(new DailyMealModel(R.drawable.beverages, "Beverages", "Drinkables"));
        dailyMealModelList.add(new DailyMealModel(R.drawable.snacks, "Snacks", "Healthy and instant snacks"));

        dailyMealAdapter = new DailyMealAdapter(requireContext(), dailyMealModelList);
        recyclerView.setAdapter(dailyMealAdapter);

        dailyMealAdapter.setOnItemClickListener(new DailyMealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DailyMealModel item) {
                Intent intent = new Intent(getActivity(), MealItemsActivity.class);
                intent.putExtra("mealCategory", item.getName());
                startActivity(intent);
            }
        });

        return root;
    }
}
