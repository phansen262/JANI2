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
import com.sticknology.jani2.databinding.ReviWorkshopSEItemBinding;
import com.sticknology.jani2.ui.common.DNumberPicker;
import com.sticknology.jani2.ui.common.SNumberPicker;
import com.sticknology.jani2.ui.workshops.exercise.EViewDialog;

import java.util.Collections;
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

        vh.mBinding.setName(eData.getName());
        vh.mBinding.nameRwel.setOnClickListener(view -> {
            AlertDialog.Builder builder = EViewDialog.BuildEViewDialog(eData, vh.mParent.getContext());
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        List<String> typeList = eData.getAttributeItem(EAttributeKeys.RECORD_TYPE);
        vh.mBinding.setLabel1(typeList.get(0));
        vh.mBinding.field1Rwsei.setOnClickListener(view -> {
            setEditDialogs(typeList.get(0), typeList.get(0), vh, eData, typeList);
        });
        vh.mBinding.setLabel2(typeList.get(1));
        vh.mBinding.field2Rwsei.setOnClickListener(view -> {
            setEditDialogs(typeList.get(1), typeList.get(1), vh, eData, typeList);
        });
        if(typeList.size() == 2){
            vh.mBinding.field3Rwsei.setVisibility(View.GONE);
        } else {
            vh.mBinding.setLabel3(typeList.get(2));
            vh.mBinding.field3Rwsei.setOnClickListener(view -> {
                setEditDialogs(typeList.get(2), typeList.get(2), vh, eData, typeList);
            });
        }

        loopDataDisplays(vh, eData, typeList);
    }

    public void loopDataDisplays(ViewHolder vh, EData eData, List<String> typeList){

        for(int i = 0; i < typeList.size(); i++){

            setDataDisplays(vh, eData, typeList.get(i), i);

        }
    }

    public void setDataDisplays(ViewHolder vh, EData eData, String type, int index){


    }

    public void setEditDialogs(String key, String title, ViewHolder vh, EData eData, List<String> typeList){

        switch (key){
            case "set":
                AlertDialog.Builder setBuilder = SNumberPicker.
                        SNumberPicker(vh.mParent.getContext(), title, 1, 10);
                setBuilder.setPositiveButton("Enter", (dialogInterface, i) -> {
                    eData.addIntData(EDataKeys.SET, new int[]{SNumberPicker.selectedValue});
                    loopDataDisplays(vh, eData, typeList);
                });
                setBuilder.create().show();
                break;
            case "reps":
                AlertDialog.Builder repBuilder = SNumberPicker.
                        SNumberPicker(vh.mParent.getContext(), title, 1, 50);
                repBuilder.setPositiveButton("Enter", (dialogInterface, i) -> {
                    eData.addIntData(EDataKeys.REPS, new int []{SNumberPicker.selectedValue});
                    loopDataDisplays(vh, eData, typeList);
                });
                repBuilder.create().show();
                break;
            case "duration":
                AlertDialog.Builder durationBuilder = DNumberPicker.DNumberPicker(vh.mParent.getContext(), title);
                durationBuilder.setPositiveButton("Enter", ((dialogInterface, i) -> {
                    List<MTime> timeData = Collections.singletonList(new MTime(0, DNumberPicker.selectedValueOne, DNumberPicker.selectedValueTwo));
                    eData.addTimeData(EDataKeys.DURATION, timeData);
                    loopDataDisplays(vh, eData, typeList);
                }));
                durationBuilder.create().show();
                break;
            case "weight":
                AlertDialog.Builder weightBuilder = SNumberPicker.
                        SNumberPicker(vh.mParent.getContext(), title, 5, 50);
                weightBuilder.setPositiveButton("Enter", (dialogInterface, i) -> {
                    eData.addIntData(EDataKeys.WEIGHT, new int[]{SNumberPicker.selectedValue});
                    loopDataDisplays(vh, eData, typeList);
                });
                weightBuilder.create().show();
                break;
            default:
                System.out.println("Error:  key not found to display set dialog for setEditDialogs in SEditEAdapter");
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
