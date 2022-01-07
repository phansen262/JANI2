package com.sticknology.jani2.ui.workshops.session;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.databinding.FragmentWorkshopSEditCompsBinding;
import com.sticknology.jani2.ui.workshops.exercise.EListAdapter;
import com.sticknology.jani2.ui.workshops.exercise.EListFragment;

import java.util.ArrayList;

public class SEditFragComps extends Fragment {

    private FragmentWorkshopSEditCompsBinding mBinding;

    public SEditFragComps() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_workshop_s_edit_comps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(true);

        mBinding = DataBindingUtil.setContentView(getActivity(), R.layout.fragment_workshop_s_edit_comps);

        //Set up rev
        if(SEditFragLanding.mSession.getExerciseList() != null) {
            RecyclerView recyclerView = mBinding.revListFwsec;
            //TODO remove filler and make work
            ArrayList<Exercise> filler = new ArrayList<Exercise>();
            EListAdapter eListAdapter = new EListAdapter(filler, getActivity(), getContext());
            recyclerView.setAdapter(eListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        //Listener for add exercise button
        mBinding.addExerciseFwsec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getActivity().setContentView(R.layout.activity_workshop_session);
                EListFragment frag = EListFragment.newInstance(true);
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.single_item, menu);
        menu.getItem(0).setTitle("Save");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home){

            //Backwards navigation
            getActivity().setContentView(R.layout.activity_workshop_session);
            SEditFragLanding frag = SEditFragLanding.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            //Perform save function

        }

        return super.onOptionsItemSelected(item);
    }
}
