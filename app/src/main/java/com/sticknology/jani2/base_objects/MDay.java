package com.sticknology.jani2.base_objects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MDay {

    private static final int[] mDaysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

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
    public int getIntDayOfWeek(){
        Calendar c = Calendar.getInstance();
        c.setTime(getDateObject());
        return c.get(Calendar.DAY_OF_WEEK);
    }
    public String getStringDayOfWeek(){
        DateFormat df = new SimpleDateFormat("EEEE", Locale.getDefault());
        return df.format(getDateObject());
    }
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

    //Get day count from months (only totals days in months)
    //Requires year for leap year designation
    public static int daysFromMonths(int months, int year){
        boolean leap = year % 4 == 0;
        int ret = 0;
        for(int i = 0; i < months; i++){
            if(!leap || i != 1){
                ret += mDaysInMonths[i];
            } else {
                ret += 29;
            }
        }
        return ret;
    }

    public static int monthsFromDays(int days, int year){
        boolean leap = year % 4 == 0;
        int ret = 0;
        while(days >= 0){
            if(!leap || ret != 1) {
                days -= mDaysInMonths[ret];
            } else {
                days -= 29;
            }
            ret++;
        }
        return ret;
    }

    public Date getDateObject(){
        try {
            int months = monthsFromDays(mDay, mYear);
            int dayOfMonth = mDay - daysFromMonths(months - 1, mYear);
            String dateString = mYear + "-" + months + "-" + dayOfMonth
                    + "T" + mTimeDay.getHours() + ":" + mTimeDay.getMinutes() + ":" + mTimeDay.getSeconds();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", java.util.Locale.getDefault());

            return sdf.parse(dateString);
        } catch (ParseException e){
            e.printStackTrace();
            return null;
        }
    }
}
