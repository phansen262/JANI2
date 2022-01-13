package com.sticknology.jani2.app_objects.trainingplan.sessions;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Session {
  
  private String mName;
  private String mDescription;
  private String mType;
  
  private List<EData> mEDataList;
  private HashMap<String, List<String>> mAttributes;
  
  //Base Constructor
  public Session(String name, String description, String type, ArrayList<EData> exerciseList, HashMap<String, List<String>> attributes){
    
    mName = name;
    mDescription = description;
    mType = type;
    
    mEDataList = exerciseList;
    mAttributes = attributes;
  }
  
  //Basic Getters and Setters
  public String getName(){return mName;}
  public String getDescription(){return mDescription;}
  public String getType(){return mType;}
  public List<EData> getEDataList(){return mEDataList;}
  
  public void setName(String name){mName = name;}
  public void setDescription(String description){mDescription = description;}
  public void setType(String type){mType = type;}
  public void setEDataList(List<EData> newList){mEDataList = newList;}

  //Adders
  public void addExercise(Exercise newExercise){
    mEDataList.add(new EData(newExercise, new HashMap<>()));
  }
  public void addEData(EData eData){
    mEDataList.add(eData);}
}
