package com.sticknology.jani2.base_operations;

import java.util.List;

public class ListMethods {

    //Finds match in string ArrayList for sample string
    public static int matchListIndex(List<String> stringList, String matchString){

        for(int i = 0; i < stringList.size(); i++){

            //System.out.println(matchString);
            System.out.println(stringList.get(i));

            if(matchString.equals(stringList.get(i))){
                return i;
            }
        }

        throw new ArrayIndexOutOfBoundsException("matchListIndex, matchString Not Found");
    }

    //Append list together using joiner, cant use java class bc of min target API requirement
    public static String joinList(List<String> inputList, String delimiter){

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < inputList.size(); i++){
            sb.append(inputList.get(i));
            if(i < inputList.size() -1){
                sb.append(delimiter);
            }
        }
        return sb.toString();
    }

    public static String joinList(List<String> inputList, String[] delimiters){

        //Keys for index on delimiter string:
        //  0: Primary
        //  1: Last delimiter
        //  2: Supporting different delimiter if only two items

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < inputList.size(); i++){
            sb.append(inputList.get(i));
            if(inputList.size() == 2 && i == 0){
                sb.append(delimiters[2]);
            } else if(i < inputList.size() -2){
                sb.append(delimiters[0]);
            } else if (i == inputList.size() - 2){
                sb.append(delimiters[1]);
            }
        }
        return sb.toString();
    }

    public static String joinList(List<String> inputList){
        return joinList(inputList, new String[]{",", ", and ", " and "});
    }
}
