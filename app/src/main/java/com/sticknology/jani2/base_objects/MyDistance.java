package com.sticknology.jani2.base_objects;

import android.util.Log;

public class MyDistance {

    //Public Variables Stored Inside Objects
    public double mValue;
    public String mUnitName;
    public double mConversionFactor;

    //Enum to allow for easier referencing to units in code
    public enum DistanceUnits {
        METERS("m"), KILOMETERS("km"), MILES("mi");
        public final String mLabel;
        DistanceUnits(String label){this.mLabel = label;}
    }

    //Class list Of Usable Units, index aligns properties
    private final String[] unitNames = {"m", "km", "mi"};
    private final double[] conversionFactors = {1, 1000, 1600};

    //Basic Constructors
    //Public constructor for general outside use
    public MyDistance (double value, DistanceUnits distanceUnit){

        mValue = value;
        mUnitName = distanceUnit.mLabel;
        mConversionFactor = conversionFactors[getUnitIndex(mUnitName)];
    }

    //Private constructor for when unit name is passed internally
    private MyDistance (double value, String unitName){

        mValue = value;
        mUnitName = unitName;
        mConversionFactor = conversionFactors[getUnitIndex(unitName)];
    }

    //Utility Method To Get Proper Unit Index For Properties
    private int getUnitIndex(String name){
        for(int i = 0; i < unitNames.length; i++){
            if(unitNames[i] == name){
                return i;
            }
        }
        throw new NullPointerException("The provided String Name does not match any unit in code");
    }

    //Start of string conversion functions
    public String toDispString(){

        String dispBuild = "";

        //Get number value for string
        String initValue = String.valueOf(mValue);
        //Sets display for correct number of decimal places
        String[] decimalSplitArray = initValue.split("\\.");
        if(decimalSplitArray[1].length() != 1){
            //For input of form:  35.02584 or 2.02 or equivalents
            dispBuild += decimalSplitArray[0] + decimalSplitArray[1].substring(0, 2);
        } else {
            //For input of form:  35.0 or equivalents
            dispBuild += initValue + "0";
        }

        //Add unit label and return
        return dispBuild + " " + mUnitName;
    }

    public MyDistance fromDispString(String input){

        //Split string input into components and return new object
        String[] splitString = input.split(" ");
        return new MyDistance(Float.parseFloat(splitString[0]), splitString[1]);
    }
    //End of string conversion functions

    //Unit Conversion Method
    public void convertUnits(DistanceUnits newUnit){
        //new value = old value x old conversion factor / new conversion factor
        String newUnitName = newUnit.mLabel;
        double newConversion = conversionFactors[getUnitIndex(newUnitName)];
        mUnitName = newUnitName;
        mValue = mValue * mConversionFactor / newConversion;
        mConversionFactor = newConversion;
    }

    //Start of getters and setters, use "convertUnits" instead of setting for unit conversion
    public double getValue(){return mValue;}
    public String getUnitName(){return mUnitName;}
    public double getConversionFactor(){return mConversionFactor;}

    public void setValue(double newValue){mValue = newValue;}
    //End of getters and setters
}
