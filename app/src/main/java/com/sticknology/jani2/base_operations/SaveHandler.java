package com.sticknology.jani2.base_operations;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SaveHandler {

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

    public Object getObjectPayload(Context context, String path){

        Object payload = new Object();

        try {
            FileInputStream fileStream = context.openFileInput(path);
            ObjectInputStream objectInput = new ObjectInputStream(fileStream);
            payload = objectInput.readObject();
            objectInput.close();
            fileStream.close();
        } catch (IOException | ClassNotFoundException e) {

            System.err.println("Unable to read user exercises file");
        }

        return payload;
    }

    public static void saveObject(Object payload, String path, Context context){

        try {
            FileOutputStream outStream = context.openFileOutput(path, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutput = new ObjectOutputStream(outStream);
            objectOutput.writeObject(payload);
            objectOutput.close();
            outStream.close();

        } catch (IOException e){

            System.out.println("Exception saving new exercise list");
        }
    }


}
