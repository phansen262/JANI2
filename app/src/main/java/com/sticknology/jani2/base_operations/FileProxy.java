package com.sticknology.jani2.base_operations;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class FileProxy {

    public void appendText(String content, String filename, Context context) {

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_APPEND));
            outputStreamWriter.append(content);
            outputStreamWriter.append("\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void writeFile(String content, String filename, Context context){

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(filename, Context.MODE_PRIVATE));
            outputStreamWriter.write(content);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public void writeFile(String content, String filename, Context context, int pog){

        try {
            File dir = new File(context.getFilesDir(), "session");
            if(!dir.exists()){
                dir.mkdir();
            }
            File gpxFile = new File(dir, "test_name.xml");
            FileOutputStream fos = new FileOutputStream(gpxFile);
            OutputStreamWriter osw  = new OutputStreamWriter(fos);
            osw.write(content);
            osw.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    public List<String> readFileLines(String filename, Context context){

        List<String> ret = new ArrayList<>();

        try {
            InputStreamReader inputStreamReader = new InputStreamReader(context.openFileInput(filename));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString;

            while ((receiveString = bufferedReader.readLine()) != null ) {
                ret.add(receiveString);
            }

            inputStreamReader.close();

        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
