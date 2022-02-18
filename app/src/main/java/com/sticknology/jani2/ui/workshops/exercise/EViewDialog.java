package com.sticknology.jani2.ui.workshops.exercise;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.edata.EData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;
import com.sticknology.jani2.base_operations.ListMethods;

import java.util.ArrayList;
import java.util.List;

public class EViewDialog{

    public static AlertDialog.Builder BuildEViewDialog(Exercise exercise, Context context){

        //build alert dialog
        final AlertDialog.Builder d = new AlertDialog.Builder(context);
        View dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_e_view, null, false);
        d.setTitle(exercise.getName());
        d.setView(dialogView);

        //Set UI information
        TextView type = dialogView.findViewById(R.id.dialog_tv_type_dev);
        type.setText(exercise.getAttributeString(EAttributeKeys.EXERCISE_TYPE));
        TextView description = dialogView.findViewById(R.id.dialog_tv_description_dev);
        description.setText(exercise.getAttributeString(EAttributeKeys.DESCRIPTION));
        if(exercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP) != null) {
            TextView mgroup = dialogView.findViewById(R.id.dialog_tv_mgroup_dev);
            mgroup.setText(exercise.getAttributeItem(EAttributeKeys.MUSCLE_GROUP).get(0));
        }
        if(exercise.getAttributeItem(EAttributeKeys.RECORD_TYPE) != null){
            TextView textView = dialogView.findViewById(R.id.dialog_tv_rectype_dev);
            List<String> recordTypes = exercise.getAttributeItem(EAttributeKeys.RECORD_TYPE);
            textView.setText(ListMethods.joinList(recordTypes));
        }

        return d;
    }
}
