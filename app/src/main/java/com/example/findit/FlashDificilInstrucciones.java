package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FlashDificilInstrucciones extends AppCompatActivity {

    boolean dificil, randomFlash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instrucciones_flash_dificil);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dificil = true;
        randomFlash = false;

    }
    public void goFlashLevel(View view){
        Intent flashScreen = new Intent(this, CuentaAtras.class);
        flashScreen.putExtra("dificil", dificil);
        flashScreen.putExtra("randomFlash", randomFlash);
        startActivity(flashScreen);
    }
}