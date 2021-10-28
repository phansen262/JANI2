package com.sticknology.jani2.app_objects.trainingplan.exercise;

import com.sticknology.jani2.app_objects.other.Equipment;

import java.util.List;

public class Exercise {

    private String mName;
    private int mReps;
    private List<Equipment> mEquipment;

    public Exercise(String name, int reps, List<Equipment> equipment){

        mName = name;
        mReps = reps;
        mEquipment = equipment;
    }

    public String getName(){
        return mName;
    }

    public int getReps(){
        return mReps;
    }


}
