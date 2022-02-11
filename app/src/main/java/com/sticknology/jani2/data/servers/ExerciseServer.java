package com.sticknology.jani2.data.servers;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.data.doms.ExerciseDOM;

import java.util.List;

public class ExerciseServer {

    private static List<Exercise> mExerciseList;

    public static void initializeEServer(Context context){
        mExerciseList = ExerciseDOM.getExerciseList(context, ExerciseDOM.ExerciseFilePath.USER.path);
        System.out.println(mExerciseList.get(0).getAttributeItem(EAttributeKeys.RECORD_TYPE).get(0));
    }

    //Check whether or not named exercise already exists
    public static boolean hasNameMatch(String name){
        for(Exercise exercise : mExerciseList){
            if(name.equals(exercise.getName())){
                return true;
            }
        }
        return false;
    }

    //Return Exercise object requested with name
    public static Exercise getNamedExercise(String name){
        for(Exercise exercise : mExerciseList){
            if(name.equals(exercise.getName())){
                return exercise;
            }
        }
        System.out.println("Returning null exercise from getNamedExercise in Exercise Server");
        return null;
    }

    //Modification methods
    public static void addNewExercise(Exercise exercise, Context context){
        mExerciseList.add(exercise);
        ExerciseDOM.writeUserExercises(context, mExerciseList);
    }

    public static void replaceExercise(Exercise original, Exercise replacement, Context context){
        mExerciseList.set(mExerciseList.indexOf(original), replacement);
        ExerciseDOM.writeUserExercises(context, mExerciseList);
    }

    public static void deleteExercise(Exercise exercise, Context context){
        mExerciseList.remove(exercise);
        ExerciseDOM.writeUserExercises(context, mExerciseList);
    }

    //Get complete list of exercises
    public static List<Exercise> getExerciseList(){

        return mExerciseList;
    }
}
