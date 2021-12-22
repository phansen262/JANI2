package com.sticknology.jani2.app_objects.trainingplan;

public class TrainingPlan {
  
  private String mName;
  private String mDescription;
  
  private MDay mStartDay;
  private MDay mEndDay;
  
  private List<Tuple> mDayTypes = new ArrayList<Tuple>();
  private List<Carrier> mAttributes = new ArrayList<Carrier>();
  
  public TrainingPlan(name, description, startDay, endDay, dTypes, attributes){
    
    mName = name;
    mDescription = description;
    mStartDay = startDay;
    mEndDay = endDay;
    mDayTypes = dayTypes;
    mAttributes = attributes
  }
}
