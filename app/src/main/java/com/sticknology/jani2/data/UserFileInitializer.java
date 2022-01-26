package com.sticknology.jani2.data;

import android.content.Context;

import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_operations.FileProxy;
import com.sticknology.jani2.data.doms.ExerciseDOM;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class UserFileInitializer {

    public static void initExerciseUserFile(Context context){

        //TODO: add functionality to permanently delete default exercises?
        try {
            //Attempt to open and close file
            FileInputStream userExercises = context.openFileInput("user_exercises.xml");
            userExercises.close();

        } catch (IOException e){

            //Attempt to write default exercises to xml file if not currently exist
            System.out.println("user_exercises.xml  :  not currently in files, adding now");
            ArrayList<Exercise> defaultExercises = ExerciseDOM.getExerciseList(context, ExerciseDOM.ExerciseFilePath.DEFAULT.path);
            ExerciseDOM.writeUserExercises(context, Objects.requireNonNull(defaultExercises));
        }
    }

    public static void initSessionRegistry(Context context){

        try{
            FileInputStream sessionRegistry = context.openFileInput(UserFileName.SESSION_REGISTRY.getPath());
            sessionRegistry.close();

        } catch (IOException e){

            System.out.println(UserFileName.SESSION_REGISTRY.getPath() + "  : not currently in files, getting now");
            FileProxy fileProxy = new FileProxy();
            fileProxy.writeFile("", UserFileName.SESSION_REGISTRY.getPath(), context);
        }

        File mydir = new File(context.getFilesDir(), "session");
        if (!mydir.exists()) {
            mydir.mkdirs();
        }
    }
}
