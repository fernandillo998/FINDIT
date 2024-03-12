package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RandomFacilDificil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_facil_dificil);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    public void goRandomNormal(View view){
        Intent intent = new Intent(this, RandomInstrucciones.class);
        startActivity(intent);
    }

    public void goRandomDificil(View view){
        Intent intent = new Intent(this, RandomDificilInstrucciones.class);
        startActivity(intent);
    }

    public void goInfinito(View view){
        Intent intent = new Intent(this, RandomInfinitoInstrucciones.class);
        startActivity(intent);
    }
}