package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RandomInfinitoInstrucciones extends AppCompatActivity {

    boolean infinito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_infinito_instrucciones);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        infinito = true;
    }

    public void goInfinitoLevel(View view){
        Intent intent = new Intent(this, CuentaAtras.class);
        intent.putExtra("infinito", infinito);
        startActivity(intent);
    }
}