package com.sticknology.jani2.ui.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.sticknology.jani2.R;
import com.sticknology.jani2.ui.day_view.DayHomeFragment;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        BottomNavigationView bnv = findViewById(R.id.bottom_navigation);
        bnv.setSelectedItemId(R.id.day_bnav);
        Fragment initialFrag = DayHomeFragment.newInstance();
        getSupportFragmentManager().beginTransaction().add(R.id.frag_container_ah, initialFrag, null).commit();

        bnv.setOnItemSelectedListener(item -> {

            switch (item.getItemId()){
                case R.id.day_bnav:
                    Fragment dayHomeFragment = DayHomeFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_ah, dayHomeFragment, null).commit();
                    break;
                case R.id.progress_bnav:
                    break;
                case R.id.workshop_bnav:
                    Fragment workshopFragment = WorkshopFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_ah, workshopFragment, null).commit();
                    break;
                case R.id.settings_bnav:
                    break;
            }

            return true;
        });

        //Currently duplicate of above, but might add custom later
        bnv.setOnItemReselectedListener(item -> {

            switch (item.getItemId()){
                case R.id.day_bnav:
                    break;
                case R.id.progress_bnav:
                    break;
                case R.id.workshop_bnav:
                    Fragment workshopFragment = WorkshopFragment.newInstance();
                    getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_ah, workshopFragment, null).commit();
                    break;
                case R.id.settings_bnav:
                    break;
            }
        });
    }

}
