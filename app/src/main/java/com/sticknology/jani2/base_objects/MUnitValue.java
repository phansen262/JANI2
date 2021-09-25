package com.sticknology.jani2.base_objects;

public class MUnitValue {

    //Private Variables Stored Inside Objects
    private double mValue;
    private MUnitsList mUnit;

    //Basic Constructors
    //Public constructor for general outside use
    public MUnitValue(double value, MUnitsList unit){

        mValue = value;
        mUnit = unit;
    }

    //TODO:  Convert to allow any number of specified decimals??
    //Start of string conversion functions
    public String toDispString(double numDecimals){

        //Normalize mValue to have correct format, Add unit label and return
        return (double) (int) mValue * Math.pow(10, numDecimals) / Math.pow(10, numDecimals)
                + " " + mUnit.mLabel;
    }
    //End of string conversion functions

    //Unit Conversion Method
    public void convertUnits(MUnitsList newUnit){
        if(mUnit.mType == newUnit.mType && mUnit.mType != 2) {
            //new value = old value x old conversion factor / new conversion factor
            mValue = mValue * mUnit.mConversion / newUnit.mConversion;
        }
        //Special conversion case for temperature
        else if(mUnit.mType == newUnit.mType){
            if(mUnit == MUnitsList.FAHRENHEIT){
                mValue = (mValue - 32) * ((float) 5/9);
            } else {
                mValue = (mValue * ((float) 9/5)) + 32;
            }
        }
        else {
            throw new IllegalArgumentException("Units are not of same type");
        }
        //Update unit object to reflect conversion
        mUnit = newUnit;
    }

    //Start of getters and setters, use "convertUnits" instead of setting for unit conversion
    public double getValue(){return mValue;}
    public MUnitsList getDistanceUnit(){return mUnit;}

    public void setValue(double newValue){mValue = newValue;}
    //End of getters and setters
}
