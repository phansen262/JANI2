package com.sticknology.jani2.base_objects;

public class MyDistance {

    //Enum to allow for easier referencing to units in code
    public enum DistanceUnits {
        METERS("m"), KILOMETERS("km"), MILES("mi");
        public final String mLabel;
        DistanceUnits(String label){this.mLabel = label;}
    }

    //Private Variables Stored Inside Objects
    private double mValue;
    private String mUnitName;
    private double mConversionFactor;

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
            if(unitNames[i].equals(name)){
                return i;
            }
        }
        throw new NullPointerException("The provided String Name does not match any unit in code");
    }

    //Start of string conversion functions
    public String toDispString(){

        //Normalize mValue to have 0.00 format, Add unit label and return
        return ((double) (int) (mValue * 100)) / 100 + " " + mUnitName;
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
        double newConversion = conversionFactors[getUnitIndex(newUnit.mLabel)];
        mUnitName = newUnit.mLabel;
        mValue = mValue * mConversionFactor / newConversion;
        mConversionFactor = newConversion;
    }

    //Start of getters and setters, use "convertUnits" instead of setting for unit conversion
    public double getValue(){return mValue;}
    public String getUnitName(){return mUnitName;}
    public double getConversionFactor(){return mConversionFactor;}

    //Search for enum corresponding to label
    public DistanceUnits getDistanceUnit(){
        for (DistanceUnits unit : DistanceUnits.values()){
            if(mUnitName.equals(unit.mLabel)){
                return unit;
            }
        }
        throw new NullPointerException("Unit name not found in distance units enum");
    }

    public void setValue(double newValue){mValue = newValue;}
    //End of getters and setters
}
