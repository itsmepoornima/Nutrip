package com.example.nutrip_ahealthydietapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meals.db";
    private static final int DATABASE_VERSION = 2; // Update version number

    private static final String TABLE_FAVORITES = "favorites";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_MEAL_NAME = "meal_name";
    private static final String COLUMN_MEAL_DESCRIPTION = "meal_description";
    private static final String COLUMN_MEAL_IMAGE = "meal_image";
    private static final String COLUMN_IS_FAVORITE = "is_favorite"; // New column

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_MEAL_NAME + " TEXT,"
                + COLUMN_MEAL_DESCRIPTION + " TEXT,"
                + COLUMN_MEAL_IMAGE + " INTEGER,"
                + COLUMN_IS_FAVORITE + " INTEGER,"
                + "UNIQUE(" + COLUMN_MEAL_NAME + ") ON CONFLICT IGNORE" // Unique constraint
                + ")";
        db.execSQL(CREATE_FAVORITES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {
            db.execSQL("ALTER TABLE " + TABLE_FAVORITES + " ADD COLUMN " + COLUMN_IS_FAVORITE + " INTEGER DEFAULT 0");
        }
    }

    // Method to add a meal to favorites
    public boolean addFavorite(String name, String description, int image) {
        Log.d("DatabaseHelper", "addFavorite called for: " + name);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_MEAL_NAME, name);
        values.put(COLUMN_MEAL_DESCRIPTION, description);
        values.put(COLUMN_MEAL_IMAGE, image);
        values.put(COLUMN_IS_FAVORITE, 1); // Mark as favorite

        long result = db.insert(TABLE_FAVORITES, null, values);
        db.close();
        return result != -1;
    }

    // Method to remove a meal from favorites
    public boolean removeFavorite(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_IS_FAVORITE, 0); // Unmark as favorite

        int result = db.update(TABLE_FAVORITES, values, COLUMN_MEAL_NAME + "=?", new String[]{name});
        db.close();
        return result > 0;
    }

    // Method to check if a meal is a favorite
    public boolean isFavorite(String name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_FAVORITES, new String[]{COLUMN_IS_FAVORITE}, COLUMN_MEAL_NAME + "=? AND " + COLUMN_IS_FAVORITE + "=1", new String[]{name}, null, null, null);
        boolean isFavorite = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return isFavorite;
    }
}
