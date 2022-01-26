package com.sticknology.jani2.data;

public enum UserFileName {

    SESSION_REGISTRY("session_registry.xml");

    private final String path;
    UserFileName(String path){
        this.path = path;
    }

    public String getPath(){return path;}
}
