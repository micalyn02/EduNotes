package com.example.edunotes;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashActivity extends AppCompatActivity {

    // developer flag, can be removed for launch
    private static final boolean FORCE_LOGOUT_ON_START = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        // can be removed for before launch
        if (FORCE_LOGOUT_ON_START) {
            // clear shared preferences on start
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            prefs.edit().putBoolean("isLoggedIn", false).apply();
        }

        // user handler to delay the transition to the next activity
        new Handler().postDelayed(() -> {

            // shared preferences to check if the user is logged in or not
            SharedPreferences prefs = getSharedPreferences("prefs", MODE_PRIVATE);
            boolean isLoggedIn = prefs.getBoolean("isLoggedIn", false);

            // check if user is logged in or not and start the appropriate activity
            // if user is logged in, start MainActivity, otherwise start LoginActivity
            if (isLoggedIn) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
            } else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
            finish(); // close the splash activity
        }, 2000); // 2 seconds splash delay
    }
}