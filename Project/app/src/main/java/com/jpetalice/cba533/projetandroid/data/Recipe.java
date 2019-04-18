package com.jpetalice.cba533.projetandroid.data;

import android.graphics.Bitmap;

import java.sql.Blob;

public class Recipe {

    private String Name;
    private String Descr;
    private Bitmap Photo;

    public Recipe() {
        Name = "";
        Descr = "";
        Photo = null;
    }

    public Recipe(String name, String descr, Bitmap photo) {
        Name = name;
        Descr = descr;
        Photo = photo;
    }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String descr) {
        Descr = descr;
    }

    public Bitmap getPhoto() {
        return Photo;
    }

    public void setPhoto(Bitmap photo) {
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
