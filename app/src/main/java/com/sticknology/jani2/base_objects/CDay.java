package com.sticknology.jani2.base_objects;

import java.util.Calendar;
import java.util.Date;

public class CDay {

    //CDAY and day of week to be used as reference
    private CDay referenceDOW = new CDay("01/01/2021", new MTimeDay(0, 0));
    private CResources.WeekDay refDOW = CResources.WeekDay.FRIDAY;

    private int mYear;
    private int mDayIndex;
    private MTimeDay mTimeDay;
    private CResources.WeekDay mDOW;

    //Base constructors, dateString of Form DD/MM/YYYY
    public CDay(String dateString, MTimeDay timeDay){

        if(!dateString.matches("\\d\\d/\\d\\d/\\d\\d\\d\\d")){
            throw new IllegalArgumentException("Date String Does Not Have Correct Format");
        }
        mYear = Integer.parseInt(dateString.split("/")[2]);
    }

    //Empty input constructor creates object for current day
    public CDay(){

        Date currentTime = Calendar.getInstance().getTime();
        String[] dayStringArray = currentTime.toString().split(" ");

    }

    //Requires format of DD/MM/YYYY
    public int getDayIndex(String dateString){

        return 0;
    }
}
