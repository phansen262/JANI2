package com.sticknology.jani2.app_objects.other;

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
}
