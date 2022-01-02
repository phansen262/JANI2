package com.sticknology.jani2.base_operations;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.Exercise;
import com.sticknology.jani2.app_objects.trainingplan.Session;
import com.sticknology.jani2.base_objects.Carrier;
import com.sticknology.jani2.base_objects.Tuple;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AssetsHandler {

    public static ArrayList<Exercise> getDefaultExercises(Context context) {

        //Reads default exercises
        String[] fileLines = readAsset(context, "default_info/exercises.ecf");
        ArrayList<Exercise> outputList = new ArrayList<Exercise>();
        if(fileLines[1].equals("VERSION1.1")){
            for(int i = 3; i < fileLines.length; i++){

                String[] splitExercise = fileLines[i].split("@!@");

                HashMap<String, Object> attributes = new HashMap<String, Object>();
                if(splitExercise.length != 3) {
                    String[] mGroups = new String[1];
                    mGroups[0] = splitExercise[3];
                    attributes.put("MGROUP", mGroups);
                }
                outputList.add(new Exercise(splitExercise[0], splitExercise[1], splitExercise[2], attributes));
            }
        }

        return  outputList;
    }

    public static ArrayList<Session> getDefaultSessions(Context context) {

        //Reads default sessions file
        String[] fileLines = readAsset(context, "default_info/exercises.ecf");
        ArrayList<Session> outputList = new ArrayList<Session>();
        if(fileLines[1].equals("VERSION1.1")){
            for(int i = 3; i < fileLines.length; i = i + 2){

                String[] sessionDetails = fileLines[i].split("@!@");
                String[] sessionAttributes = fileLines[i+1].split("@!@");
            }
        }

        return null;
    }


    public static ArrayList<String> getExerciseTypes(Context context) {

        //Get Default List of Types for Exercises
        String[] fileLines = readAsset(context, "default_info/exercises.ecf");
        ArrayList<String> outputList = new ArrayList<>();
        if(fileLines[2].split(":")[0].equals("TYPES")) {
            outputList.addAll(Arrays.asList(fileLines[2].split(":")[1].split("@!@")));
        }
        return outputList;
    }

    public static ArrayList<String> getMuscleTypes(Context context) {

        //Get default list of muscle groups, (currently names only)
        String[] fileLines = readAsset(context, "userdata/muscle.mcf");
        ArrayList<String> outputList = new ArrayList<>();
        if(fileLines[1].equals("VERSION1.0")){
            outputList.addAll(Arrays.asList(fileLines).subList(2, fileLines.length));
        }
        return outputList;
    }

    public static String[] readAsset(Context context, String filename) {

        //TODO:  adjust to no longer have blank initial line???
        try {
            InputStream inputStream = context.getAssets().open(filename);
            FileProxy fileProxy = new FileProxy();
            String fileOutput = fileProxy.readFileToString("", context, inputStream);
            return fileOutput.split("\n");

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
