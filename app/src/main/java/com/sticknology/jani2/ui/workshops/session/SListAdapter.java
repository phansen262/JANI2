package com.sticknology.jani2.ui.workshops.session;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;
import com.sticknology.jani2.databinding.ReviWorkshopSListBinding;
import com.sticknology.jani2.ui.workshops.exercise.EListAdapter;

import java.util.List;

public class SListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Session> mSessionList;

    public SListAdapter(List<Session> sessionList){

        mSessionList = sessionList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReviWorkshopEListBinding binding = ReviWorkshopEListBinding.inflate(layoutInflater, parent, false);
        return new EListAdapter.ViewHolder1(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return mSessionList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ReviWorkshopSListBinding mBinding;

        public ViewHolder(ReviWorkshopSListBinding binding) {

            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
