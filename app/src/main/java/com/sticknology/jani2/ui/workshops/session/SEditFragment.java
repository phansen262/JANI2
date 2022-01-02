package com.sticknology.jani2.ui.workshops.session;

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
import com.sticknology.jani2.app_objects.trainingplan.Session;
import com.sticknology.jani2.base_operations.AssetsHandler;
import com.sticknology.jani2.base_operations.ListPicker;
import com.sticknology.jani2.databinding.FragmentWorkshopEEditBinding;
import com.sticknology.jani2.databinding.FragmentWorkshopSEditBinding;
import com.sticknology.jani2.databinding.FragmentWorkshopSListBinding;
import com.sticknology.jani2.ui.workshops.exercise.EListFragment;
import com.sticknology.jani2.ui.workshops.exercise.EWorkshopActivity;

import java.util.ArrayList;

public class SEditFragment extends Fragment {

    private static Session mSession;
    private static boolean mHasInputSession;
    private static int mIndex;

    public SEditFragment() {

    }

    public static SEditFragment newInstance(Session inputSession, int index) {
        mSession = inputSession;
        mHasInputSession = true;
        mIndex = index;
        return new SEditFragment();
    }

    public static SEditFragment newInstance(){
        mHasInputSession = false;
        return new SEditFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        mHasInputSession = getArguments().getBoolean("hasInputSession");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop_s_edit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set app bar button presence
        setHasOptionsMenu(true);
        SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(true);

        //Settup class binding
        FragmentWorkshopSEditBinding binding = DataBindingUtil.setContentView(getActivity(),
                R.layout.fragment_workshop_s_edit);

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
            getActivity().setContentView(R.layout.activity_workshop_session);
            SListFragment frag = SListFragment.newInstance();
            getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            //Perform save function

        }

        return super.onOptionsItemSelected(item);
    }
}
