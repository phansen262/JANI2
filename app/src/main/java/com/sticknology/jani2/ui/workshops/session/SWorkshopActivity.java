package com.sticknology.jani2.ui.workshops.session;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.ui.workshops.exercise.EListFragment;

public class SWorkshopActivity extends AppCompatActivity {

    public static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize actionbar for usage displaying back button during creation
        actionBar = getSupportActionBar();

        setContentView(R.layout.activity_workshop_session);

        //Add base list fragment to activity's view
        //Fragment added here shows list of activities and includes navigation to creating new
        Fragment sessionListFrag = SListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_aws, sessionListFrag, null).commit();
    }
}
