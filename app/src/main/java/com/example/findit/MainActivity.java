package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        musicManager = MusicManager.getInstance(this);

        int currentSongResourceId = musicManager.getCurrentSongResourceId();

        int mainActivitySongResourceId = R.raw.inicio;

        if (currentSongResourceId == 0 || currentSongResourceId != mainActivitySongResourceId) {
            musicManager.stopMusic();
            musicManager.startMusic(this, mainActivitySongResourceId);
        }



    }

    public void goRandom(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent randomScreen = new Intent(this, RandomFacilDificil.class);
        startActivity(randomScreen);
    }

    public void goFlash(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent flashScreen = new Intent(this, FlashFacilDificil.class);
        startActivity(flashScreen);
    }

    public void stopMusic(View view) {
        musicManager.stopMusic();
    }

}