package com.sticknology.jani2.base_objects;

import java.util.Calendar;
import java.util.Date;

public class MDate {

    //TODO:  Time zone compatibility, actually relevant if traveling for races, etc
    //TODO:  ABOVE IS VERY IMPORTANT

    private int mYear;
    private int mMonth;
    private int mDay;

    private MTimeDay mTimeDay;

    /*
    Date String input must be of form:  DD/MM/YYYY
     */

    //Full constructor
    public MDate(String dateString, MTimeDay time){

        upDate(dateString);
        mTimeDay = time;
    }

    //Constructor with default 00:00 time
    public MDate(String dateString){

        upDate(dateString);
        mTimeDay = new MTimeDay(0, 0);
    }

    //No params will fill object with current date, and time if param is true, otherwise object
    //will be filled with 00:00 default time
    public MDate(boolean time){

        String[] rawDateSplit = Calendar.getInstance().getTime().toString().split(" ");
        mYear = Integer.parseInt(rawDateSplit[5]);
        mDay = Integer.parseInt(rawDateSplit[2]);

        if(time){
            String[] dayTimeSplit = rawDateSplit[3].split(":");
            mTimeDay = new MTimeDay(Integer.parseInt(dayTimeSplit[0]),
                    Integer.parseInt(dayTimeSplit[1]), Integer.parseInt(dayTimeSplit[2]));
        } else {
            mTimeDay = new MTimeDay(0, 0);
        }

    }

    public void upDate(String dateString){
        String[] splitString = dateString.split("/");
        mYear = Integer.parseInt(splitString[2]);
        mMonth = Integer.parseInt(splitString[1]);
        mDay = Integer.parseInt(splitString[0]);
    }
}
