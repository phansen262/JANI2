package com.sticknology.jani2.app_objects.trainingplan.exercises;

import java.util.ArrayList;
import java.util.List;

public class ExerciseEnums {

    public enum EType {

        NONE("None"),
        CARDIO("Cardio"),
        STRENGTH("Strength"),
        WEIGHTS("Weights"),
        YOGA("Yoga");

        String displayName;
        EType(String name){
            displayName = name;
        }
        public String getName(){return this.displayName;}
    }

    public static List<String> getTypeNames(){
        List<String> ret = new ArrayList<>();
        for (EType eType : EType.values()){
            ret.add(eType.displayName);
        }
        return ret;
    }

}
