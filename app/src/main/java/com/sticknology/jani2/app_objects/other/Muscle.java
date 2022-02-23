package com.sticknology.jani2.app_objects.other;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

//Class to handle muscle groups and fatigue
public class Muscle {

    public enum MGroup{
        CHEST("Chest"),
        ARMS("Arms"),
        LEGS("Legs"),
        BACK("Back"),
        ABS("Abs"),
        NONE("None");

        String displayName;
        MGroup(String name){
            displayName = name;
        }
        public String getName(){return this.displayName;}
    }

    public static List<String> getMGroupNames(){
        List<String> ret = new ArrayList<>();
        for(MGroup mg : MGroup.values()){
            ret.add(mg.displayName);
        }
        return ret;
    }
}
