package com.sticknology.jani2.app_objects.trainingplan.sessions;

import androidx.annotation.NonNull;

import java.util.Locale;

public enum SAttributeKeys {

    LOCATION,
    DESCRIPTION;


    @NonNull
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}
