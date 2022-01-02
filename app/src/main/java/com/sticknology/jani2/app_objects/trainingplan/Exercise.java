package com.sticknology.jani2.app_objects.trainingplan;

import java.io.Serializable;
import java.util.HashMap;

public class Exercise implements Serializable {

    private String mName;
    private String mDescription;
    private String mType;

    private HashMap<String, Object> mAttributes;

    public Exercise(String name, String description, String type, HashMap<String, Object> attributes){

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
    public void addAttribute(String key, Object payload){
        mAttributes.put(key, payload);
    }

    public HashMap<String, Object> getAttributes(){return mAttributes;}

    public Object getAttributeItem(String key){
        return mAttributes.get(key);
    }
}
