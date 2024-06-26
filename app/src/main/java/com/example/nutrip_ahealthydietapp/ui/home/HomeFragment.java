package com.example.nutrip_ahealthydietapp.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.adapters.HomeHorAdapter;
import com.example.nutrip_ahealthydietapp.adapters.HomeVerAdapter;
import com.example.nutrip_ahealthydietapp.adapters.UpdateVerticalRec;
import com.example.nutrip_ahealthydietapp.models.HomeHorModel;
import com.example.nutrip_ahealthydietapp.models.HomeVerModel;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements UpdateVerticalRec, HomeVerAdapter.OnItemClickListener {  // Implement OnItemClickListener

    RecyclerView homeHorizontalRec, homeVerticalRec;
    ArrayList<HomeHorModel> homeHorModelLists;
    HomeHorAdapter homeHorAdapter;
    TextView descriptionTextView;

    ArrayList<HomeVerModel> homeVerModelLists;
    HomeVerAdapter homeVerAdapter;
    TextView itemDescription;  // Add this line

    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        homeVerticalRec = root.findViewById(R.id.home_ver_rec);
        homeHorizontalRec = root.findViewById(R.id.home_hor_rec);
        descriptionTextView = root.findViewById(R.id.descriptionTextView);

        // Setting up the horizontal RecyclerView
        homeHorizontalRec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        homeHorModelLists = new ArrayList<>();
        homeHorModelLists.add(new HomeHorModel(R.drawable.juices, "Juices","healthy juices"));
        homeHorModelLists.add(new HomeHorModel(R.drawable.quickbites, "Quick Bites","fast eatables"));
        homeHorModelLists.add(new HomeHorModel(R.drawable.mbreakfast, "5 mins breakfast","easy to prepare breakfast"));
        homeHorModelLists.add(new HomeHorModel(R.drawable.south_indian, "Traditional South","south indian dishes"));
        homeHorModelLists.add(new HomeHorModel(R.drawable.healreci, "High on Taste","simple to prepare"));

        homeHorAdapter = new HomeHorAdapter(this, getActivity(), homeHorModelLists);
        homeHorizontalRec.setAdapter(homeHorAdapter);
        homeHorizontalRec.setHasFixedSize(true);
        homeHorizontalRec.setNestedScrollingEnabled(false);

        // Setting up the vertical RecyclerView
        homeVerModelLists = new ArrayList<>();
        homeVerAdapter = new HomeVerAdapter(getActivity(), homeVerModelLists, this);  // Pass this as the listener
        homeVerticalRec.setAdapter(homeVerAdapter);
        homeVerticalRec.setHasFixedSize(true);
        homeVerticalRec.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return root;
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {
        homeVerAdapter = new HomeVerAdapter(getContext(), list, this);  // Pass this as the listener
        homeVerAdapter.notifyDataSetChanged();
        homeVerticalRec.setAdapter(homeVerAdapter);
    }
    @Override
    public void onItemClick(HomeVerModel item) {
        // Update descriptionTextView with the clicked item's description
        descriptionTextView.setText(item.getDescription());
    }
}
