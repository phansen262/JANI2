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
import com.sticknology.jani2.databinding.FragmentWorkshopExerciseListBinding;

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

        //Set up rev for list of exercises
        RecyclerView recyclerView = mBinding.revListFwel;
        recyclerView.setAdapter(new EListAdapter(getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        //mBinding.getRoot().findViewById(R.id.rev_list_fwel);
    }
}