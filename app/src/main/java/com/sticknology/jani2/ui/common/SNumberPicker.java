package com.sticknology.jani2.ui.common;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.sticknology.jani2.databinding.DialogCommonSnumberpickerBinding;

public class SNumberPicker {

    public static AlertDialog.Builder SNumberPicker(Context context, String title){

        //build alert dialog
        final AlertDialog.Builder d = new AlertDialog.Builder(context);
        DialogCommonSnumberpickerBinding mBinding = DialogCommonSnumberpickerBinding.inflate(LayoutInflater.from(context));
        d.setTitle(title);
        d.setView(mBinding.getRoot());

        mBinding.pickerDcs.setMinValue(0);
        mBinding.pickerDcs.setMaxValue(25);


        return d;

    }
}
