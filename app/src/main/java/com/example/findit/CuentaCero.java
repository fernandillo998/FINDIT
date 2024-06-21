package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class CuentaCero extends AppCompatActivity {
    private boolean dificil;
    MusicManager musicManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_cero);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        musicManager = MusicManager.getInstance(this);

        musicManager.stopMusic();

        musicManager.startMusic(this,R.raw.perdiste);

        dificil = getIntent().getBooleanExtra("dificil", dificil);
    }

    public void goInicio(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goCuentaAtras(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent intent = new Intent(this, CuentaAtras.class);
        intent.putExtra("dificil", dificil);
        startActivity(intent);
    }
}