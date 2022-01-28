package com.sticknology.jani2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long start = System.currentTimeMillis();



        long end = System.currentTimeMillis();

        if((end - start) > 3000){
            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    //Run all code here, need to implement minimum display time
                    System.out.println(3000 - (end - start) + ":  Is elapsed time in millies to initialize app");
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();
                }
            }, 3000 - (end - start));
        }
    }
}
