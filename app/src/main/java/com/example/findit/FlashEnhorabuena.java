package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FlashEnhorabuena extends AppCompatActivity {

    private long tiempoTranscurridoMillis;
    private float tiempoTranscurridoSeg;
    TextView tiempoText;

    boolean dificil;

    MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enhorabuena_flash);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        musicManager = MusicManager.getInstance(this);

        musicManager.stopMusic();

        musicManager.startMusic(this,R.raw.enhorabuenabien);

        tiempoTranscurridoMillis = getIntent().getLongExtra("elapsedTime", 0);
        dificil = getIntent().getBooleanExtra("dificil", false);
        tiempoTranscurridoSeg = tiempoTranscurridoMillis/1000;
        tiempoText = findViewById(R.id.tiempoTextView);

        tiempoText.setText("Lo has logrado en " + tiempoTranscurridoSeg + " segundos!");


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

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}