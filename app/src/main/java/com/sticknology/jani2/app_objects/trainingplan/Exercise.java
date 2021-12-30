package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.Carrier;

import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable {

    private String mName;
    private String mDescription;
    private String mType;

    private ArrayList<Carrier> mAttributes = new ArrayList<>();

    private ArrayList<Carrier> mData = new ArrayList<>();

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

        if(mAttributes != null) {
            for (int i = 0; i < mAttributes.size(); i++) {

                if (mAttributes.get(i).getKey().matches("MGROUP")) {
                    inter += (String) mAttributes.get(i).getPayload() + "@!@";
                }
            }
            return inter.split("@!@");
        }

        return null;
    }

    //Interact with Attributes
    public void addAttributes(ArrayList<Carrier> newAttributes){mAttributes = newAttributes;}

    public ArrayList<Carrier> getAttributes(){return mAttributes;}

    //Interact with exercise data
    public void addData(ArrayList<Carrier> newData){
        mData = newData;
    }

    public ArrayList<Carrier> getData(){
        return mData;
    }
}
