package com.sticknology.jani2.ui.workshops.session;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.databinding.ReviWorkshopSEItemBinding;
import com.sticknology.jani2.ui.workshops.exercise.EViewDialog;

import java.util.List;

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
        vh.mBinding.nameRwel.setOnClickListener(view -> {
            AlertDialog.Builder builder = EViewDialog.BuildEViewDialog(eData.getKey(), vh.mParent.getContext());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        List<String> typeList = eData.getKey().getAttributeItem(EAttributeKeys.RECORD_TYPE.getKey());
        vh.mBinding.setLabel1(EData.getDisplayFromKey(typeList.get(0)));
        vh.mBinding.field1Rwsei.setOnClickListener(view -> {

        });
        vh.mBinding.setLabel2(EData.getDisplayFromKey(typeList.get(1)));
        vh.mBinding.field2Rwsei.setOnClickListener(view -> {

        });
        if(typeList.size() == 2){
            vh.mBinding.field3Rwsei.setVisibility(View.GONE);
        } else {
            vh.mBinding.setLabel3(EData.getDisplayFromKey(typeList.get(2)));
            vh.mBinding.field3Rwsei.setOnClickListener(view -> {

            });
        }
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
