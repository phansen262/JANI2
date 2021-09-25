package com.sticknology.jani2.base_objects;

public class UDistance {

    //Enum to allow for easier referencing to units in code
    public enum DistanceUnits {
        METERS("m", 1),
        KILOMETERS("km", 1000),
        MILES("mi", 1600);
        public final String mLabel;
        public final double mConversion;
        DistanceUnits(String label, double conversion){
            this.mLabel = label;
            this.mConversion = conversion;
        }
    }

    //Private Variables Stored Inside Objects
    private double mValue;
    private DistanceUnits mDistanceUnit;

    //Basic Constructors
    //Public constructor for general outside use
    public UDistance(double value, DistanceUnits distanceUnit){

        mValue = value;
        mDistanceUnit = distanceUnit;
    }

    //Start of string conversion functions
    public String toDispString(){

        //Normalize mValue to have 0.00 format, Add unit label and return
        return ((double) (int) (mValue * 100)) / 100 + " " + mDistanceUnit.mLabel;
    }
    //End of string conversion functions

    //Unit Conversion Method
    public void convertUnits(DistanceUnits newUnit){
        //new value = old value x old conversion factor / new conversion factor
        mValue = mValue * mDistanceUnit.mConversion / newUnit.mConversion;
        mDistanceUnit = newUnit;
    }

    //Start of getters and setters, use "convertUnits" instead of setting for unit conversion
    public double getValue(){return mValue;}
    public DistanceUnits getDistanceUnit(){return mDistanceUnit;}

    public void setValue(double newValue){mValue = newValue;}
    //End of getters and setters
}
