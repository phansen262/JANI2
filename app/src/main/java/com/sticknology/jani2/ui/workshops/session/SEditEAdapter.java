package com.sticknology.jani2.ui.workshops.session;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.databinding.ReviWorkshopSEItemBinding;
import com.sticknology.jani2.ui.workshops.exercise.EViewDialog;

public class SEditEAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public SEditEAdapter(){

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        LayoutInflater layoutInflator = LayoutInflater.from(parent.getContext());
        ReviWorkshopSEItemBinding binding = ReviWorkshopSEItemBinding.inflate(layoutInflator, parent, false);
        return new ViewHolder(binding, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){

        ViewHolder vh = (ViewHolder) holder;
        EData eData = SEditFragLanding.mSession.getEDataList().get(position);

        vh.mBinding.setName(eData.getKey().getName());
        vh.mBinding.nameRwel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = EViewDialog.EViewDialog(eData.getKey(), vh.mParent.getContext());
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount(){return SEditFragLanding.mSession.getEDataList().size();}

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ReviWorkshopSEItemBinding mBinding;
        public ViewGroup mParent;

        public ViewHolder(ReviWorkshopSEItemBinding binding, @NonNull ViewGroup parent){

            super(binding.getRoot());
            mBinding = binding;
            mParent = parent;
        }
    }
}
