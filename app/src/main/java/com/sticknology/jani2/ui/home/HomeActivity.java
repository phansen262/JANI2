package com.sticknology.jani2.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.data.ExerciseServer;
import com.sticknology.jani2.data.UserFileInitializer;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Use Below Lines to Clear User Generated Files
        //this.deleteFile("user_exercises.xml");
        //this.deleteFile("user_sessions.xml");

        //TODO: Need to move initialization of files and servers to actual main activity/loading screen when active
        UserFileInitializer.initExerciseUserFile(this);
        ExerciseServer.initializeEServer(this);

        Fragment workshopFragment = WorkshopFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_ah, workshopFragment, null).commit();
    }
}
