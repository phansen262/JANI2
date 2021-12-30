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
import com.sticknology.jani2.base_objects.Carrier;
import com.sticknology.jani2.base_operations.AssetsHandler;
import com.sticknology.jani2.base_operations.ListPicker;
import com.sticknology.jani2.base_operations.UserDataHandler;
import com.sticknology.jani2.databinding.FragmentWorkshopEEditBinding;

import java.util.ArrayList;

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
        EEditFragment fragment = new EEditFragment();
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
        binding = DataBindingUtil.setContentView(getActivity(),
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

        //Set behavior for filling it edit information from selected exercise
        if(mHasInputExercise){
            binding.setName(mExercise.getName());
            binding.setDescription(mExercise.getDescription());
            binding.setType(ListPicker.matchListIndex(exerciseTypes, mExercise.getType()));

            if(mExercise.getMuscleGroups() != null) {
                binding.setGroup(ListPicker.matchListIndex(muscleGroups, mExercise.getMuscleGroups()[0]));
                binding.setGroupVisible(View.VISIBLE);
            }

        }

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
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            String eName = binding.nameFwee.getText().toString();
            String eDescription = binding.descriptionFwee.getText().toString();
            String eType = binding.typeFwee.getSelectedItem().toString();
            String mGroup = binding.groupFwee.getSelectedItem().toString();

            ArrayList<Carrier> attributes = new ArrayList<Carrier>();
            Carrier mGroups = new Carrier("MGROUP", mGroup);
            attributes.add(mGroups);

            Exercise saveExercise = new Exercise(eName, eDescription, eType, attributes);

            if(mHasInputExercise) {
                EListFragment.userExercises.set(mInputExerciseIndex, saveExercise);
            } else {
                EListFragment.userExercises.add(saveExercise);
            }

            EListAdapter.flippedIndex = -1;

            UserDataHandler.saveExercises(EListFragment.userExercises, getContext());

            getActivity().setContentView(R.layout.activity_workshop_exercise);
            EListFragment frag = EListFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}