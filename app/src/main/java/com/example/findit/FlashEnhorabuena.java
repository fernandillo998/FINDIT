package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FlashEnhorabuena extends AppCompatActivity {

    private long tiempoTranscurridoMillis;
    private float tiempoTranscurridoSeg;
    TextView tiempoText;

    boolean dificil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enhorabuena_flash);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        tiempoTranscurridoMillis = getIntent().getLongExtra("elapsedTime", 0);
        dificil = getIntent().getBooleanExtra("dificil", false);
        tiempoTranscurridoSeg = tiempoTranscurridoMillis/1000;
        tiempoText = findViewById(R.id.tiempoTextView);

        tiempoText.setText("Lo has logrado en " + tiempoTranscurridoSeg + " segundos!");


    }

    public void goInicio(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goCuentaAtras(View view){
        Intent intent = new Intent(this, CuentaAtras.class);
        intent.putExtra("dificil", dificil);
        startActivity(intent);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}