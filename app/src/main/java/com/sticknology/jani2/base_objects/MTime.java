package com.sticknology.jani2.base_objects;

public class MTime {

    protected int mSeconds;

    //Base Constructors
    public MTime(int hours, int minutes, int seconds){

        mSeconds = hours * 3600 + minutes * 60 + seconds;
    }

    public MTime(int seconds){

        mSeconds = seconds;
    }

    //Get Display String
    public String getDispString(){

        String buildString = "";
        boolean hasHours = false;

        if(getHours() != 0){
            buildString += getHours() + ":";
            hasHours = true;
        }
        if(getMinutes() < 10 && hasHours){
            buildString += "0" + getMinutes() + ":";
        } else {
            buildString += getMinutes() + ":";
        }
        if(getSeconds() < 10){
            buildString += "0" + getSeconds();
        } else {
            buildString += getSeconds();
        }

        return buildString;
    }

    //Subtracts Duration From Time
    public void subtractDuration(MTime time){

        mSeconds -= time.getTotalSeconds();
    }

    //Add two durations together
    public void addDuration(MTime time){

        mSeconds += time.getTotalSeconds();
    }

    //Start of getters/object reset
    public int getHours(){return mSeconds / 3600;}
    public int getMinutes(){return (mSeconds % 3600) / 60;}
    public int getSeconds(){return (mSeconds % 3600) % 60;}
    public int getTotalSeconds(){return mSeconds;}

    public void resetTime(int seconds){mSeconds = seconds;}
    public void resetTime(int hours, int minutes, int seconds){
        mSeconds = hours * 3600 + minutes * 60 + seconds;
    }
    //End of getters/object reset
}
