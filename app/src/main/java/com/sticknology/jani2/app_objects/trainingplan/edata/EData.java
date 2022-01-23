package com.sticknology.jani2.app_objects.trainingplan.edata;

import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_objects.MUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EData extends Exercise {

    //Class Start
    private DataMap payload;

    public EData(Exercise e, DataMap dataMap){

        super(e);
        payload = dataMap;
    }

    public void addIntData(Enum<?> key, int[] data){

        List<String> intData = new ArrayList<>();
        for(int i : data){
            intData.add(String.valueOf(i));
        }
        payload.put(key, intData);
    }

    public void addMUnitData(Enum<?> key, List<MUnit> data, int decimals){

        List<String> mUnitData = new ArrayList<>();
        for(MUnit mUnit : data){
            mUnitData.add(mUnit.toDispString(decimals));
        }

        payload.put(key, mUnitData);
    }

    public void addTimeData(Enum<?> key, List<MTime> data){
        List<String> mTimeData = new ArrayList<>();
        for(MTime mTime : data){
            mTimeData.add(mTime.getDispString());
        }
        payload.put(key, mTimeData);
    }

    //Base getters and setters
    public List<String> getData(Enum<?> key){
        return payload.get(key);
    }
}