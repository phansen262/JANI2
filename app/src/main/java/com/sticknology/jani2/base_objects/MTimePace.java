package com.sticknology.jani2.base_objects;

public class MTimePace extends MTime {

    UDistance uDistance;

    //Base Constructors
    public MTimePace(int hours, int minutes, int seconds, UDistance distance) {
        super(hours, minutes, seconds);
        uDistance = distance;
    }

    public MTimePace(int minutes, int seconds, UDistance distance){
        super(0, minutes, seconds);
        uDistance = distance;
    }

    //Get Display String
    @Override
    public String getDispString() {

        String paceString = super.getDispString();
        if(uDistance.getValue() != 1.0) {
            paceString += " /" + ((int) uDistance.getValue()) + uDistance.getDistanceUnit().mLabel;
        } else {
            paceString += " /" + uDistance.getDistanceUnit().mLabel;
        }

        return paceString;
    }

    public String getDispString(boolean speed){

        UDistance speedDistance = new UDistance(uDistance.getValue() *
                getQuotient(new MTime(3600)), uDistance.getDistanceUnit());
        return speedDistance.toDispString() + "/hr";
    }

    //Convert pace to different per unit distance
    public void convertPace(UDistance newUDistance){

        //Convert stored uDistance to proper unit type
        uDistance.convertUnits(newUDistance.getDistanceUnit());
        //Scale time according to difference in unit distance lengths
        double timeFactor = newUDistance.getValue() / uDistance.getValue();
        resetTime((int) Math.round(getTotalSeconds() * timeFactor));
        //Fully update uDistance
        uDistance = newUDistance;
    }
}
