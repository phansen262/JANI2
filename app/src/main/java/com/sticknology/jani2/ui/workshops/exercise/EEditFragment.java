package com.sticknology.jani2.ui.workshops.exercise;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sticknology.jani2.R;


public class EEditFragment extends Fragment {

    public EEditFragment() {

    }

    public static EEditFragment newInstance(String param1, String param2) {
        EEditFragment fragment = new EEditFragment();
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
        return inflater.inflate(R.layout.fragment_workshop_e_edit, container, false);
    }
}