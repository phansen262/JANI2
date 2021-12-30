package com.sticknology.jani2.base_objects;

import java.io.Serializable;

public class Carrier implements Serializable {

    private String mKey;
    private Object mPayload;

    public Carrier(String key, Object payload){

        mKey = key;
        mPayload = payload;
    }

    public String getKey(){
        return mKey;
    }

    public Object getPayload(){
        return mPayload;
    }
}
