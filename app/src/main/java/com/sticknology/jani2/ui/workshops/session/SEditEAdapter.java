package com.sticknology.jani2.ui.workshops.session;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.edata.EData;
import com.sticknology.jani2.app_objects.trainingplan.edata.EDataKeys;
import com.sticknology.jani2.base_objects.MTime;
import com.sticknology.jani2.base_operations.ListMethods;
import com.sticknology.jani2.databinding.ReviWorkshopSEItemBinding;
import com.sticknology.jani2.ui.common.DNumberPicker;
import com.sticknology.jani2.ui.common.SNumberPicker;
import com.sticknology.jani2.ui.workshops.exercise.EViewDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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

        vh.mBinding.setName(eData.getName());
        vh.mBinding.nameRwel.setOnClickListener(view -> {
            AlertDialog.Builder builder = EViewDialog.BuildEViewDialog(eData, vh.mParent.getContext());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        List<String> stringTypeList = eData.getAttributeItem(EAttributeKeys.RECORD_TYPE);
        List<EDataKeys> typeList = new ArrayList<>();
        for(String s : stringTypeList){
            typeList.add(EDataKeys.valueOf(s.toUpperCase(Locale.ROOT)));
        }
        //Field 1
        vh.mBinding.setLabel1(stringTypeList.get(0));
        vh.mBinding.field1Rwsei.setOnClickListener(view -> {
            setEditDialogs(typeList.get(0), typeList.get(0).getDisplay(), vh, eData, typeList);
        });
        //Field 2
        if(typeList.size() == 1){
            vh.mBinding.field2Rwsei.setVisibility(View.GONE);
        } else {
            vh.mBinding.setLabel2(stringTypeList.get(1));
            vh.mBinding.field2Rwsei.setOnClickListener(view -> {
                setEditDialogs(typeList.get(1), typeList.get(1).getDisplay(), vh, eData, typeList);
            });
        }
        //Field 3
        if(typeList.size() == 2){
            vh.mBinding.field3Rwsei.setVisibility(View.GONE);
        } else {
            vh.mBinding.setLabel3(stringTypeList.get(2));
            vh.mBinding.field3Rwsei.setOnClickListener(view -> {
                setEditDialogs(typeList.get(2), typeList.get(2).getDisplay(), vh, eData, typeList);
            });
        }


        loopDataDisplays(vh, eData, typeList);
    }

    public void loopDataDisplays(ViewHolder vh, EData eData, List<EDataKeys> typeList){

        for(int i = 0; i < typeList.size(); i++){

            setDataDisplays(vh, eData, typeList.get(i), i);
        }
    }

    public void setDataDisplays(ViewHolder vh, EData eData, EDataKeys type, int index){

        if(eData.getPayloadItem(type) == null){
            return;
        }

        switch (index){
            case 0: vh.mBinding.setData1(eData.getPayloadItem(type).get(0));
                    break;
            case 1: vh.mBinding.setData2(eData.getPayloadItem(type).get(0));
                    break;
            case 2: vh.mBinding.setData3(eData.getPayloadItem(type).get(0));
                    break;
        }
    }

    public void setEditDialogs(EDataKeys key, String title, ViewHolder vh, EData eData, List<EDataKeys> typeList){

        if(key == EDataKeys.SET) {
            AlertDialog.Builder setBuilder = SNumberPicker.
                    SNumberPicker(vh.mParent.getContext(), title, 1, 10);
            setBuilder.setPositiveButton("Enter", (dialogInterface, i) -> {
                eData.addIntData(EDataKeys.SET, new int[]{SNumberPicker.selectedValue});
                loopDataDisplays(vh, eData, typeList);
            });
            setBuilder.create().show();
        } else if(key == EDataKeys.REPS) {
            AlertDialog.Builder repBuilder = SNumberPicker.
                    SNumberPicker(vh.mParent.getContext(), title, 1, 50);
            repBuilder.setPositiveButton("Enter", (dialogInterface, i) -> {
                eData.addIntData(EDataKeys.REPS, new int[]{SNumberPicker.selectedValue});
                loopDataDisplays(vh, eData, typeList);
            });
            repBuilder.create().show();
        } else if(key == EDataKeys.DURATION) {
            AlertDialog.Builder durationBuilder = DNumberPicker.DNumberPicker(vh.mParent.getContext(), title);
            durationBuilder.setPositiveButton("Enter", ((dialogInterface, i) -> {
                List<MTime> timeData = Collections.singletonList(new MTime(0, DNumberPicker.selectedValueOne, DNumberPicker.selectedValueTwo));
                eData.addTimeData(EDataKeys.DURATION, timeData);
                loopDataDisplays(vh, eData, typeList);
            }));
            durationBuilder.create().show();
        } else if(key == EDataKeys.WEIGHT) {
            AlertDialog.Builder weightBuilder = SNumberPicker.
                    SNumberPicker(vh.mParent.getContext(), title, 5, 50);
            weightBuilder.setPositiveButton("Enter", (dialogInterface, i) -> {
                eData.addIntData(EDataKeys.WEIGHT, new int[]{SNumberPicker.selectedValue});
                loopDataDisplays(vh, eData, typeList);
            });
            weightBuilder.create().show();
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
