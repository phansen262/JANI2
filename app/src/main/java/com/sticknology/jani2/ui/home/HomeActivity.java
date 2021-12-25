package com.sticknology.jani2.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.base_operations.UserFileInitializer;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //TODO: Need to move initialization of files to actual main activity when active
        UserFileInitializer.initExerciseUserFile(this);

        Fragment workshopFragment = WorkshopFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_ah, workshopFragment, null).commit();
    }
}
