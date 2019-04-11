package com.jpetalice.cba533.projetandroid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.jpetalice.cba533.projetandroid.data.Recipe;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat Tag
    private static final String LOG = "DatabaseHelper";

    // Database Informations
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EasyRecipe";

    // Tables
    private static final String RECIPE = "tbl_recipe";
    private static final String PASSWORD = "tbl_password";

    // Tables Create Statements
    private static final String CREATE_TBL_RECIPE = "CREATE TABLE tbl_recipe( Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT, Descr TEXT, Photo BLOB );";
    private static final String CREATE_TBL_PASSWORD = "CREATE TABLE tbl_password( Descr TEXT, Value INTEGER );";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TBL_RECIPE);
        db.execSQL(CREATE_TBL_PASSWORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + RECIPE);
        db.execSQL(" DROP TABLE IF EXISTS " + PASSWORD);
        onCreate(db);
    }

    public void deleteFromDatabase(){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(" DROP TABLE IF EXISTS " + RECIPE);
        db.execSQL(" DROP TABLE IF EXISTS " + PASSWORD);
        onCreate(db);
    }

    public void addRecipe(Recipe recipe){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", recipe.getName());
        values.put("Descr", recipe.getDescr());
        db.insert("tbl_recipe", null, values);
        db.close();
    }

    public List<Recipe> getRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM tbl_recipe";

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()){
            do {
                Recipe currentRecipe = new Recipe();
                currentRecipe.setName(c.getString(c.getColumnIndex("Name")));
                currentRecipe.setDescr(c.getString(c.getColumnIndex("Descr")));
                recipes.add(currentRecipe);
            } while (c.moveToNext());
        }
        c.close();
        return recipes;
    }

    public void deleteRecipe(String recipeId){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(RECIPE, "Id=" + recipeId, null);
    }
}
