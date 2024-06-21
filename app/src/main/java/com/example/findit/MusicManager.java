package com.example.findit;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class MusicManager {
    private static MusicManager instance;
    private MediaPlayer mediaPlayer;
    private boolean isMusicPlaying = false;
    private int currentSongResourceId = 0;
    private SharedPreferences sharedPreferences;
    private Context context;

    private MusicManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences("music_pref", Context.MODE_PRIVATE);
    }

    public int getCurrentSongResourceId() {
        return currentSongResourceId;
    }

    public static synchronized MusicManager getInstance(Context context) {
        if (instance == null) {
            instance = new MusicManager(context.getApplicationContext());
        }
        return instance;
    }

    public void startMusic(Context context, int musicResourceId) {
        if (!isMusicPlaying) {
            mediaPlayer = MediaPlayer.create(context, musicResourceId);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();
            isMusicPlaying = true;
            currentSongResourceId = musicResourceId;
            // Guardar el estado de la música en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isMusicPlaying", isMusicPlaying);
            editor.apply();
        }
    }

    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
            isMusicPlaying = false;
            // Actualizar el estado de la música en SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isMusicPlaying", isMusicPlaying);
            editor.apply();
        }
    }

    public void playButtonSound(int soundResourceId) {
        MediaPlayer buttonSound = MediaPlayer.create(context, soundResourceId);
        buttonSound.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
        buttonSound.start();
    }


}

