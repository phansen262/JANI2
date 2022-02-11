package com.sticknology.jani2.data;

import androidx.annotation.NonNull;

import java.util.Locale;

public enum DirectoryNames {

    SESSION_LIST;



    @NonNull
    @Override
    public String toString() {
        return super.toString().toLowerCase(Locale.ROOT);
    }
}
