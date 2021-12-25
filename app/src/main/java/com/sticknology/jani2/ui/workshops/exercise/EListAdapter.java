package com.sticknology.jani2.ui.workshops.exercise;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.Exercise;
import com.sticknology.jani2.databinding.ReviWorkshopEExpandedBinding;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;

import java.util.List;

public class EListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Exercise> mExerciseList;

    private static int flippedIndex = -1;

    public EListAdapter(List<Exercise> exerciseList){

        mExerciseList = exerciseList;
    }

    @Override
    public int getItemViewType(int position) {

        if(flippedIndex != position){
            return 0;
        } else {
            return 1;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        if(viewType == 0) {
            ReviWorkshopEListBinding binding = ReviWorkshopEListBinding.inflate(layoutInflater, parent, false);
            viewHolder = new ViewHolder1(binding);
        }
        else {
            ReviWorkshopEExpandedBinding binding = ReviWorkshopEExpandedBinding.inflate(layoutInflater, parent, false);
            viewHolder = new ViewHolder2(binding, parent.getContext());
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        EListAdapter eListAdapter = this;
        int mPosition = position;

        if(holder.getItemViewType() == 0) {

            ViewHolder1 vh1 = (ViewHolder1) holder;

            vh1.mBinding.executePendingBindings();

            //Show basic details of Exercises
            vh1.mBinding.setName(mExerciseList.get(position).getName());
            vh1.mBinding.setDescription(mExerciseList.get(position).getDescription());

            vh1.mBinding.cardviewRwel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    flippedIndex = mPosition;
                    eListAdapter.notifyItemChanged(mPosition);
                }
            });
        } else {

            ViewHolder2 vh2 = (ViewHolder2) holder;

            vh2.mBinding.setName(mExerciseList.get(position).getName());
            vh2.mBinding.setDescription(mExerciseList.get(position).getDescription());

            //Set listening to activate edit on selected item
            vh2.mBinding.editButtonRwee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //No idea why, but need to reset setContentView, potentially because of setcontentview above with data binding?
                    ((AppCompatActivity)vh2.mContext).setContentView(R.layout.activity_workshop_exercise);
                    EEditFragment frag = EEditFragment.newInstance(mExerciseList.get(mPosition), true);
                    FragmentManager manager = ((AppCompatActivity)vh2.mContext).getSupportFragmentManager();
                    manager.beginTransaction().addToBackStack("").replace(R.id.frag_container_awe, frag).commit();
                }
            });

            //Set card listener to return to collapsed state
            vh2.mBinding.cardviewRwee.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    flippedIndex = -1;
                    eListAdapter.notifyItemChanged(mPosition);
                }
            });
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

        public ReviWorkshopEListBinding getBinding(){
            return mBinding;
        }
    }

    public static class ViewHolder2 extends RecyclerView.ViewHolder {

        public ReviWorkshopEExpandedBinding mBinding;
        public Context mContext;

        public ViewHolder2(ReviWorkshopEExpandedBinding binding, Context context) {

            super(binding.getRoot());
            mBinding = binding;
            mContext = context;
        }
    }
}
