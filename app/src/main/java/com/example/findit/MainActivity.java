package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.midnightride);
        mediaPlayer.start();
    }

    public void goRandom(View view){
        Intent randomScreen = new Intent(this, RandomFacilDificil.class);
        startActivity(randomScreen);
    }

    public void goFlash(View view){
        Intent flashScreen = new Intent(this, FlashFacilDificil.class);
        startActivity(flashScreen);
    }
}