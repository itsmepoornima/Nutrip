package com.example.nutrip_ahealthydietapp.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.databinding.DailyMealFragmentBinding;

public class DailyMealFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.daily_meal_fragment, container, false);
        return root;
    }

}