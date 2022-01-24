package com.sticknology.jani2.app_objects.trainingplan.exercises;

import androidx.annotation.NonNull;

import java.util.Locale;

public enum EAttributeKeys {

    MUSCLE_GROUP,
    RECORD_TYPE,
    DESCRIPTION,
    EXERCISE_TYPE;

    @NonNull
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}
