package com.sticknology.jani2.app_objects.trainingplan.exercises;

import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_objects.MUnit;

import java.util.ArrayList;
import java.util.List;

public class EData extends Exercise{

    //Class Enum Functions
    public static String getDisplayFromKey(String inputKey){

        for(EDataKeys key : EDataKeys.values()){
            if(key.getKey().equals(inputKey)){return key.getDisplay();}
        }

        System.out.println("Did not find matching key request");
        return "";
    }

    //Class Start
    private DataMap payload;

    public EData(Exercise e, DataMap dataMap){

        super(e);
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
    public DataMap getData(){
        return payload;
    }
}
