package com.sticknology.jani2.app_objects.trainingplan.exercises;

import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_objects.MUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

    private Exercise key;
    private HashMap<String, String> payload;

    public EData(Exercise exercise, HashMap<String, String> dataMap){

        key = exercise;
        payload = dataMap;
    }

    public void addSetRepData(int sets, int reps){

        payload.put(EDataKeys.SET.key, String.valueOf(sets));
        payload.put(EDataKeys.REPS.key, String.valueOf(reps));
    }

    public void addSetRepWeightData(int sets, int reps, List<MUnit> weights){

        payload.put(EDataKeys.SET.key, String.valueOf(sets));
        payload.put(EDataKeys.REPS.key, String.valueOf(reps));
        String build = "";
        for(int i = 0; i < weights.size(); i++){
            build += weights.get(i).toDispString(0);
            if(i < weights.size() - 1){
                build += "@!@";
            }
        }
        payload.put(EDataKeys.WEIGHT.key, build);
    }

    public void addDuration(MTime duration){
        payload.put(EDataKeys.DURATION.key, duration.getDispString());
    }

    public void addSetDuration(int sets, List<MTime> durations){
        payload.put(EDataKeys.SET.key, String.valueOf(sets));
        payload.put(EDataKeys.DURATION.key, String.valueOf(getDuration().getTotalSeconds()));
    }

    public int getSets(){

        return Integer.parseInt(payload.get(EDataKeys.SET.key));
    }

    public int getReps(){

        return Integer.parseInt(payload.get(EDataKeys.REPS.key));
    }

    public List<MUnit> getWeights(){
        List<String> values = Arrays.asList(payload.get(EDataKeys.WEIGHT.key).split("@!@"));
        List<MUnit> weights = new ArrayList<>();

        for(String value : values){
            weights.add(new MUnit(value));
        }

        return weights;
    }

    public MTime getDuration(){

        return new MTime(Integer.parseInt(payload.get(EDataKeys.DURATION.key)));
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
