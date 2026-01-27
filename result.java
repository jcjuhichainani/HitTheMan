package com.jr.hittheman;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class result extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if (getSupportActionBar() != null) getSupportActionBar().hide();

        TextView tvCurrentScore = findViewById(R.id.tvCurrentScore);
        TextView tvHighScore = findViewById(R.id.tvBestScore);
        Button btnRestart = findViewById(R.id.btnRestart);
        Button btnExit = findViewById(R.id.btnExit);

        int finalScore = getIntent().getIntExtra("SCORE", 0);
        tvCurrentScore.setText("Score: " + finalScore);

        SharedPreferences prefs = getSharedPreferences("GamePrefs", MODE_PRIVATE);
        int highScore = prefs.getInt("highScore", 0);
        tvHighScore.setText("High Score: " + highScore);

        // RESTART: Smooth transition back to Game
        btnRestart.setOnClickListener(v -> {
            Intent intent = new Intent(result.this, GameActivity.class);
            startActivity(intent);
            // Built-in smooth fade transition
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        });

        // EXIT: Clear stack and return to Welcome Screen smoothly
        btnExit.setOnClickListener(v -> {
            Intent intent = new Intent(result.this, MainActivity.class);
            // These flags ensure you don't create multiple "Home" screens in the background
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            finish();
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Ensure even the hardware back button feels smooth
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
