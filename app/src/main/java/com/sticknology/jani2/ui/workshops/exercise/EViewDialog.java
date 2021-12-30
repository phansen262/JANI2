package com.sticknology.jani2.ui.workshops.exercise;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import com.sticknology.jani2.R;

public class EViewDialog{
/*
    public void threePickerDialog(Button buttonObject, View view, Activity activity, Context context, String type){

        buttonObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //build alert dialog
                final AlertDialog.Builder d = new AlertDialog.Builder(context);
                LayoutInflater inflater = activity.getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_three_number_picker, null);
                d.setTitle("Set " + type);
                d.setView(dialogView);





                //Set button behavior
                d.setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (type.equals("Time")) {
                            String time = new MyTime(picker1.getValue(), picker2.getValue(),
                                    picker3.getValue()).getDispString();
                            buttonObject.setText(time);
                        }
                    }
                });
                d.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

                //Final show
                AlertDialog alertDialog = d.create();
                alertDialog.show();
            }
        });
    }*/
}
