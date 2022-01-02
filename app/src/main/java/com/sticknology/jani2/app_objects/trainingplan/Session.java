package com.sticknology.jani2.app_objects.trainingplan;

import com.sticknology.jani2.base_objects.Carrier;

import java.util.ArrayList;
import java.util.List;

public class Session {
  
  private String mName;
  private String mDescription;
  private String mType;
  
  private ArrayList<Exercise> mExerciseList = new ArrayList();
  private ArrayList<Carrier> mAttributes = new ArrayList();
  
  //Base Constructor
  public Session(String name, String description, String type, ArrayList<Exercise> exerciseList, ArrayList<Carrier> attributes){
    
    mName = name;
    mDescription = description;
    mType = type;
    
    mExerciseList = exerciseList;
    mAttributes = attributes;
  }
  
  //Basic Getters and Setters
  public String getName(){return mName;}
  public String getDescription(){return mDescription;}
  public String getType(){return mType;}
  
  public void setName(String name){mName = name;}
  public void setDescription(String description){mDescription = description;}
  public void setType(String type){mType = type;}
}
