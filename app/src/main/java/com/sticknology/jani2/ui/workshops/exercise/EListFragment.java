package com.sticknology.jani2.ui.workshops.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercise.Exercise;
import com.sticknology.jani2.base_operations.AssetsHandler;
import com.sticknology.jani2.databinding.FragmentWorkshopExerciseListBinding;

import java.io.IOException;
import java.util.List;

public class EListFragment extends Fragment {

    public EListFragment() {
        // Required empty public constructor
    }

    public static EListFragment newInstance() {

        EListFragment fragment = new EListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop_exercise_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set up binding for class use
        FragmentWorkshopExerciseListBinding mBinding = DataBindingUtil.setContentView(getActivity(),
                R.layout.fragment_workshop_exercise_list);

        //Get Default Exercises
        AssetsHandler assetsHandler = new AssetsHandler();
        //Add default exercise list to list
        List<Exercise> exerciseList = null;
        try {
            exerciseList = assetsHandler.getDefaultExercises(getContext());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Set up rev for list of exercises
        RecyclerView recyclerView = mBinding.revListFwel;
        EListAdapter eListAdapter = new EListAdapter(getActivity(), exerciseList);
        recyclerView.setAdapter(eListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //mBinding.getRoot().findViewById(R.id.rev_list_fwel);
    }
}