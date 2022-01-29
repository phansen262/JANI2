package com.sticknology.jani2.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.sticknology.jani2.data.UserFileInitializer;
import com.sticknology.jani2.data.servers.ExerciseServer;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        long start = System.currentTimeMillis();

        //Use Below Lines to Clear User Generated Files
        //this.deleteFile("user_exercises.xml");
        //this.deleteFile("user_sessions.xml");

        //All app initialization and loading
        UserFileInitializer.initExerciseUserFile(this);
        UserFileInitializer.initSessionRegistry(this);
        ExerciseServer.initializeEServer(this);

        long end = System.currentTimeMillis();

        if((end - start) > 1500){
            startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
            finish();
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    System.out.println(1500 - (end - start) + ":  Is remaining time in millies to initialize app");
                    startActivity(new Intent(SplashScreenActivity.this, HomeActivity.class));
                    finish();
                }
            }, 1500 - (end - start));
        }
    }
}
