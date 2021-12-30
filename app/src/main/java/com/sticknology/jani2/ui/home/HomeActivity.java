package com.sticknology.jani2.ui.home;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.Exercise;
import com.sticknology.jani2.base_objects.Carrier;
import com.sticknology.jani2.base_operations.UserDataHandler;
import com.sticknology.jani2.base_operations.UserFileInitializer;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Use Below Line to Clear User Generated Exercises and reset to default exercise list
        //this.deleteFile("user_exercises.ecf");

        //TODO: Need to move initialization of files to actual main activity when active
        UserFileInitializer.initExerciseUserFile(this);

        Fragment workshopFragment = WorkshopFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_ah, workshopFragment, null).commit();
    }
}
