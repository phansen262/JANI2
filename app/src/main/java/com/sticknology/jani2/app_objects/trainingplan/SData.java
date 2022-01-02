package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.MTimeDay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SData {

    private MTimeDay mSessionTime;
    private Session mSession;
    private HashMap<String, Object> mAttributes;

    public SData(MTimeDay timeDay, Session session, HashMap<String, Object> attributes){

        mSessionTime = timeDay;
        mSession = session;
        mAttributes = attributes;
    }

    public MTimeDay getTimeDay(){return mSessionTime;}
    public Session getSession(){return mSession;}
    public HashMap<String, Object> getAttributes(){return mAttributes;}
}
