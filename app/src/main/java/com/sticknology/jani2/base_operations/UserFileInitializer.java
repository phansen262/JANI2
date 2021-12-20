package com.sticknology.jani2.base_operations;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserFileInitializer {

    public static void initExerciseUserFile(Context context){

        try {
            InputStream inputStream = context.openFileInput("user_exercises.ecf");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = bufferedReader.readLine();
            inputStream.close();

            if(!receiveString.equals("VERSION1.1")){
                //TODO: change error thrown to reflect actual cause
                throw new IOException();
            }

        } catch (IOException e){

            Log.d("File Init", "Created new file");
            FileProxy fileProxy = new FileProxy();
            fileProxy.writeFile("VERSION1.1\n", "user_exercises.ecf", context);
        }
    }
}
