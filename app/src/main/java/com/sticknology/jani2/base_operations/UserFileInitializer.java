package com.sticknology.jani2.base_operations;

import android.content.Context;
import android.util.Log;

import com.sticknology.jani2.app_objects.trainingplan.Exercise;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserFileInitializer {

    public static void initExerciseUserFile(Context context){

        //TODO: add functionality to permanently delete default exercises
        try {
            //Attempt to open and close file
            FileInputStream userExercises = context.openFileInput("user_exercises.ecf");
            userExercises.close();

        } catch (IOException e){

            try {
                //Attempt to write default exercises to file if not currently exist
                System.out.println("user_exercises.ecf  :  not currently in files, adding now");
                ArrayList<Exercise> defExercises = AssetsHandler.getDefaultExercises(context);
                FileOutputStream outputStream = context.openFileOutput("user_exercises.ecf", Context.MODE_PRIVATE);
                ObjectOutputStream objectOut = new ObjectOutputStream(outputStream);
                objectOut.writeObject(defExercises);
                objectOut.close();
                outputStream.close();

            } catch (IOException bad) {
                System.err.println("Errored trying to write initial exercises to internal file");
            }
        }
    }
}
