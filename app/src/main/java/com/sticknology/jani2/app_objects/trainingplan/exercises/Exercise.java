package com.sticknology.jani2.app_objects.trainingplan.exercises;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Exercise implements Serializable {

    private String mName;
    private String mDescription;
    private String mType;

    private HashMap<String, List<String>> mAttributes;

    //Empty constructor to use add/set functions to build instead of constructor
    public Exercise(){

        mAttributes = new HashMap<>();
    }

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

    //Baseline set methods
    public void setName(String name){mName = name;}
    public void setDescription(String description){mDescription = description;}
    public void setType(String type){mType = type;}

    //Interact with Attributes
    public void addAttribute(String key, List<String> payload){
        mAttributes.put(key, payload);
    }

    public void addAttributeMap(HashMap<String, List<String>> attributeMap){mAttributes = attributeMap;}

    public HashMap<String, List<String>> getAttributes(){return mAttributes;}

    public List<String> getAttributeItem(String key){
        return mAttributes.get(key);
    }
}
