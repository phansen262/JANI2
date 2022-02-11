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
import com.sticknology.jani2.data.servers.SessionServer;
import com.sticknology.jani2.databinding.FragmentWorkshopSListBinding;
import com.sticknology.jani2.ui.home.HomeActivity;

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
        //SWorkshopActivity.actionBar.setDisplayHomeAsUpEnabled(false);

        //Set up binding for class use
        FragmentWorkshopSListBinding mBinding = DataBindingUtil.setContentView(requireActivity(),
                R.layout.fragment_workshop_s_list);

        //Set recyclerview for list
        if(requireContext().getFileStreamPath("session_registry.xml").exists()) {

            RecyclerView recyclerView = mBinding.revListFwsl;
            SListAdapter sListAdapter = new SListAdapter(SessionServer.getSessionList(requireContext()), requireActivity());
            recyclerView.setAdapter(sListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }

        //Set listener for new session floating button
        mBinding.buttonNewSessionFwsl.setOnClickListener(view1 -> {

            //No idea why, but need to reset setContentView, potentially because of setcontentview above with data binding?
            requireActivity().setContentView(R.layout.activity_workshop_session);
            SEditFragLanding frag = SEditFragLanding.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().addToBackStack("").replace(R.id.frag_container_aws, frag).commit();
        });

        if(requireActivity() instanceof HomeActivity){
            System.out.println("DOD DODODO DOD DO D");
        }
    }
}
