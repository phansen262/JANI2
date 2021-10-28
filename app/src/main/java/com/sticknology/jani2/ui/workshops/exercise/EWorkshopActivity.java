package com.sticknology.jani2.ui.workshops.exercise;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.sticknology.jani2.R;

public class EWorkshopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_workshop_exercise);

        //Add base list fragment to activity's view
        //Fragment added here shows list of activities and includes navigation to creating new
        Fragment exerciseListFrag = EListFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.workshop_e_frag_container, exerciseListFrag).commit();
    }
}