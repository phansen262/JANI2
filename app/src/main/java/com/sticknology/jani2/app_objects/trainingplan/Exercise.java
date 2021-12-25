package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.Carrier;

import java.util.ArrayList;

public class Exercise {

    private String mName;
    private String mDescription;
    private String mType;

    private ArrayList<Carrier> mAttributes = new ArrayList<>();

    public Exercise(String name, String description, String type, ArrayList<Carrier> carrierList){

        mName = name;
        mDescription = description;
        mType = type;
        mAttributes = carrierList;
    }

    //Baseline get methods
    public String getName(){
        return mName;
    }

    public String getDescription(){return mDescription;}

    public String getType(){return mType;}

    public String[] getMuscleGroups(){

        String inter = "";

        for(int i = 0; i < mAttributes.size(); i++){

            if(mAttributes.get(i).getKey().matches("MGROUP")){
                inter += (String) mAttributes.get(i).getPayload() + "@!@";
            }
        }

        return inter.split("@!@");
    }
}
