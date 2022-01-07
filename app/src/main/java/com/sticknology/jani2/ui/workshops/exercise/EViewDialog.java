package com.sticknology.jani2.ui.workshops.exercise;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;

public class EViewDialog{

    public static AlertDialog EViewDialog(Exercise exercise, int position, FragmentActivity activity, Context context){

        //build alert dialog
        final AlertDialog.Builder d = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_e_view, null, false);
        d.setTitle(exercise.getName());
        d.setView(dialogView);

        //Set UI information
        TextView type = dialogView.findViewById(R.id.dialog_tv_type_dev);
        type.setText(exercise.getType());
        TextView description = dialogView.findViewById(R.id.dialog_tv_description_dev);
        description.setText(exercise.getDescription());
        if(exercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP.getKey()) != null) {
            TextView mgroup = dialogView.findViewById(R.id.dialog_tv_mgroup_dev);
            mgroup.setText(exercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP.getKey()).get(0));
        }

        //Set button behavior
        d.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                activity.setContentView(R.layout.activity_workshop_exercise);
                EEditFragment frag = EEditFragment.newInstance(exercise, position);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frag_container_awe, frag).commit();
            }
        });

        return d.create();
    }
}
