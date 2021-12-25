package com.sticknology.jani2.ui.workshops.exercise;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.sticknology.jani2.R;
import com.sticknology.jani2.base_operations.UserFileInitializer;

public class EWorkshopActivity extends AppCompatActivity {

    public static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize actionbar for usage displaying back button during creation
        actionBar = getSupportActionBar();

        setContentView(R.layout.activity_workshop_exercise);

        //Add base list fragment to activity's view
        //Fragment added here shows list of activities and includes navigation to creating new
        Fragment exerciseListFrag = EListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_awe, exerciseListFrag, null).commit();
    }
}