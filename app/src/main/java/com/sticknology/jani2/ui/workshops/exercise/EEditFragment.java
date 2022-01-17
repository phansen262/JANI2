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
import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EType;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.app_objects.trainingplan.exercises.ExerciseDOM;
import com.sticknology.jani2.base_operations.ListMethods;
import com.sticknology.jani2.databinding.FragmentWorkshopEEditBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class EEditFragment extends Fragment {

    //Class inputs
    private static Exercise mExercise;
    private static boolean mHasInputExercise;
    private static int mInputExerciseIndex;

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
    public static EEditFragment newInstance(Exercise inputExercise, int inputIndex) {
        mExercise = inputExercise;
        mHasInputExercise = true;
        mInputExerciseIndex = inputIndex;
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
                    List<String> recordPayload = Arrays.asList(EData.EDataKeys.SET.getKey(), EData.EDataKeys.REPS.getKey());
                    mExercise.addAttribute(EAttributeKeys.RECORD_TYPE.getKey(), recordPayload);
                    break;
                case 1:
                    recordPayload = Arrays.asList(EData.EDataKeys.SET.getKey(), EData.EDataKeys.REPS.getKey(), EData.EDataKeys.WEIGHT.getKey());
                    mExercise.addAttribute(EAttributeKeys.RECORD_TYPE.getKey(), recordPayload);
                    break;
                case 2:
                    recordPayload = Arrays.asList(EData.EDataKeys.SET.getKey(), EData.EDataKeys.DURATION.getKey());
                    mExercise.addAttribute(EAttributeKeys.RECORD_TYPE.getKey(), recordPayload);
                    break;
            }
        });


        //Set behavior for filling it edit information from selected exercise
        if(mHasInputExercise){

            binding.setName(mExercise.getName());
            binding.setDescription(mExercise.getDescription());
            binding.setType(ListMethods.matchListIndex(exerciseTypes, mExercise.getType()));

            if(mExercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP.getKey()) != null) {
                binding.setGroup(ListMethods.matchListIndex(muscleGroups,
                        mExercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP.getKey()).get(0)));
                binding.setGroupVisible(View.VISIBLE);
            }

            if(mExercise.getAttributeItem(EAttributeKeys.RECORD_TYPE.getKey()) != null){
                List<String> recordTypes = mExercise.getAttributeItem(EAttributeKeys.RECORD_TYPE.getKey());
                if(recordTypes.size() == 3){
                    binding.rtypeSetsRepsWeightsFwee.setChecked(true);
                } else if(recordTypes.contains(EData.EDataKeys.DURATION.getKey())){
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
            String eDescription = binding.descriptionFwee.getText().toString();
            String eType = binding.typeFwee.getSelectedItem().toString();

            HashMap<String, List<String>> attributes = new HashMap<>();

            if(!binding.groupFwee.getSelectedItem().toString().equals("None")) {
                List<String> mGroup = new ArrayList<>();
                mGroup.add(binding.groupFwee.getSelectedItem().toString());
                attributes.put(EAttributeKeys.MUSCLE_GROUP.getKey(), mGroup);
            }

            int id = binding.typeRadiogroupFwee.getCheckedRadioButtonId();

            RadioButton selected = requireView().findViewById(id);
            String selectedText = String.valueOf(selected.getText());
            switch (selectedText) {
                case "Sets and Reps": {
                    List<String> payload = Arrays.asList(EData.EDataKeys.SET.getKey(), EData.EDataKeys.REPS.getKey());
                    attributes.put(EAttributeKeys.RECORD_TYPE.getKey(), payload);
                    break;
                }
                case "Sets and Reps with Weights": {
                    List<String> payload = Arrays.asList(EData.EDataKeys.SET.getKey(), EData.EDataKeys.REPS.getKey(), EData.EDataKeys.WEIGHT.getKey());
                    attributes.put(EAttributeKeys.RECORD_TYPE.getKey(), payload);
                    break;
                }
                case "Sets and Duration": {
                    List<String> payload = Arrays.asList(EData.EDataKeys.SET.getKey(), EData.EDataKeys.DURATION.getKey());
                    attributes.put(EAttributeKeys.RECORD_TYPE.getKey(), payload);
                    break;
                }
            }

            Exercise saveExercise = new Exercise(eName, eDescription, eType, attributes);

            if(mHasInputExercise) {
                EListFragment.userExercises.set(mInputExerciseIndex, saveExercise);
            } else {
                EListFragment.userExercises.add(saveExercise);
            }

            ExerciseDOM.writeUserExercises(getContext(), EListFragment.userExercises);

            requireActivity().setContentView(R.layout.activity_workshop_exercise);
            EListFragment frag = EListFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}