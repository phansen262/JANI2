package com.sticknology.jani2.ui.workshops.session;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.databinding.ReviWorkshopEListBinding;
import com.sticknology.jani2.databinding.ReviWorkshopSListBinding;
import com.sticknology.jani2.ui.home.HomeActivity;
import com.sticknology.jani2.ui.workshops.exercise.EListAdapter;

import java.util.List;

public class SListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Session> mSessionList;
    private FragmentActivity mActivity;

    public SListAdapter(List<Session> sessionList, FragmentActivity activity){

        mSessionList = sessionList;
        mActivity = activity;
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

        if(mActivity instanceof HomeActivity){
            vh1.mBinding.buttonAddRwsl.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mSessionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ReviWorkshopSListBinding mBinding;
        public ViewGroup mParent;

        public ViewHolder(ReviWorkshopSListBinding binding) {

            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
