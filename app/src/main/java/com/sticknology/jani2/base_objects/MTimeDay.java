package com.sticknology.jani2.base_objects;

public class MTimeDay extends MTime {

    //Base Constructors, option to not include seconds, internals require 24hr time format
    public MTimeDay(int hours, int minutes, int seconds) {
        super(hours, minutes, seconds);
    }

    public MTimeDay(int hours, int minutes){
        super(hours, minutes, 0);
    }

    //Gets Display String for UI
    @Override
    public String getDispString() {

        //TODO: implement 24hr display option, use boolean in options/config and if/else switch in
        // method to change display string output, should only require in method implementation as
        // affects display only

        String buildString = "";

        //Get Display for Minutes
        String minutesString = "";
        if(getMinutes() < 10){
            minutesString = "0" + getMinutes();
        } else {
            minutesString = String.valueOf(getMinutes());
        }

        //Get Overall Display String for AM/PM Format
        if(getHours() > 12){
            buildString += (getHours() - 12) + ":" + minutesString + " PM";
        } else if(getHours() == 0){
            buildString += 12 + ":" + minutesString + " AM";
        } else if(getHours() == 12) {
            buildString += getHours() + ":" + minutesString + " PM";
        } else {
            buildString += getHours() + ":" + minutesString + " AM";
        }

        return buildString;
    }

    //Compares TimeDay to Duration
    @Override
    public void addDuration(MTime time) {
        super.addDuration(time);
        checkTimeBounds();
    }

    @Override
    public void subtractDuration(MTime time){
        super.addDuration(time);
        checkTimeBounds();
    }

    //Making sure doesn't fall out of 24 hr time period
    //Elapsed Days must be accounted for elsewhere
    private void checkTimeBounds(){
        if(getTotalSeconds() < 0){
            resetTime(getTotalSeconds() * -1);
        } else if(getTotalSeconds() > (3600 * 24)){
            resetTime(getTotalSeconds() % (3600 * 24));
        }
    }

    //Compares to other time to find duration between times
    public MTime compareDayTime(MTimeDay otherTime){
        return new MTime(Math.abs(getTotalSeconds() - otherTime.getTotalSeconds()));
    }
}
