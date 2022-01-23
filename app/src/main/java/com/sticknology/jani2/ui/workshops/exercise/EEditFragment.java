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
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.other.Muscle;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.edata.EDataKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EType;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.base_operations.ListMethods;
import com.sticknology.jani2.data.ExerciseServer;
import com.sticknology.jani2.databinding.FragmentWorkshopEEditBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EEditFragment extends Fragment {

    //TODO:  Fix entering this fragment from elist by way of session editor

    //Class inputs
    private static Exercise mExercise;
    private static boolean mHasInputExercise;

    //Class Variables
    private FragmentWorkshopEEditBinding binding;

    //Required empty constructor
    public EEditFragment() {

    }

    //Default constructor without input exercise
    public static EEditFragment newInstance(){
        mHasInputExercise = false;
        return new EEditFragment();
    }

    //Constructor to use if edit as opposed to new
    public static EEditFragment newInstance(Exercise inputExercise) {
        mExercise = inputExercise;
        mHasInputExercise = true;
        return new EEditFragment();
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
        binding = DataBindingUtil.setContentView(requireActivity(),
                R.layout.fragment_workshop_e_edit);

        //Set up spinner for type
        ArrayList<String> exerciseTypes = new ArrayList<>();
        for(EType type : EType.values()){
            exerciseTypes.add(type.getName());
        }
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, exerciseTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.typeFwee.setAdapter(typeAdapter);
        binding.setType(ListMethods.matchListIndex(exerciseTypes, "None"));

        //Set up spinner for muscle group initial
        ArrayList<String> muscleGroups = new ArrayList<>();
        for(Muscle.MGroup group : Muscle.MGroup.values()){
            muscleGroups.add(group.getName());
        }
        ArrayAdapter<String> groupAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item, muscleGroups);
        groupAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.groupFwee.setAdapter(groupAdapter);
        binding.setGroup(ListMethods.matchListIndex(muscleGroups, "None"));
        binding.setGroupVisible(View.GONE);

        //Set up radio group for record type
        binding.typeRadiogroupFwee.setOnCheckedChangeListener((radioGroup, i) -> {

            switch (i){
                case 0:
                    List<String> recordPayload = Arrays.asList(EDataKeys.SET.getKey(), EDataKeys.REPS.getKey());
                    mExercise.putAttribute(EAttributeKeys.RECORD_TYPE, recordPayload);
                    break;
                case 1:
                    recordPayload = Arrays.asList(EDataKeys.SET.getKey(), EDataKeys.REPS.getKey(), EDataKeys.WEIGHT.getKey());
                    mExercise.putAttribute(EAttributeKeys.RECORD_TYPE, recordPayload);
                    break;
                case 2:
                    recordPayload = Arrays.asList(EDataKeys.SET.getKey(), EDataKeys.DURATION.getKey());
                    mExercise.putAttribute(EAttributeKeys.RECORD_TYPE, recordPayload);
                    break;
            }
        });


        //Set behavior for filling it edit information from selected exercise
        if(mHasInputExercise){

            binding.setName(mExercise.getName());
            binding.setDescription(mExercise.getAttributeString(EAttributeKeys.DESCRIPTION));
            binding.setType(ListMethods.matchListIndex(exerciseTypes, mExercise.getAttributeString(EAttributeKeys.EXERCISE_TYPE)));

            if(mExercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP) != null) {
                binding.setGroup(ListMethods.matchListIndex(muscleGroups,
                        mExercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP).get(0)));
                binding.setGroupVisible(View.VISIBLE);
            }

            if(mExercise.getAttributeItem(EAttributeKeys.RECORD_TYPE) != null){
                List<String> recordTypes = mExercise.getAttributeItem(EAttributeKeys.RECORD_TYPE);
                if(recordTypes.size() == 3){
                    binding.rtypeSetsRepsWeightsFwee.setChecked(true);
                } else if(recordTypes.contains(EDataKeys.DURATION.getKey())){
                    binding.rtypeSetsDurationFwee.setChecked(true);
                } else {
                    binding.rtypeSetsRepsFwee.setChecked(true);
                }
            }
        }

        //Listener for type to trigger visibility
        binding.typeFwee.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if(binding.typeFwee.getSelectedItem().toString().matches("Strength|Weights")){
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
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.single_item, menu);
        menu.getItem(0).setTitle("Save");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home){

            //Backwards navigation
            requireActivity().setContentView(R.layout.activity_workshop_exercise);
            EListFragment frag = EListFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            String eName = binding.nameFwee.getText().toString();

            DataMap attributes = new DataMap();

            String eDescription = binding.descriptionFwee.getText().toString();
            String eType = binding.typeFwee.getSelectedItem().toString();

            attributes.put(EAttributeKeys.DESCRIPTION, Collections.singletonList(eDescription));
            attributes.put(EAttributeKeys.EXERCISE_TYPE, Collections.singletonList(eType));

            if(!binding.groupFwee.getSelectedItem().toString().equals("None")) {
                List<String> mGroup = new ArrayList<>();
                mGroup.add(binding.groupFwee.getSelectedItem().toString());
                attributes.put(EAttributeKeys.MUSCLE_GROUP, mGroup);
            }

            int id = binding.typeRadiogroupFwee.getCheckedRadioButtonId();

            RadioButton selected = requireView().findViewById(id);
            String selectedText = String.valueOf(selected.getText());
            switch (selectedText) {
                case "Sets and Reps": {
                    List<String> payload = Arrays.asList(EDataKeys.SET.getKey(), EDataKeys.REPS.getKey());
                    attributes.put(EAttributeKeys.RECORD_TYPE, payload);
                    break;
                }
                case "Sets and Reps with Weights": {
                    List<String> payload = Arrays.asList(EDataKeys.SET.getKey(), EDataKeys.REPS.getKey(), EDataKeys.WEIGHT.getKey());
                    attributes.put(EAttributeKeys.RECORD_TYPE, payload);
                    break;
                }
                case "Sets and Duration": {
                    List<String> payload = Arrays.asList(EDataKeys.SET.getKey(), EDataKeys.DURATION.getKey());
                    attributes.put(EAttributeKeys.RECORD_TYPE, payload);
                    break;
                }
            }

            Exercise saveExercise = new Exercise(eName, attributes);

            if(mHasInputExercise) {
                ExerciseServer.replaceExercise(mExercise, saveExercise, requireContext());
            } else {
                ExerciseServer.addNewExercise(saveExercise, requireContext());
            }

            requireActivity().setContentView(R.layout.activity_workshop_exercise);
            EListFragment frag = EListFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}