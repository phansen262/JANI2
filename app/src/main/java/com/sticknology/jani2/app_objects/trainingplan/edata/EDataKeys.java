package com.sticknology.jani2.app_objects.trainingplan.edata;

public enum EDataKeys {

    SET("set", "Sets"),
    REPS("reps", "Reps"),
    DURATION("duration", "Duration"),
    WEIGHT("weight", "Weight");

    private final String key;
    private final String display;

    EDataKeys(String key, String display) {
        this.key = key;
        this.display = display;
    }

    public String getKey() {
        return this.key;
    }

    public String getDisplay() {
        return this.display;
    }
}
