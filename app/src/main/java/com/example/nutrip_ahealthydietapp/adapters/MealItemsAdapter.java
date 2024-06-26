package com.example.nutrip_ahealthydietapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.activities.MealItemsActivity;
import com.example.nutrip_ahealthydietapp.models.MealItemModel;

import java.util.List;

public class MealItemsAdapter extends RecyclerView.Adapter<MealItemsAdapter.ViewHolder> {

    private final Context context;
    private final List<MealItemModel> list;
    private OnItemClickListener onItemClickListener;
    private OnFavoriteButtonClickListener onFavoriteButtonClickListener;

    public interface OnItemClickListener {
        void onItemClick(MealItemModel item);
    }
    public interface OnFavoriteButtonClickListener {
        void onFavoriteClick(MealItemModel item, boolean isChecked);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    public void setOnFavoriteButtonClickListener(OnFavoriteButtonClickListener listener) {
        this.onFavoriteButtonClickListener = listener;
    }

    public MealItemsAdapter(Context context, List<MealItemModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MealItemsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealItemsAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(MealItemsAdapter.ViewHolder holder, int position) {
        MealItemModel model = list.get(position);
        holder.imageView.setImageResource(model.getImage());
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());

        // Set the tag to the item view for later retrieval
        holder.itemView.setTag(model);

        // Set click listener for the whole item view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(model);
                }
            }
        });

        // Set click listener for the favorite button
        holder.favoriteButton.setChecked(model.isFavorite()); // Update favorite button state
        holder.favoriteButton.setOnCheckedChangeListener(null); // Clear previous listener to prevent multiple calls
        holder.favoriteButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (onFavoriteButtonClickListener != null) {
                onFavoriteButtonClickListener.onFavoriteClick(model, isChecked);
                ((MealItemsActivity)context).onFavoriteButtonClick(model, isChecked);




                // Show toast message
                if (isChecked) {
                    Toast.makeText(context.getApplicationContext(), "Added to favorites: " + model.getName(), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(context.getApplicationContext(), "Removed from favorites: " + model.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, description;
        ToggleButton favoriteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.meal_item_image);
            name = itemView.findViewById(R.id.meal_item_name);
            description = itemView.findViewById(R.id.meal_item_description);
            favoriteButton = itemView.findViewById(R.id.favorite_button);
        }
    }



}
