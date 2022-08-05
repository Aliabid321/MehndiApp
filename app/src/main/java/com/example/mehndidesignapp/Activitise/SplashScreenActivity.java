package com.example.mehndidesignapp.Activitise;

import androidx.appcompat.app.AppCompatActivity;
import com.example.mehndidesignapp.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mehndidesignapp.Activitise.*;

import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}