package com.sticknology.jani2.base_objects;

public class UWeight {

    public enum WeightUnits {
        POUNDS("lbs", 1),
        KILOGRAMS("kg", 2.2);
        public final String mLabel;
        public final double mConversion;
        WeightUnits(String label, double conversion){
            this.mLabel = label;
            this.mConversion = conversion;
        }
    }

    //Private Variables Stored Inside Objects
    private double mValue;
    private WeightUnits mWeightUnit;

    //Basic Constructors
    //Public constructor for general outside use
    public UWeight(double value, WeightUnits distanceUnit){

        mValue = value;
        mWeightUnit = distanceUnit;
    }

    //Start of string conversion functions
    public String toDispString(){

        //Normalize mValue to have 0.00 format, Add unit label and return
        return (int) mValue + " " + mWeightUnit.mLabel;
    }
    //End of string conversion functions

    //Unit Conversion Method
    public void convertUnits(WeightUnits newUnit){
        //new value = old value x old conversion factor / new conversion factor
        mValue = mValue * mWeightUnit.mConversion / newUnit.mConversion;
        mWeightUnit = newUnit;
    }

    //Start of getters and setters, use "convertUnits" instead of setting for unit conversion
    public double getValue(){return mValue;}
    public WeightUnits getDistanceUnit(){return mWeightUnit;}

    public void setValue(double newValue){mValue = newValue;}
    //End of getters and setters
}
