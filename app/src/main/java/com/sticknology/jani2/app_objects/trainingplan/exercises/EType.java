package com.sticknology.jani2.app_objects.trainingplan.exercises;

public enum EType {

    NONE("None"),
    CARDIO("Cardio"),
    STRENGTH("Strength"),
    WEIGHTS("Weights"),
    YOGA("Yoga");

    String displayName;
    EType(String name){
        displayName = name;
    }
    public String getName(){return this.displayName;}
}
