package com.example.nutrip_ahealthydietapp.models;

public class MealItemModel {
    private int image;
    private String name;
    private String description;
    private boolean isFavorite; // Add a field to store favorite state

    public MealItemModel(int image, String name, String description) {
        this.image = image;
        this.name = name;
        this.description = description;
        this.isFavorite = false; // Default is not favorite
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

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
