package com.sticknology.jani2.ui.home;

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
import com.sticknology.jani2.ui.workshops.session.SWorkshopActivity;

public class WorkshopFragment extends Fragment {

    private FragmentHomeWorkshopBinding mBinding;

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

        mBinding = FragmentHomeWorkshopBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Settup class binding

        mBinding.exercisesFhw.setOnClickListener(view1 -> {

            Intent intent = new Intent(getActivity(), EWorkshopActivity.class);
            startActivity(intent);
        });

        mBinding.sessionsFhw.setOnClickListener(view12 -> {

            Intent intent = new Intent(getActivity(), SWorkshopActivity.class);
            startActivity(intent);
        });

    }
}
