package com.sticknology.jani2.base_objects;

import java.util.Calendar;
import java.util.TimeZone;

public class MDateMillies {

    private long mTimeMillies;

    public MDateMillies(){

        mTimeMillies = Calendar.getInstance().getTimeInMillis();
    }

    public void convertToLocal(){

        TimeZone tz = Calendar.getInstance().getTimeZone();

    }
}
