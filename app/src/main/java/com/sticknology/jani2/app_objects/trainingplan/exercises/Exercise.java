package com.sticknology.jani2.app_objects.trainingplan.exercises;

import com.sticknology.jani2.app_objects.trainingplan.AppData;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_operations.ListMethods;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Exercise extends AppData {

    //Empty constructor to use add/set functions to build instead of constructor
    public Exercise(String name, DataMap attributes){

        super(name, attributes);
    }

    protected Exercise(Exercise exercise){
        super(exercise);
    }
}
