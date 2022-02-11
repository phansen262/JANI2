package com.sticknology.jani2.ui.workshops.session;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.sessions.SAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.base_objects.DataMap;
import com.sticknology.jani2.databinding.FragmentWorkshopSEditLandingBinding;

import java.util.ArrayList;
import java.util.Collections;

public class SEditFragLanding extends Fragment {

    public static Session mSession;

    protected static boolean mHasInputSession;
    protected static int mIndex;

    private FragmentWorkshopSEditLandingBinding mBinding;

    public SEditFragLanding() {

    }

    public static SEditFragLanding newInstance(Session inputSession, int index) {
        mSession = inputSession;
        mHasInputSession = true;
        mIndex = index;
        return new SEditFragLanding();
    }

    public static SEditFragLanding newInstance(){
        mHasInputSession = false;
        if(mSession == null) {
            mSession = new Session("", "", new ArrayList<>(), new DataMap());
        }
        return new SEditFragLanding();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop_s_edit_landing, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Set app bar button presence
        setHasOptionsMenu(true);
        if(requireActivity() instanceof SWorkshopActivity) {
            SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //Settup class binding
        mBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_workshop_s_edit_landing);

        if(mSession.getName() != null && !mSession.getName().equals("")){
            mBinding.setName(mSession.getName());
        }
        if(mSession.getAttributeItem(SAttributeKeys.DESCRIPTION) != null && !mSession.getAttributeString(SAttributeKeys.DESCRIPTION).equals("")){
            mBinding.setDescription(mSession.getAttributeString(SAttributeKeys.DESCRIPTION));
        }

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.single_item, menu);
        menu.getItem(0).setTitle("Next");
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item){

        if(item.getItemId() == android.R.id.home){

            //Backwards navigation
            requireActivity().setContentView(R.layout.activity_workshop_session);
            SListFragment frag = SListFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            mSession.setName(mBinding.nameFwsel.getText().toString());
            mSession.putAttribute(SAttributeKeys.DESCRIPTION, Collections.singletonList(mBinding.descriptionFwsel.getText().toString()));

            requireActivity().setContentView(R.layout.activity_workshop_session);
            SEditFragComps frag = new SEditFragComps();
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();

        }

        return super.onOptionsItemSelected(item);
    }
}
