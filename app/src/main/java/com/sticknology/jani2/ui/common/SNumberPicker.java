package com.sticknology.jani2.ui.common;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.sticknology.jani2.databinding.DialogCommonSnumberpickerBinding;

public class SNumberPicker {

    public static int selectedValue;

    public static AlertDialog.Builder SNumberPicker(Context context, String title, int min, int max){

        //build alert dialog
        final AlertDialog.Builder d = new AlertDialog.Builder(context);
        DialogCommonSnumberpickerBinding mBinding = DialogCommonSnumberpickerBinding.inflate(LayoutInflater.from(context));
        d.setTitle(title);
        d.setView(mBinding.getRoot());

        mBinding.pickerDcs.setMinValue(min);
        mBinding.pickerDcs.setMaxValue(max);

        selectedValue = min;

        mBinding.pickerDcs.setOnValueChangedListener((numberPicker, i, i1) -> {

            selectedValue = numberPicker.getValue();
        });

        return d;
    }
}
