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
        ReviWorkshopSListBinding binding = ReviWorkshopSListBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder vh1 = (ViewHolder) holder;

        vh1.mBinding.setTestText(mSessionList.get(0).getEDataList().get(0).getName());
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
