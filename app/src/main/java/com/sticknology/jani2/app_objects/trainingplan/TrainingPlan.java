package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.Carrier;
import com.sticknology.jani2.base_objects.MDay;
import com.sticknology.jani2.base_objects.Tuple;

import java.util.ArrayList;
import java.util.List;

public class TrainingPlan {
  
  private String mName;
  private String mDescription;
  
  private MDay mStartDay;
  private MDay mEndDay;
  
  private List<Tuple> mDayTypes = new ArrayList<Tuple>();
  private List<Carrier> mAttributes = new ArrayList<Carrier>();
  
  public TrainingPlan(String name, String description, MDay startDay, MDay endDay, List<Tuple> dayTypes, List<Carrier> attributes){
    
    mName = name;
    mDescription = description;
    mStartDay = startDay;
    mEndDay = endDay;
    mDayTypes = dayTypes;
    mAttributes = attributes;
  }
}
