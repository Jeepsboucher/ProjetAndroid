package com.jpetalice.cba533.projetandroid.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.jpetalice.cba533.projetandroid.data.Recipe;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        recipe.getPhoto().compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();
        values.put("Photo", img);

        db.insert("tbl_recipe", null, values);
        db.close();
    }

    public void updateRecipe(Recipe recipe){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Name", recipe.getName());
        values.put("Descr", recipe.getDescr());

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        recipe.getPhoto().compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[] img = bos.toByteArray();
        values.put("Photo", img);

        db.update("tbl_recipe", values, "Id = " + recipe.getId(), null);
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
                currentRecipe.setId(c.getInt(c.getColumnIndex("Id")));

                byte[] data = c.getBlob(c.getColumnIndex("Photo"));
                if (data != null) {
                    if (data.length != 0){
                        ByteArrayInputStream imageStream = new ByteArrayInputStream(data);
                        Bitmap img = BitmapFactory.decodeStream(imageStream);
                        currentRecipe.setPhoto(img);
                    }
                }
                currentRecipe.setName(c.getString(c.getColumnIndex("Name")));
                currentRecipe.setDescr(c.getString(c.getColumnIndex("Descr")));
                recipes.add(currentRecipe);
            } while (c.moveToNext());
        }
        c.close();
        return recipes;
    }

    public Recipe getRecipe(int recipeId) {
        Recipe recipe = new Recipe();

        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM tbl_recipe WHERE tbl_recipe.Id = " + recipeId;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()){
            recipe.setId(c.getInt(c.getColumnIndex("Id")));

            byte[] data = c.getBlob(c.getColumnIndex("Photo"));
            if (data != null) {
                if (data.length != 0){
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(data);
                    Bitmap img = BitmapFactory.decodeStream(imageStream);
                    recipe.setPhoto(img);
                }
            }

            recipe.setName(c.getString(c.getColumnIndex("Name")));
            recipe.setDescr(c.getString(c.getColumnIndex("Descr")));
        }
        c.close();
        return recipe;
    }

    public void deleteRecipe(int RecipeId){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(RECIPE, "Id = " + RecipeId, null);
    }

    public void addOrUpdatePassword(Integer password){
        SQLiteDatabase dbReadable = getReadableDatabase();
        SQLiteDatabase dbWritable = getWritableDatabase();
        String selectQuery = "SELECT * FROM tbl_password";

        Cursor c = dbReadable.rawQuery(selectQuery, null);
        if(c.moveToFirst()) {
            ContentValues values = new ContentValues();
            values.put("Value", password);
            dbWritable.update(PASSWORD, values, "Descr='password'", null);
        } else {

            ContentValues values = new ContentValues();
            values.put("Descr", "password");
            values.put("Value", password);
            dbWritable.insert(PASSWORD, null, values);
        }
        dbWritable.close();
    }

    public void deletePassword() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(PASSWORD, "Descr='password'", null);
        db.close();
    }

    public boolean checkPassword(int password) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM tbl_password";

        Cursor c = db.rawQuery(selectQuery, null);
        boolean toReturn = false;
        if(c.moveToFirst()) {
            int dbPassword = c.getInt(c.getColumnIndex("Value"));
            if(password == dbPassword) {
                db.close();
                toReturn = true;
            }
        }
        db.close();
        return toReturn;
    }

    public boolean havePassword() {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM tbl_password";

        Cursor c = db.rawQuery(selectQuery, null);
        boolean toReturn = false;
        if(c.moveToFirst()) {
                toReturn = true;
        }
        db.close();
        return toReturn;
    }
}
