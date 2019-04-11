package com.jpetalice.cba533.projetandroid.data;

import java.sql.Blob;

public class Recipe {

    private String Name;
    private String Descr;
    private Blob Photo;

    public Recipe() {
        Name = "";
        Descr = "";
        Photo = null;
    }

    public Recipe(String name, String descr) {
        Name = name;
        Descr = descr;
        Photo = null;
    }

    public String getDescr() {
        return Descr;
    }

    public void setDescr(String descr) {
        Descr = descr;
    }

    public Blob getPhoto() {
        return Photo;
    }

    public void setPhoto(Blob photo) {
        Photo = photo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
