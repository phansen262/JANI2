package com.sticknology.jani2.ui.workshops.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.Exercise;
import com.sticknology.jani2.base_operations.AssetsHandler;
import com.sticknology.jani2.base_operations.UserDataHandler;
import com.sticknology.jani2.databinding.FragmentWorkshopEListBinding;
import com.sticknology.jani2.ui.workshops.session.SWorkshopActivity;

import java.util.ArrayList;
import java.util.List;

public class EListFragment extends Fragment {

    public static ArrayList<Exercise> userExercises;

    public static EListAdapter eListAdapter;

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
        return inflater.inflate(R.layout.fragment_workshop_e_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(false);
        EWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(false);

        //Set up binding for class use
        FragmentWorkshopEListBinding mBinding = DataBindingUtil.setContentView(getActivity(),
                R.layout.fragment_workshop_e_list);

        //Add exercise list to list
        if(userExercises == null) {
            userExercises = new UserDataHandler().getUserExercises(getContext());
        }
        //List<Exercise> exerciseList =  AssetsHandler.getDefaultExercises(getContext());

        //Set up rev for list of exercises
        RecyclerView recyclerView = mBinding.revListFwel;
        eListAdapter = new EListAdapter(userExercises, getActivity(), getContext());
        recyclerView.setAdapter(eListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //Set listener for new exercise floating button
        mBinding.buttonNewExerciseFwel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //No idea why, but need to reset setContentView, potentially because of setcontentview above with data binding?
                getActivity().setContentView(R.layout.activity_workshop_exercise);
                EEditFragment frag = EEditFragment.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
            }
        });
    }
}