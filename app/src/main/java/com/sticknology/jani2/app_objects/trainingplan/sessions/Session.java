package com.sticknology.jani2.app_objects.trainingplan.sessions;

import com.sticknology.jani2.app_objects.trainingplan.AppData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_objects.DataMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Session extends AppData {
  
  private String mPath;

  private List<EData> mEDataList;

  
  //Base Constructors
  public Session(String name, String path, ArrayList<EData> eDataList, DataMap attributes){

    super(name, attributes);
    mPath = path;
    mEDataList = eDataList;
  }
  
  //Basic Getters and Setters
  public String getName(){return mName;}
  public String getPath(){return mPath;}
  public List<EData> getEDataList(){return mEDataList;}
  
  public void setName(String name){mName = name;}
  public void setEDataList(List<EData> newList){mEDataList = newList;}

  //Adders
  public void addExercise(Exercise newExercise){
    mEDataList.add(new EData(newExercise, new DataMap()));
  }
  public void addEData(EData eData){
    mEDataList.add(eData);
  }
}
