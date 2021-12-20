package com.sticknology.jani2.ui.workshops.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.Exercise;
import com.sticknology.jani2.base_operations.AssetsHandler;
import com.sticknology.jani2.base_operations.ListPicker;
import com.sticknology.jani2.databinding.FragmentWorkshopEEditBinding;

import java.util.ArrayList;

public class EEditFragment extends Fragment {

    private Exercise mExercise;

    public EEditFragment() {

    }

    public static EEditFragment newInstance() {
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set app bar button presence
        setHasOptionsMenu(true);
        EWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(true);

        //Settup class binding
        FragmentWorkshopEEditBinding binding = DataBindingUtil.setContentView(getActivity(),
                R.layout.fragment_workshop_e_edit);

        //Set up spinner for type
        ArrayList<String> exerciseTypes = AssetsHandler.getExerciseTypes(getContext());
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, exerciseTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeFwee.setAdapter(typeAdapter);
        binding.setType(ListPicker.matchListIndex(exerciseTypes, "None"));

        //Set up spinner for muscle group initial
        ArrayList<String> muscleGroups = AssetsHandler.getMuscleTypes(getContext());
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, muscleGroups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.groupFwee.setAdapter(groupAdapter);
        binding.setGroup(ListPicker.matchListIndex(muscleGroups, "None"));
        binding.setGroupVisible(View.GONE);

        //Listener for type to trigger visibility
        binding.typeFwee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(binding.typeFwee.getSelectedItem().toString().matches("Strength||Weights")){
                    binding.setGroupVisible(View.VISIBLE);
                } else {
                    binding.setGroupVisible(View.GONE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

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
            getActivity().setContentView(R.layout.activity_workshop_exercise);
            EListFragment frag = EListFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_awe, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            //Perform save function

        }

        return super.onOptionsItemSelected(item);
    }
}