package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class FlashFacilDificil extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_facil_dificil);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void goFlashNormal(View view){
        Intent intent = new Intent(this, FlashInstrucciones.class);
        startActivity(intent);
    }

    public void goFlashDificil(View view){
        Intent intent = new Intent(this, FlashDificilInstrucciones.class);
        startActivity(intent);
    }
}