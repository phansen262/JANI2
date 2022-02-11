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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.data.servers.SessionServer;
import com.sticknology.jani2.databinding.FragmentWorkshopSEditCompsBinding;
import com.sticknology.jani2.ui.home.HomeActivity;
import com.sticknology.jani2.ui.workshops.exercise.EListFragment;

public class SEditFragComps extends Fragment {

    private FragmentWorkshopSEditCompsBinding mBinding;

    public SEditFragComps() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_workshop_s_edit_comps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
        if(requireActivity() instanceof SWorkshopActivity){
            SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(true);
        } else if (requireActivity() instanceof HomeActivity){
            HomeActivity.mActionBar.setDisplayHomeAsUpEnabled(true);
        }

        mBinding = DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_workshop_s_edit_comps);

        //Set up rev
        if(SEditFragLanding.mSession.getEDataList() != null) {

            RecyclerView recyclerView = mBinding.revListFwsec;
            SEditEAdapter sEditEAdapter = new SEditEAdapter();
            recyclerView.setAdapter(sEditEAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        //Listener for add exercise button
        mBinding.addExerciseFwsec.setOnClickListener(view1 -> {

            requireActivity().setContentView(R.layout.activity_workshop_session);
            EListFragment frag = EListFragment.newInstance(true);
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();
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
            requireActivity().setContentView(R.layout.activity_workshop_session);
            SEditFragLanding frag = SEditFragLanding.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();

        } else if(item.getItemId() == R.id.single_item){

            Session saveSession = SEditFragLanding.mSession;
            saveSession.setPath(saveSession.getName());

            SessionServer.saveSession(saveSession, requireContext());

            SEditFragLanding.mSession = null;

            //Move back to session list
            requireActivity().setContentView(R.layout.activity_workshop_session);
            SListFragment frag = SListFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_aws, frag).commit();
        }

        return super.onOptionsItemSelected(item);
    }
}
