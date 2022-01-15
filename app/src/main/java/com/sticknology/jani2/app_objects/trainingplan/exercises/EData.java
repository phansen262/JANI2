package com.sticknology.jani2.app_objects.trainingplan.exercises;

import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_objects.MUnit;

import java.util.ArrayList;
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
    private HashMap<String, String> payload;

    public EData(Exercise exercise, HashMap<String, String> dataMap){

        key = exercise;
        payload = dataMap;
    }

    public void addIntData(String key, int data){

        payload.put(key, String.valueOf(data));
    }

    public void addMUnitData(String key, List<MUnit> data){

        StringBuilder build = new StringBuilder();
        for(int i = 0; i < data.size(); i++){
            build.append(data.get(i).toDispString(0));
            if(i < data.size() - 1){
                build.append("@!@");
            }
        }
        payload.put(key, build.toString());
    }

    public void addTimeData(String key, MTime data){
        payload.put(key, data.getDispString());
    }

    public void addDuration(List<MTime> durations){
        payload.put(EDataKeys.DURATION.key, durations.get(0).getDispString());
    }

    public List<MUnit> getWeights(){
        String[] values = Objects.requireNonNull(payload.get(EDataKeys.WEIGHT.key)).split("@!@");
        List<MUnit> weights = new ArrayList<>();

        for(String value : values){
            weights.add(new MUnit(value));
        }

        return weights;
    }

    //Base getters and setters
    public Exercise getKey(){
        return key;
    }
    public HashMap<String, String> getData(){
        return payload;
    }
    public void setKey(Exercise newExercise){key = newExercise;}
    public void setPayload(HashMap<String, String> newPayload){payload = newPayload;}
}
