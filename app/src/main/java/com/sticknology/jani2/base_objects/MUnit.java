package com.sticknology.jani2.base_objects;

public class MUnit {

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
}
