package com.sticknology.jani2.app_objects.trainingplan.edata;

import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_objects.MUnit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class EData extends Exercise {

    //Class Start
    private DataMap payload;

    public EData(Exercise e, DataMap dataMap){

        super(e);
        payload = dataMap;
    }


    //Adders for different datatypes to get data into edata payload
    public void addStringData(Enum<?> key, List<String> inputList){
        payload.put(key, inputList);
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
    public List<String> getPayloadItem(Enum<?> key){
        return payload.get(key);
    }
    public Set<Enum<?>> getPayloadKeys(){return payload.keySet();}
    public void putPayload(Enum<?> key, List<String> payload){this.payload.put(key, payload);}
}
