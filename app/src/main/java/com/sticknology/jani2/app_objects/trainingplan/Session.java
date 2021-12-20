package com.sticknology.jani2.app_objects.trainingplan;

public class Session {
  
  private String mName;
  private String mDescription;
  private String mType;
  
  private List<Exercise> mExerciseList = new ArrayList();
  private List<Container> mAttributes = new ArrayList();
  
  //Base Constructor
  public Session(String name, String description, String type, List<Exercise> exerciseList, List<Container> attributes){
    
    mName = name;
    mDescription = description;
    mType = type;
    
    mExerciseList = exerciseList;
    mAttributes = attributes;
  }
  
  //Basic Getters and Setters
  
}
