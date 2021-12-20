package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.Carrier;

import java.util.ArrayList;

public class Exercise {

    private String mName;
    private String mDescription;

    public Exercise(String name, String description, String type, ArrayList<Carrier> carrier){

        mName = name;
        mDescription = description;
    }

    //Baseline get methods
    public String getName(){
        return mName;
    }

    public String getDescription(){return mDescription;}
}
