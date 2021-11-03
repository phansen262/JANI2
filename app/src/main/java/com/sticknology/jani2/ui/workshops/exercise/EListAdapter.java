package com.sticknology.jani2.ui.workshops.exercise;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.exercise.Exercise;
import com.sticknology.jani2.base_operations.AssetsHandler;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;

import java.io.IOException;
import java.util.List;

public class EListAdapter extends RecyclerView.Adapter<EListAdapter.ViewHolder> {

    private final Activity mActivity;
    private final List<Exercise> mExerciseList;

    public EListAdapter(Activity activity, List<Exercise> exerciseList){

        mActivity = activity;
        mExerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReviWorkshopEListBinding binding = ReviWorkshopEListBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.mBinding.executePendingBindings();

        //Show basic details of Exercises
        holder.mBinding.nameRwel.setText(mExerciseList.get(position).getName());
        holder.mBinding.descriptionRwel.setText(mExerciseList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ReviWorkshopEListBinding mBinding;

        public ViewHolder(ReviWorkshopEListBinding binding) {

            super(binding.getRoot());
            mBinding = binding;
        }

        public ReviWorkshopEListBinding getBinding(){
            return mBinding;
        }
    }
}
