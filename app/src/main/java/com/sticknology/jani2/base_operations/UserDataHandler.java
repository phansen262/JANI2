package com.sticknology.jani2.base_operations;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.Exercise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDataHandler {

    //TODO:  Move to Exercise.java??
    //TODO:  Create common file path enum
    public ArrayList<Exercise> getUserExercises(Context context){

        ArrayList<Exercise> userExercises = new ArrayList<>();

        try {
            FileInputStream fileStream = context.openFileInput("user_exercises.ecf");
            ObjectInputStream objectInput = new ObjectInputStream(fileStream);
            userExercises = (ArrayList<Exercise>) objectInput.readObject();
            objectInput.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Unable to read user exercises file");
        }

        return userExercises;
    }

    public static void saveExercises(ArrayList<Exercise> exercises, Context context){

        try {
            FileOutputStream outStream = context.openFileOutput("user_exercises.ecf", Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(outStream);
            objectOutput.writeObject(exercises);
            objectOutput.close();
            outStream.close();

        } catch (IOException e){
            System.out.println("Exception saving new exercise list");
        }
    }


}
