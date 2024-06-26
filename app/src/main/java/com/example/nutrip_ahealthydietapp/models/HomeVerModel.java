package com.example.nutrip_ahealthydietapp.models;

import java.util.ArrayList;

public class HomeVerModel{

        private int image;
        private String name;
        private String description;
       private ArrayList<HomeVerModel> homeVerModels;


        public HomeVerModel(int image, String name, String description) {
            this.image = image;
            this.name = name;
            this.description = description;
        }

    public HomeVerModel(int image, String name, ArrayList<HomeVerModel> homeVerModels) {
        this.image = image;
        this.name = name;
        this.homeVerModels = homeVerModels;
    }




    public int getImage() {
            return image;
        }

        public String getName() {
            return name;
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

