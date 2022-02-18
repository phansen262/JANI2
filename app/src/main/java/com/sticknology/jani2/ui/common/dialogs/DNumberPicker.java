package com.sticknology.jani2.ui.common.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.sticknology.jani2.databinding.DialogCommonDnumberpickerBinding;

public class DNumberPicker {

    public static int selectedValueOne;
    public static int selectedValueTwo;

    public static AlertDialog.Builder DNumberPicker(Context context, String title){

        //build alert dialog
        final AlertDialog.Builder d = new AlertDialog.Builder(context);
        DialogCommonDnumberpickerBinding mBinding = DialogCommonDnumberpickerBinding.inflate(LayoutInflater.from(context));
        d.setTitle(title);
        d.setView(mBinding.getRoot());

        mBinding.picker1Dcd.setMinValue(0);
        mBinding.picker1Dcd.setMaxValue(60);
        mBinding.picker2Dcd.setMinValue(0);
        mBinding.picker2Dcd.setMaxValue(60);

        selectedValueOne = 0;
        selectedValueTwo = 0;

        mBinding.picker1Dcd.setOnValueChangedListener((numberPicker, i, i1) -> {

            selectedValueOne = numberPicker.getValue();
        });
        mBinding.picker2Dcd.setOnValueChangedListener(((numberPicker, i, i1) -> {

            selectedValueTwo = numberPicker.getValue();
        }));

        return d;
    }
}
