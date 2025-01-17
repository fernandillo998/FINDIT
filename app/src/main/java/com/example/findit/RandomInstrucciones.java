package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;

public class RandomInstrucciones extends AppCompatActivity {

    boolean dificil, randomFlash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_instrucciones);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dificil = false;
        randomFlash = true;
    }

    public void goRandomLevel(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent randomScreen = new Intent(this, CuentaAtras.class);
        randomScreen.putExtra("dificil", dificil);
        randomScreen.putExtra("randomFlash", randomFlash);
        startActivity(randomScreen);

    }
}