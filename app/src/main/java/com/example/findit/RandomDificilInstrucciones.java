package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RandomDificilInstrucciones extends AppCompatActivity {

    boolean dificil, randomFlash;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_dificil_instrucciones);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dificil = true;
        randomFlash = true;
    }

    public void goRandomDificilLevel(View view){
        Intent flashScreen = new Intent(this, CuentaAtras.class);
        flashScreen.putExtra("dificil", dificil);
        flashScreen.putExtra("randomFlash", randomFlash);
        startActivity(flashScreen);
    }
}