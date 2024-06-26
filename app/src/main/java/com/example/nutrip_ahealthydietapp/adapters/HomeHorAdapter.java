package com.example.nutrip_ahealthydietapp.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nutrip_ahealthydietapp.R;
import com.example.nutrip_ahealthydietapp.models.HomeHorModel;
import com.example.nutrip_ahealthydietapp.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {
    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list){
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    public HomeHorAdapter(Context context, List<HomeHorModel> homeHorModelLists) {
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel(R.drawable.lemon, "lemon", "A refreshing lemon juice."));
            homeVerModels.add(new HomeVerModel(R.drawable.abcjuice, "ABC juice", "A healthy juice made from apple, beetroot, and carrot."));
            homeVerModels.add(new HomeVerModel(R.drawable.cargojuice, "Cargo juice", "A blend of carrot and orange."));
            homeVerModels.add(new HomeVerModel(R.drawable.candb, "CarBeet Juice", "Carrot and beetroot juice for a healthy start."));
            homeVerModels.add(new HomeVerModel(R.drawable.tropical_punch, "tropical punch", "A tropical punch juice with a mix of various fruits."));
            homeVerModels.add(new HomeVerModel(R.drawable.watermelon, "Watermelon mint", "A cooling watermelon mint juice."));
            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();
                if (position == 0) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.lemon, "lemon", "A refreshing lemon juice."));
                    homeVerModels.add(new HomeVerModel(R.drawable.abcjuice, "ABC juice", "A healthy juice made from apple, beetroot, and carrot."));
                    homeVerModels.add(new HomeVerModel(R.drawable.cargojuice, "Cargo juice", "A blend of carrot and orange."));
                    homeVerModels.add(new HomeVerModel(R.drawable.candb, "CarBeet Juice", "Carrot and beetroot juice for a healthy start."));
                    homeVerModels.add(new HomeVerModel(R.drawable.tropical_punch, "tropical punch", "A tropical punch juice with a mix of various fruits."));
                    homeVerModels.add(new HomeVerModel(R.drawable.watermelon, "Watermelon mint", "A cooling watermelon mint juice."));
                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 1) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.barfi, "Peanut burfi", "A sweet treat made from peanuts."));
                    homeVerModels.add(new HomeVerModel(R.drawable.coconutdryfruitladdu, "Coconut dry fruit laddu", "Laddu made with coconut and dry fruits."));
                    homeVerModels.add(new HomeVerModel(R.drawable.peanutmasala, "Peanut masala", "A spicy snack made from peanuts."));
                    homeVerModels.add(new HomeVerModel(R.drawable.poriurundai, "Pori Urundai", "A traditional puffed rice sweet."));
                    homeVerModels.add(new HomeVerModel(R.drawable.ravaladdu, "Rava laddu", "A delicious sweet made from semolina."));
                    homeVerModels.add(new HomeVerModel(R.drawable.wholewheatbuttercookies, "Whole wheat butter cookie", "Cookies made from whole wheat and butter."));
                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 2) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.breakfast, "Toast egg bread", "A quick breakfast of toast with eggs."));
                    homeVerModels.add(new HomeVerModel(R.drawable.eggchapathi, "Egg chapathi", "Chapathi filled with eggs."));
                    homeVerModels.add(new HomeVerModel(R.drawable.frenchtoast, "French toast", "A sweet breakfast made from bread and eggs."));
                    homeVerModels.add(new HomeVerModel(R.drawable.onionutapam, "Onion utapam", "A South Indian breakfast of onion utapam."));
                    homeVerModels.add(new HomeVerModel(R.drawable.podidly, "Podi idly", "Idly coated with spicy podi."));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich, "Mint chutney sandwich", "A sandwich with mint chutney filling."));
                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 3) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.ragikoozh, "Ragi koozh", "A traditional South Indian drink made from ragi."));
                    homeVerModels.add(new HomeVerModel(R.drawable.kambukoozh, "Kambu koozh", "A cooling drink made from pearl millet."));
                    homeVerModels.add(new HomeVerModel(R.drawable.curdrice, "Curd rice", "A simple dish made from rice and curd."));
                    homeVerModels.add(new HomeVerModel(R.drawable.idiyapam, "Idiyapam", "A South Indian breakfast dish made from rice flour."));
                    homeVerModels.add(new HomeVerModel(R.drawable.puttu, "Puttu", "A Kerala breakfast dish made from rice flour and coconut."));
                    homeVerModels.add(new HomeVerModel(R.drawable.ragidosa, "Ragi dosa", "A healthy dosa made from ragi."));
                    homeVerModels.add(new HomeVerModel(R.drawable.sambaravaupma, "Samba rava upma", "Upma made from samba rava."));
                    updateVerticalRec.callBack(position, homeVerModels);
                } else if (position == 4) {
                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.sundal, "Sundal", "A healthy snack made from chickpeas."));
                    homeVerModels.add(new HomeVerModel(R.drawable.grilledcorn, "Grilled corn", "Corn grilled to perfection."));
                    homeVerModels.add(new HomeVerModel(R.drawable.panakizhangu, "Panakizhangu", "A traditional South Indian snack."));
                    homeVerModels.add(new HomeVerModel(R.drawable.pattani, "Beach side pattani", "A popular beach side snack made from peas."));
                    homeVerModels.add(new HomeVerModel(R.drawable.peanutchat, "Peanut chat", "A tangy and spicy peanut chat."));
                    homeVerModels.add(new HomeVerModel(R.drawable.sweetpotato, "Sweet potato", "Sweet potatoes cooked to perfection."));
                    homeVerModels.add(new HomeVerModel(R.drawable.blacksundal, "Black sundal", "Sundal made from black chickpeas."));
                    homeVerModels.add(new HomeVerModel(R.drawable.sweetcorn, "Sweet corn", "Sweet corn cooked with butter."));
                    updateVerticalRec.callBack(position, homeVerModels);
                }
              holder.cardView.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      String description = list.get(position).getDescription();
                      Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
                  }
              });

            }
        });

        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select = false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
            } else {
                holder.cardView.setBackgroundResource(R.color.bg_clr1);
            }
        }
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_txt);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
