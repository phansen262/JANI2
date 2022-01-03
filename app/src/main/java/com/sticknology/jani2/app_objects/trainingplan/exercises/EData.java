package com.sticknology.jani2.app_objects.trainingplan.exercises;

import java.util.HashMap;

public class EData {

    private Exercise key;
    private HashMap<String, Object> payload;

    public EData(Exercise exercise, HashMap<String, Object> dataMap){

        key = exercise;
        payload = dataMap;
    }

    public Exercise getKey(){
        return key;
    }

    public HashMap<String, Object> getData(){
        return payload;
    }
}
