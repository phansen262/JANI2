package com.sticknology.jani2.base_objects;

import com.sticknology.jani2.base_operations.ListMethods;

import java.util.HashMap;
import java.util.List;

public class DataMap extends HashMap<Enum<?>, List<String>> {

    //Returns string value of attribute
    public String getItemString(String key, String[] delimiters){

        List<String> item = this.get(key);
        if(item == null){
            return "ERROR IN DATAMAP:  GETITEMSTRING";
        } else if(item.size() == 1){
            return item.get(0);
        } else {
            return ListMethods.joinList(item, delimiters);
        }
    }

    public String getItemString(String key){

        List<String> item = this.get(key);
        if(item == null){
            return "ERROR IN DATAMAP:  GETITEMSTRING";
        } else if(item.size() == 1){
            return item.get(0);
        } else {
            return ListMethods.joinList(item, new String[]{", ", ", and ", " and "});
        }
    }
}
