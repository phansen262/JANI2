package com.sticknology.jani2.app_objects.trainingplan.sessions;

import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_objects.Carrier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Session {
  
  private String mName;
  private String mDescription;
  private String mType;
  
  private ArrayList<Exercise> mExerciseList = new ArrayList();
  private HashMap<String, List<String>> mAttributes = new HashMap<>();
  
  //Base Constructor
  public Session(String name, String description, String type, ArrayList<Exercise> exerciseList, HashMap<String, List<String>> attributes){
    
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
  public ArrayList<Exercise> getExerciseList(){return mExerciseList;}
  
  public void setName(String name){mName = name;}
  public void setDescription(String description){mDescription = description;}
  public void setType(String type){mType = type;}
  public void setExerciseList(ArrayList<Exercise> newList){mExerciseList = newList;}

  //Adders
  public void addExercise(Exercise newExercise){mExerciseList.add(newExercise);}
}
