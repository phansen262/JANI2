package com.sticknology.jani2.base_operations;

import java.util.ArrayList;

public class ListPicker {

    //Finds match in string ArrayList for sample string
    public static int matchListIndex(ArrayList<String> stringList, String matchString){

        for(int i = 0; i < stringList.size(); i++){
            if(matchString.equals(stringList.get(i))){
                return i;
            }
        }

        throw new ArrayIndexOutOfBoundsException("matchListIndex, matchString Not Found");
    }
}
