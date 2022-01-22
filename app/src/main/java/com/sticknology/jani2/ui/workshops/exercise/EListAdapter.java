package com.sticknology.jani2.ui.workshops.exercise;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;
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

        return new ViewHolder1(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder1 vh1 = (ViewHolder1) holder;
        vh1.mBinding.executePendingBindings();

        //Show basic details of Exercises
        vh1.mBinding.setName(mExerciseList.get(position).getName());
        vh1.mBinding.setDescription(mExerciseList.get(position).getAttributeString(EAttributeKeys.DESCRIPTION.getKey()));
        vh1.mBinding.cardviewRwel.setOnClickListener(view -> {

            AlertDialog.Builder builder = EViewDialog.BuildEViewDialog(mExerciseList.get(vh1.getAdapterPosition()), mContext);
            builder.setPositiveButton("Edit", (dialogInterface, i) -> {

                mActivity.setContentView(R.layout.activity_workshop_exercise);
                EEditFragment frag = EEditFragment.newInstance(mExerciseList.get(vh1.getAdapterPosition()));
                mActivity.getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });

        if(EListFragment.fromSession){
            vh1.mBinding.addButtonRwel.setVisibility(View.VISIBLE);
            vh1.mBinding.addButtonRwel.setOnClickListener(view -> {

                if(SEditFragLanding.mSession.getEDataList() == null){
                    SEditFragLanding.mSession.setEDataList(new ArrayList<>());
                }
                SEditFragLanding.mSession.addExercise(EListFragment.displayExercises.get(vh1.getAdapterPosition()));
            });
        } else {
            vh1.mBinding.addButtonRwel.setVisibility(View.GONE);
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
