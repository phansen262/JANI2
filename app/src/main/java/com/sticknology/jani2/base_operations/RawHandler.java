package com.sticknology.jani2.base_operations;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.exercise.Exercise;

import java.util.ArrayList;
import java.util.List;

public class RawHandler {

    public List<Exercise> getDefaultExercises(Context context){

        FileProxy fileProxy = new FileProxy();
        String fileOutput = fileProxy.readFileToString("default_info/exercises.txt", context);
        String[] fileLines = fileOutput.split("\n");

        List<Exercise> outputList = new ArrayList<Exercise>();

        if(fileLines[0].equals("VERSION1.0")){
            for(int i = 1; i < fileLines.length; i++){

                outputList.add(new Exercise(fileLines[i].split(",")[0], fileLines[i].split(",")[1]));
            }
        }

        return  outputList;
    }
}
