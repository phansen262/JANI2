package com.sticknology.jani2.base_objects;

import java.util.ArrayList;
import java.util.List;

public class Tuple {
  
  private List<Object> objects = new ArrayList<Object>();
  
  public Tuple(List<Object> input){

    objects = input;
  }
  
  public List<Object> getTupleList(){return objects;}
  
  public Object getTupleItem(int i){return objects.get(i);}
}
