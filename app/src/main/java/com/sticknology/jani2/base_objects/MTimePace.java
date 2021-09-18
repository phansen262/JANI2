package com.sticknology.jani2.base_objects;

public class MTimePace extends MTime {

    MyDistance uDistance;

    //Base Constructors
    public MTimePace(int hours, int minutes, int seconds, MyDistance distance) {
        super(hours, minutes, seconds);
        uDistance = distance;
    }

    public MTimePace(int minutes, int seconds, MyDistance distance){
        super(0, minutes, seconds);
        uDistance = distance;
    }

    //Get Display String
    @Override
    public String getDispString() {

        String paceString = super.getDispString();
        paceString += " /" + ((int) uDistance.getValue()) + uDistance.getUnitName();

        return paceString;
    }

    public void convertPace(MyDistance newUDistance){

        //Convert stored uDistance to proper unit type
        uDistance.convertUnits(newUDistance.getDistanceUnit());
        //Scale time according to difference in unit distance lengths
        double timeFactor = newUDistance.getValue() / uDistance.getValue();
        resetTime((int) Math.round(getTotalSeconds() * timeFactor));
        //Fully update uDistance
        uDistance = newUDistance;
    }
}
