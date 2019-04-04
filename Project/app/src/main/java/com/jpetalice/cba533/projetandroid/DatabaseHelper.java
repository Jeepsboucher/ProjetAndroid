package com.jpetalice.cba533.projetandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat Tag
    private static final String LOG = "DatabaseHelper";

    // Database Informations
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EasyRecipe";

    // Tables
    private static final String RECIPE = "recipe";
    private static final String PASSWORD = "password";

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
                currentRecipe.setName(c.getString(0));
                currentRecipe.setDescr(c.getString(1));
                recipes.add(currentRecipe);
            } while (c.moveToNext());
        }
        c.close();
        return recipes;

    }

    public void deleteRecipe(String recipeId){
        //SQLiteDatabase db = getWritableDatabase();
        //db.execSQL("DELETE FROM tbl_recipe WHERE Id = " + recipeId + ";");
    }
}
