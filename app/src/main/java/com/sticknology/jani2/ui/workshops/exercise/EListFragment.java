package com.sticknology.jani2.ui.workshops.exercise;

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
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.data.servers.ExerciseServer;
import com.sticknology.jani2.databinding.FragmentWorkshopEListBinding;
import com.sticknology.jani2.ui.workshops.session.SWorkshopActivity;

import java.util.List;

public class EListFragment extends Fragment {

    public static List<Exercise> displayExercises;

    public static boolean fromSession;

    public EListFragment() {
        // Required empty public constructor
    }

    //Default behavior of fragment
    public static EListFragment newInstance() {

        fromSession = false;
        return new EListFragment();
    }

    //For including whether or not this is coming from a session workshop request
    public static EListFragment newInstance(boolean session){

        fromSession = session;
        return new EListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop_e_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(!fromSession) {
            setHasOptionsMenu(false);
            EWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(false);
        } else {
            setHasOptionsMenu(true);
            SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Set up binding for class use
        FragmentWorkshopEListBinding mBinding = DataBindingUtil.setContentView(requireActivity(),
                R.layout.fragment_workshop_e_list);

        //Add exercise list to list
        displayExercises = ExerciseServer.getExerciseList();

        //List<Exercise> exerciseList =  AssetsHandler.getDefaultExercises(getContext());

        //Set up rev for list of exercises
        RecyclerView recyclerView = mBinding.revListFwel;
        EListAdapter eListAdapter = new EListAdapter(displayExercises, getActivity(), getContext());
        recyclerView.setAdapter(eListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Set listener for new exercise floating button
        mBinding.buttonNewExerciseFwel.setOnClickListener(view1 -> {

            //No idea why, but need to reset setContentView, potentially because of setcontentview above with data binding?
            requireActivity().setContentView(R.layout.activity_workshop_exercise);
            EEditFragment frag = EEditFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
        });
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.single_item, menu);
        menu.getItem(0).setTitle("Done");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home || item.getItemId() == R.id.single_item){

            //Backwards navigation :  Only from session type
            requireActivity().setContentView(R.layout.activity_workshop_session);
            requireActivity().getSupportFragmentManager().popBackStack();
        }

        return super.onOptionsItemSelected(item);
    }
}