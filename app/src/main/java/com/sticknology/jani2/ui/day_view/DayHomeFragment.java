package com.sticknology.jani2.ui.day_view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.sticknology.jani2.R;
import com.sticknology.jani2.base_objects.MDay;
import com.sticknology.jani2.databinding.FragmentDayViewBinding;
import com.sticknology.jani2.ui.home.WorkshopFragment;
import com.sticknology.jani2.ui.workshops.session.SListFragment;
import com.sticknology.jani2.ui.workshops.session.SWorkshopActivity;

public class DayHomeFragment extends Fragment {

    private FragmentDayViewBinding mBinding;
    private MDay selectedDay;

    public DayHomeFragment(){

    }

    public static DayHomeFragment newInstance(){

        return new DayHomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = FragmentDayViewBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mBinding.daySelectCalendarFdv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {

                selectedDay = new MDay(year, day + MDay.daysFromMonths(month, year));
            }
        });

        mBinding.buttonAddsessionFdv.setOnClickListener(view1 -> {

            requireActivity().setContentView(R.layout.activity_home);
            Fragment sessionListFrag = SListFragment.newInstance();
            requireActivity().getSupportFragmentManager().beginTransaction().add(R.id.frag_container_ah, sessionListFrag, null).commit();
        });
    }
}
