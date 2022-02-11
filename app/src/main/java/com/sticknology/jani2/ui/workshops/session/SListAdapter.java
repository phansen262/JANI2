package com.sticknology.jani2.ui.workshops.session;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.sticknology.jani2.app_objects.trainingplan.sessions.Session;
import com.sticknology.jani2.base_objects.MDay;
import com.sticknology.jani2.data.servers.SessionServer;
import com.sticknology.jani2.databinding.ReviWorkshopSListBinding;
import com.sticknology.jani2.ui.day_view.DayHomeFragment;
import com.sticknology.jani2.ui.home.HomeActivity;

import java.util.List;

public class SListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<Session> mSessionList;
    private final FragmentActivity mActivity;

    public SListAdapter(List<Session> sessionList, FragmentActivity activity){

        mSessionList = sessionList;
        mActivity = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ReviWorkshopSListBinding binding = ReviWorkshopSListBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(binding, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ViewHolder vh1 = (ViewHolder) holder;

        vh1.mBinding.setTestText(mSessionList.get(position).getEDataList().get(0).getName());

        if(mActivity instanceof HomeActivity){
            vh1.mBinding.buttonAddRwsl.setVisibility(View.VISIBLE);
            vh1.mBinding.buttonAddRwsl.setOnClickListener(view ->
                    SessionServer.writeAssignedSession(mSessionList.get(position), DayHomeFragment.selectedDay, vh1.mParent.getContext()));
        }
    }

    @Override
    public int getItemCount() {
        return mSessionList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ReviWorkshopSListBinding mBinding;
        public ViewGroup mParent;

        public ViewHolder(ReviWorkshopSListBinding binding, ViewGroup parent) {

            super(binding.getRoot());
            mBinding = binding;
            mParent = parent;
        }
    }
}
