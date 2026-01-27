package com.jr.hittheman;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.ProgressBar;
import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity { // Renamed to Splash

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. Fullscreen splash
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        // 2. Progress Bar Animation logic
        ProgressBar progressBar = findViewById(R.id.splashProgress);

        // This links the "progress" property of the bar to an animator
        ObjectAnimator progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        progressAnimator.setDuration(3000); // Match your 3000ms delay
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();

        // 3. Delay for 3 seconds (3000 milliseconds)
        new Handler().postDelayed(() -> {
            // Updated to Splash.this to match the class name
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);

            // Smooth transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

            finish();
        }, 3000);
    }
}
