package com.sticknology.jani2.app_objects.trainingplan.exercises;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Exercise implements Serializable {

    private String mName;
    private String mDescription;
    private String mType;

    private HashMap<String, List<String>> mAttributes;

    public Exercise(String name, String description, String type, HashMap<String, List<String>> attributes){

        mName = name;
        mDescription = description;
        mType = type;
        mAttributes = attributes;
    }

    //Baseline get methods
    public String getName(){
        return mName;
    }

    public String getDescription(){return mDescription;}

    public String getType(){return mType;}

    //Interact with Attributes
    public void addAttribute(String key, List<String> payload){
        mAttributes.put(key, payload);
    }

    public HashMap<String, List<String>> getAttributes(){return mAttributes;}

    public List<String> getAttributeItem(String key){
        return mAttributes.get(key);
    }
}
