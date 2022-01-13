package com.sticknology.jani2.app_objects.trainingplan.exercises;

public enum EAttributeKeys {

    MUSCLE_GROUP("muscle_group"),
    RECORD_TYPE("record_type");

    private final String key;
    EAttributeKeys(String key){this.key = key;}
    public String getKey(){return this.key;}
}
