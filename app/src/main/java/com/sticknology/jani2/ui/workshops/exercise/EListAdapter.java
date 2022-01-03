package com.sticknology.jani2.ui.workshops.exercise;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;
import com.sticknology.jani2.ui.workshops.session.SEditFragComps;
import com.sticknology.jani2.ui.workshops.session.SEditFragLanding;

import java.util.ArrayList;
import java.util.List;

public class EListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Exercise> mExerciseList;
    private final FragmentActivity mActivity;
    private final Context mContext;

    public EListAdapter(List<Exercise> exerciseList, FragmentActivity activity, Context context){

        mExerciseList = exerciseList;
        mActivity = activity;
        mContext = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReviWorkshopEListBinding binding = ReviWorkshopEListBinding.inflate(layoutInflater, parent, false);
        RecyclerView.ViewHolder viewHolder = new ViewHolder1(binding);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        //required for some reason otherwise yells at me
        int mPosition = position;


        ViewHolder1 vh1 = (ViewHolder1) holder;
        vh1.mBinding.executePendingBindings();

        //Show basic details of Exercises
        vh1.mBinding.setName(mExerciseList.get(position).getName());
        vh1.mBinding.setDescription(mExerciseList.get(position).getDescription());
        vh1.mBinding.cardviewRwel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog alertDialog = EViewDialog.EViewDialog(mExerciseList.get(mPosition), mPosition, mActivity, mContext);
                alertDialog.show();
            }
        });

        if(EListFragment.fromSession){
            vh1.mBinding.setAddButton(View.VISIBLE);
            vh1.mBinding.addButtonRwel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(SEditFragLanding.mSession.getExerciseList() == null){
                        SEditFragLanding.mSession.setExerciseList(new ArrayList<Exercise>());
                    }
                    SEditFragLanding.mSession.addExercise(EListFragment.userExercises.get(mPosition));
                }
            });
        } else {
            vh1.mBinding.setAddButton(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return mExerciseList.size();
    }


    public static class ViewHolder1 extends RecyclerView.ViewHolder {

        public ReviWorkshopEListBinding mBinding;

        public ViewHolder1(ReviWorkshopEListBinding binding) {

            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
