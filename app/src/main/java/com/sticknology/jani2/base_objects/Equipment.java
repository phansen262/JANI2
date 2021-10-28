package com.sticknology.jani2.base_objects;

import android.media.Image;

public class Equipment {

    private String mName;
    private String mDescription;
    private Image mImage;

    //Base Constructor for has age
    public Equipment(String name, String description, Image image){

        mName = name;
        mDescription = description;
        mImage = image;
    }

    //Getters
    public String getName(){
        return mName;
    }

    public String getDescription(){
        return mDescription;
    }
}
