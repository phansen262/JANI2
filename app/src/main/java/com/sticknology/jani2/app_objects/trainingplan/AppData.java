package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_operations.ListMethods;

import java.util.List;
import java.util.Set;

public abstract class AppData {

    protected String mName;
    protected DataMap mAttributes;

    protected AppData(String name, DataMap attributes){

        mName = name;
        mAttributes = attributes;
    }

    protected AppData(AppData appData){
        this.mName = appData.mName;
        this.mAttributes = appData.mAttributes;
    }

    //Name get/set methods
    public String getName(){
        return mName;
    }
    public void setName(String name){mName = name;}

    //Interact with Attributes
    public void putAttribute(String key, List<String> payload){
        mAttributes.put(key, payload);
    }
    public List<String> getAttributeItem(String key){
        return mAttributes.get(key);
    }
    public Set<String> getUsedAttributes(){return mAttributes.keySet();}

    protected DataMap getAttributes(){return mAttributes;}

    //Returns string value of attribute
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
