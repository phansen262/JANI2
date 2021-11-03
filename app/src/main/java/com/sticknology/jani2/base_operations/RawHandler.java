package com.sticknology.jani2.base_operations;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.exercise.Exercise;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RawHandler {

    public List<Exercise> getDefaultExercises(Context context) throws IOException {

        InputStream inputStream = context.getAssets().open("default_info/exercises.txt");

        FileProxy fileProxy = new FileProxy();
        String fileOutput = fileProxy.readFileToString("default_info/exercises.txt", context, inputStream);

        String[] fileLines = fileOutput.split("\n");

        List<Exercise> outputList = new ArrayList<Exercise>();

        if(fileLines[1].equals("VERSION1.0")){
            for(int i = 2; i < fileLines.length; i++){

                outputList.add(new Exercise(fileLines[i].split(",")[0], fileLines[i].split(",")[1]));
            }
        }

        return  outputList;
    }
}
