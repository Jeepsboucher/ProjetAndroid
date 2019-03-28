package com.jpetalice.cba533.projetandroid;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {

        //information of database
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "recipesDB.db";

        //initialize the database
        public static final String TBL_RECIPE = "CREATE TABLE tbl_recipe( Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name TEXT, Descr TEXT, Photo BLOB );";
        public static final String TBL_PASSWORD = "CREATE TABLE tbl_password( Descr TEXT, Value INTEGER );";


        public Database(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(TBL_RECIPE);
            db.execSQL(TBL_PASSWORD);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            //db.execSQL("DROP " + DATABASE_NAME);
            onCreate(db);
        }

        public void addRecipe(Recipe recipe){
            ContentValues values = new ContentValues();
            values.put("Name", recipe.getName());
            values.put("Descr", recipe.getDescr());
            SQLiteDatabase db = getWritableDatabase();
            db.insert("tbl_recipe", null, values);
            db.close();
        }

        public List<Recipe> getRecipes() {
            List<Recipe> recipes = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT * FROM tbl_recipe";

            Cursor cursor = db.execSQL(query);
            if (cursor.moveToNext()){
                while (cursor != null) {
                    Recipe currentRecipe = new Recipe();
                    currentRecipe.setName(cursor.getString(0));
                    currentRecipe.setDescr(cursor.getString(1));
                    recipes.add(currentRecipe)
                    cursor.moveToNext();
                }
            }

            cursor.close();
        return recipes;

        }

        public void deleteRecipe(String recipeId){
            SQLiteDatabase db = getWritableDatabase();
            db.execSQL("DELETE FROM tbl_recipe WHERE Id = " + recipeId + ";");
        }
}
