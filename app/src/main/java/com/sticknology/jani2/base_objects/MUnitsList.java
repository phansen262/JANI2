package com.sticknology.jani2.base_objects;

public enum MUnitsList {

    //Distance Units
    METERS("m", "Meters", 0, 1),
    KILOMETERS("km", "Kilometers", 0, 1000),
    MILES("mi", "Miles", 0, 1600),

    //Weight Units
    KILOGRAM("kg", "Kilograms", 1, 1),
    POUND("lbs", "Pounds", 1, 2.2),

    //Temperature Units
    CELSIUS("\u00B0C", "Degrees Celsius", 2, 0),
    FAHRENHEIT("\u00B0F", "Degrees Fahrenheit", 2, 0);

    public final String mLabel;
    public final String mFullName;
    public final int mType;
    public final double mConversion;

    MUnitsList(String label, String fullName, int type, double conversion){
        this.mLabel = label;
        this.mFullName = fullName;
        this.mType = type;
        this.mConversion = conversion;
    }
}
