package com.sticknology.jani2.app_objects.trainingplan.sessions;

import com.sticknology.jani2.base_objects.MTimeDay;

import java.util.HashMap;

public class SData {

    private final MTimeDay mSessionTime;
    private final Session mSession;
    private final HashMap<String, Object> mAttributes;

    public SData(MTimeDay timeDay, Session session, HashMap<String, Object> attributes){

        mSessionTime = timeDay;
        mSession = session;
        mAttributes = attributes;
    }

    public MTimeDay getTimeDay(){return mSessionTime;}
    public Session getSession(){return mSession;}
    public HashMap<String, Object> getAttributes(){return mAttributes;}
}
