package com.sticknology.jani2.ui.workshops.exercise;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.sticknology.jani2.R;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EAttributeKeys;
import com.sticknology.jani2.app_objects.trainingplan.exercises.EData;
import com.sticknology.jani2.app_objects.trainingplan.exercises.Exercise;

import java.util.ArrayList;
import java.util.List;

public class EViewDialog{

    public static AlertDialog.Builder EViewDialog(Exercise exercise, Context context){

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
        if(exercise.getAttributeItem(EAttributeKeys.RECORD_TYPE.getKey()) != null){
            TextView textView = dialogView.findViewById(R.id.dialog_tv_rectype_dev);
            List<String> recordTypes = exercise.getAttributeItem(EAttributeKeys.RECORD_TYPE.getKey());
            List<String> build = new ArrayList<>();
            String display = "";
            for(String keystring : recordTypes){
                for(EData.EDataKeys dataKey : EData.EDataKeys.values()){
                    if(keystring.equals(dataKey.getKey())){
                        build.add(dataKey.getDisplay());
                    }
                }
            }
            if(recordTypes.size() == 2){
                display = build.get(0) + " and " + build.get(1);
            } else {
                display = build.get(0) + ", " + build.get(1) + ", and " + build.get(2);
            }
            textView.setText(display);
        }

        return d;
    }
}
