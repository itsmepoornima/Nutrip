package com.example.nutrip_ahealthydietapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.models.HomeVerModel;
import java.util.ArrayList;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HomeVerModel> list;
    private OnItemClickListener listener;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list, OnItemClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_vertical_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HomeVerModel item = list.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.name.setText(item.getName());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener.onItemClick(item);
                return true;// Notify listener of item click
            }
        });

        holder.itemView.setOnLongClickListener(v -> {
            // Display a unique toast message for each item
            String toastMessage = "This is : " + item.getDescription();
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            return true;
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });

        holder.itemView.setOnClickListener(v -> {
            // Display a unique toast message for each item
            String toastMessage = "You Clicked : " + item.getName();
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name = itemView.findViewById(R.id.name);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(HomeVerModel item);
    }
}

