package com.jr.hittheman;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    TextView tvScore, tvStrikes;
    ImageView[] holes = new ImageView[9];
    ImageView ivHammer;

    int score = 0;
    int strikes = 7;
    boolean isHit = false;
    int lastHole = -1;

    Handler handler = new Handler();
    Runnable runnable;
    Random random = new Random();
    SoundPool soundPool;
    int hitSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // Hide Title Bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        tvScore = findViewById(R.id.tvScore);
        tvStrikes = findViewById(R.id.tvStrikes);
        ivHammer = findViewById(R.id.ivHammer);

        // Sound Setup
        AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setMaxStreams(5).setAudioAttributes(attrs).build();
        hitSoundId = soundPool.load(this, R.raw.hit_sound, 1);

        // Initialize Holes
        for (int i = 0; i < 9; i++) {
            int resID = getResources().getIdentifier("iv" + i, "id", getPackageName());
            holes[i] = findViewById(resID);
            holes[i].setOnClickListener(v -> handleHit((ImageView) v));
        }

        startGame();
    }
