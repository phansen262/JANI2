package com.sticknology.jani2.app_objects.trainingplan.exercises;

import com.sticknology.jani2.base_operations.ListMethods;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Exercise implements Serializable {

    protected String mName;

    protected HashMap<String, List<String>> mAttributes;

    //Empty constructor to use add/set functions to build instead of constructor
    public Exercise(){

        mAttributes = new HashMap<>();
    }

    public Exercise(String name, HashMap<String, List<String>> attributes){

        mName = name;
        mAttributes = attributes;
    }

    //Baseline get methods
    public String getName(){
        return mName;
    }

    //Baseline set methods
    public void setName(String name){mName = name;}

    //Interact with Attributes
    public void addAttribute(String key, List<String> payload){
        mAttributes.put(key, payload);
    }

    public void addAttributeMap(HashMap<String, List<String>> attributeMap){mAttributes = attributeMap;}

    public HashMap<String, List<String>> getAttributes(){return mAttributes;}

    public List<String> getAttributeItem(String key){
        return mAttributes.get(key);
    }

    public String getAttributeString(String key, String[] delimiters){

        List<String> attribute = mAttributes.get(key);
        if(attribute == null){
            return "ERROR IN GETATTRIBUTE STRING FOR EXERCISE";
        } else if(attribute.size() == 1){
            return attribute.get(0);
        } else {
            return ListMethods.joinList(attribute, delimiters);
        }
    }

    public String getAttributeString(String key){

        List<String> attribute = mAttributes.get(key);
        if(attribute == null){
            return "ERROR IN GETATTRIBUTE STRING FOR EXERCISE";
        } else if(attribute.size() == 1){
            return attribute.get(0);
        } else {
            return ListMethods.joinList(attribute, new String[]{", ", ", and ", " and "});
        }
    }
}
