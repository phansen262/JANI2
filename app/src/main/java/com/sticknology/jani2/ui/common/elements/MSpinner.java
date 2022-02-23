package com.sticknology.jani2.ui.common.elements;

import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import java.util.List;

public class MSpinner extends androidx.appcompat.widget.AppCompatSpinner {

    public MSpinner(Context context, Activity activity, List<String> inputList) {
        super(context);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(activity,
                android.R.layout.simple_spinner_item, inputList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);

        //Set margins for spinner dropdown
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        params.setMargins(10, 10, 10, 10);
        this.setLayoutParams(params);
    }
}
