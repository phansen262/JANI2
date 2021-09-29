package com.sticknology.jani2.base_objects;

import java.util.Calendar;

public class MDay {

    private int mYear;
    private int mDay;
    private MTimeDay mTimeDay;

    //Empty params with constructor will fill with current date/time
    public MDay(){

        //All time length units in terms of milliseconds
        long seconds = 1000;
        long minutes = seconds * 60;
        long hours = minutes * 60;
        long days = hours * 24;
        long years = days * 365;
        long fourYears = (years * 4) + days;

        //Get local time in milliseconds
        long timeMillies = Calendar.getInstance().getTimeInMillis();
        int offset = Calendar.getInstance().getTimeZone().getOffset(timeMillies);
        timeMillies += offset;

        //Assign values to object variables
        long lYear = timeMillies / fourYears * 4 + timeMillies % fourYears / years + 1970;
        long lDay = timeMillies % fourYears % years / days;
        mYear = (int) lYear;
        mDay = (int) lDay;
        long lHours = timeMillies % fourYears % years % days / hours;
        long lMinutes = timeMillies % fourYears % years % days % hours / minutes;
        long lSeconds = timeMillies % fourYears % years % days % hours % minutes / seconds;
        mTimeDay = new MTimeDay((int) lHours, (int) lMinutes, (int) lSeconds);
    }

    //Params with constructor for setting up date object with input
    public MDay(int year, int day, MTimeDay timeDay){

        mYear = year;
        mDay = day;
        mTimeDay = timeDay;
    }

    //Partial params for setting up date only with no attached time
    public MDay(int year, int day){

        mYear = year;
        mDay = day;
        mTimeDay = new MTimeDay(0, 0);
    }

    //Object Variable Getters
    public int getYear(){return mYear;}
    public int getDay(){return mDay;}
    public MTimeDay getTimeDay(){return mTimeDay;}

    //Get number of days in year
    public int getDaysInYear(int year){

        if(year % 4 == 0){
            return 366;
        } else {
            return 365;
        }
    }

    //Correct object if date variable ends up negative, call whenever modifying value
    public void correctObject(){

        if(mTimeDay.getTotalSeconds() < 0){
            mDay -= 1 + mTimeDay.getTotalSeconds() / 24*60*60;
            MTimeDay newTimeDay = new MTimeDay(24, 0);
            newTimeDay.addDuration(mTimeDay);
            mTimeDay = newTimeDay;
        }
        while(mDay < 0){
            mYear--;
            mDay += getDaysInYear(mYear);
        }
    }

    //Method to add/subtract time from object, +input to add and -input to subtract (except for MTime)
    public MDay applyOffset(int yearOffset, int dayOffset, MTime timeOffset){

        int yearDiff = mYear + yearOffset;
        int dayDiff = mDay + dayOffset;
        MTimeDay timeDiff = mTimeDay;
        timeDiff.addDuration(timeOffset);
        return new MDay(yearDiff, dayDiff, timeDiff);
    }

    //Find offset between time in object and other time
    public MDay findOffset(MDay compareDay){

        int yearOffset = mYear - compareDay.getYear();
        int dayOffset = mDay - compareDay.getDay();
        MTimeDay timeOffset = new MTimeDay(mTimeDay.compareDayTime(compareDay.getTimeDay()));
        return new MDay(yearOffset, dayOffset, timeOffset);
    }
}
