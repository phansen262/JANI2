package com.sticknology.jani2.app_objects.trainingplan.exercise;

public class Exercise {

    private String mName;
    private String mDescription;

    //False if currently only used as info / display, True if exercise has been activated
    //^Activated if has been used or assigned rep/duration info, or other info delegating specific usage
    private boolean isActive = false;

    private int mReps;

    public Exercise(String name, String description){

        mName = name;
        mDescription = description;
    }

    //Baseline get methods
    public String getName(){
        return mName;
    }

    public String getDescription(){return mDescription;}

    //Method to activate exercise
    public void setActive(){isActive = true;}


}
