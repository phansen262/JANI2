package com.sticknology.jani2.base_objects;

import android.media.Image;

public class EquipmentAge extends Equipment {

    private int mLifeSpan;
    private int mAge;
    private boolean mIsConsumable;
    private String mLifeLabel;

    public EquipmentAge(String name, int lifespan, int age, boolean isConsumable, String lifeLabel,
                     String description, Image image){

        super(name, description, image);

        mLifeSpan = lifespan;
        mAge = age;
        mIsConsumable = isConsumable;
        mLifeLabel = lifeLabel;
    }

    //Getters
    public int getLifeSpan(){
        return mLifeSpan;
    }

    public int getAge(){
        return mAge;
    }

    public String getLifeLabel(){
        return mLifeLabel;
    }

    public int getLifeRemaining(){
        return mLifeSpan - mAge;
    }

    public boolean getIsConsumable(){
        return mIsConsumable;
    }

    //Setters
    public void setAge(int newAge){
        mAge = newAge;
    }

    public void setLifeSpan(int newSpan){
        mLifeSpan = newSpan;
    }

    //Decreasing life remaining (aging) equipment
    public void ageEquipment(int aging){
        if(getIsConsumable()) {
            mAge = 0;
            mLifeSpan -= aging;
        } else {
            mAge += aging;
        }
    }
}
