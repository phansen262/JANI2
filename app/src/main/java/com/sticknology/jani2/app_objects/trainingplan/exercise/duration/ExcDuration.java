package com.sticknology.jani2.app_objects.trainingplan.exercise.duration;

import com.sticknology.jani2.app_objects.trainingplan.exercise.Exercise;
import com.sticknology.jani2.app_objects.other.Equipment;
import com.sticknology.jani2.base_objects.MTime;

import java.util.List;

public class ExcDuration extends Exercise {

    private MTime mDuration;

    public ExcDuration(String name, String description){

        super(name, description);
    }

    public MTime getDuration(){
        return mDuration;
    }

    //To change interior MTime object, use getDuration above, modify using MTime methods,
    //and then use as parameter in below method to avoid using object variable outside class
    public void updateDuration(MTime newDuration){
        mDuration = newDuration;
    }
}
