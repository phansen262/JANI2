package com.sticknology.jani2.ui.workshops.exercise;

import android.app.Activity;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercise.Exercise;
import com.sticknology.jani2.base_operations.RawHandler;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;

import java.util.List;

public class EListAdapter extends RecyclerView.Adapter<EListAdapter.ViewHolder> {

    private final Activity mActivity;

    public EListAdapter(Activity activity){

        mActivity = activity;
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

        System.out.println("Got Here");

        holder.mBinding.executePendingBindings();

        RawHandler rawHandler = new RawHandler();
        //Add default exercise list to list
        List<Exercise> exerciseList = rawHandler.getDefaultExercises(holder.itemView.getContext());

        holder.mBinding.nameRwel.setText(exerciseList.get(position).getName());
        holder.mBinding.descriptionRwel.setText(exerciseList.get(position).getDescription());


        //holder.mBinding.descriptionRwel.setText("World");
        //holder.mBinding.nameRwel.setText("Hello");
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ReviWorkshopEListBinding mBinding;

        public ViewHolder(ReviWorkshopEListBinding binding) {

            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
