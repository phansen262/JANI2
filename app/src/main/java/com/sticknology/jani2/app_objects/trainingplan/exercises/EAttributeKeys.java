package com.sticknology.jani2.app_objects.trainingplan.exercises;

public enum EAttributeKeys {

    MGROUP("MGROUP"),
    RECORD_TYPE("Record_Type");

    private String key;
    EAttributeKeys(String key){this.key = key;}
    public String getKey(){return this.key;}
}
