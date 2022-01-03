package com.sticknology.jani2.ui.workshops.session;

import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.sticknology.jani2.base_operations.SaveHandler;
import com.sticknology.jani2.databinding.FragmentWorkshopSListBinding;

import java.util.List;

public class SListFragment extends Fragment {

    public SListFragment() {
        // Required empty public constructor
    }

    public static SListFragment newInstance() {

        SListFragment fragment = new SListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workshop_s_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(false);
        SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(false);

        //Set up binding for class use
        FragmentWorkshopSListBinding mBinding = DataBindingUtil.setContentView(getActivity(),
                R.layout.fragment_workshop_s_list);

        //Set recyclerview for list
        if(getContext().getFileStreamPath("user_sessions.scf").exists()) {

            List<Session> sessionList = (List<Session>) new SaveHandler().getObjectPayload(getContext(), "user_sessions.scf");

            RecyclerView recyclerView = mBinding.revListFwsl;
            SListAdapter sListAdapter = new SListAdapter(sessionList);
            recyclerView.setAdapter(sListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        //Set listener for new session floating button
        mBinding.buttonNewSessionFwsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //No idea why, but need to reset setContentView, potentially because of setcontentview above with data binding?
                getActivity().setContentView(R.layout.activity_workshop_session);
                SEditFragLanding frag = SEditFragLanding.newInstance();
                getActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();
            }
        });
    }
}
