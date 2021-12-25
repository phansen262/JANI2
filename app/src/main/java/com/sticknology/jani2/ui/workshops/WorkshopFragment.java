package com.sticknology.jani2.ui.workshops;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.databinding.FragmentHomeWorkshopBinding;
import com.sticknology.jani2.ui.workshops.exercise.EWorkshopActivity;

public class WorkshopFragment extends Fragment {

    public WorkshopFragment(){

    }

    public static WorkshopFragment newInstance() {

        WorkshopFragment fragment = new WorkshopFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_workshop, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Settup class binding
        FragmentHomeWorkshopBinding binding = DataBindingUtil.setContentView(getActivity(),
                R.layout.fragment_home_workshop);

        binding.exercisesFhw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), EWorkshopActivity.class);
                startActivity(intent);
            }
        });
    }
}
