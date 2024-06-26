package com.example.nutrip_ahealthydietapp.models;

import android.util.EventLogTags;

import java.util.ArrayList;

public class HomeHorModel {
    private int image;
    private String name;
    private String description; // New field for description
    private ArrayList<HomeVerModel> homeVerModels;



    public HomeHorModel(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
    }

    public HomeHorModel(int image, String name, ArrayList<HomeVerModel> homeVerModels) {
        this.image = image;
        this.name = name;
        this.homeVerModels = homeVerModels;
    }


    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<HomeVerModel> getHomeVerModels() {
        return homeVerModels;
    }

    public void setHomeVerModels(ArrayList<HomeVerModel> homeVerModels) {
        this.homeVerModels = homeVerModels;
    }
}
