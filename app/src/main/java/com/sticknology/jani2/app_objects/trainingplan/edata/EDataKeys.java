package com.sticknology.jani2.app_objects.trainingplan.edata;

import androidx.annotation.NonNull;

import java.util.Locale;

public enum EDataKeys {

    SET("Sets"),
    REPS("Reps"),
    DURATION("Duration"),
    WEIGHT("Weight"),
    EXERCISE_NAME("THIS IS A MISTAKE");

    private final String display;

    EDataKeys(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return this.display;
    }


    @NonNull
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}
