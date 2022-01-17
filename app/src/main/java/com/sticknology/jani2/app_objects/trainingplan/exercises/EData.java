package com.sticknology.jani2.app_objects.trainingplan.exercises;

import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_objects.MUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class EData {

    public enum EDataKeys {

        SET("set", "Sets"),
        REPS("reps", "Reps"),
        DURATION("duration", "Duration"),
        WEIGHT("weight", "Weight");

        private final String key;
        private final String display;
        EDataKeys(String key, String display){this.key = key; this.display = display;}
        public String getKey(){return this.key;}
        public String getDisplay(){return this.display;}
    }

    //Class Enum Functions
    public static String getDisplayFromKey(String inputKey){

        for(EDataKeys key : EDataKeys.values()){
            if(key.key.equals(inputKey)){return key.display;}
        }

        System.out.println("Did not find matching key request");
        return "";
    }

    //Class Start

    private Exercise key;
    private HashMap<String, List<String>> payload;

    public EData(Exercise exercise, HashMap<String, List<String>> dataMap){

        key = exercise;
        payload = dataMap;
    }

    public void addIntData(String key, int[] data){

        List<String> intData = new ArrayList<>();
        for(int i : data){
            intData.add(String.valueOf(i));
        }
        payload.put(key, intData);
    }

    public void addMUnitData(String key, List<MUnit> data, int decimals){

        List<String> mUnitData = new ArrayList<>();
        for(MUnit mUnit : data){
            mUnitData.add(mUnit.toDispString(decimals));
        }

        payload.put(key, mUnitData);
    }

    public void addTimeData(String key, List<MTime> data){
        List<String> mTimeData = new ArrayList<>();
        for(MTime mTime : data){
            mTimeData.add(mTime.getDispString());
        }
        payload.put(key, mTimeData);
    }

    //Base getters and setters
    public Exercise getKey(){
        return key;
    }
    public HashMap<String, List<String>> getData(){
        return payload;
    }
    public void setKey(Exercise newExercise){key = newExercise;}
    public void setPayload(HashMap<String, List<String>> newPayload){payload = newPayload;}
}
