package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CuentaAtras extends AppCompatActivity {

    ImageView numerin;
    CountDownTimer timer;
    int cont=3;

    boolean dificil, randomFlash, infinito;
    MusicManager musicManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta_atras);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        musicManager = MusicManager.getInstance(this);

        musicManager.stopMusic();

        musicManager.startMusic(this,R.raw.tictac3);

        infinito = getIntent().getBooleanExtra("infinito", infinito);

        dificil = getIntent().getBooleanExtra("dificil", dificil);

        randomFlash = getIntent().getBooleanExtra("randomFlash", randomFlash);

        numerin = findViewById(R.id.numero);


        timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch (cont){
                    case 4:
                        numerin.setImageResource(R.drawable.cuatro);
                        break;

                    case 3:
                        numerin.setImageResource(R.drawable.tres);
                        break;

                    case 2:
                        numerin.setImageResource(R.drawable.dos);
                        break;

                    case 1:
                        numerin.setImageResource(R.drawable.uno);
                        break;
                }
                cont = cont-1;
            }
            @Override
            public void onFinish() {
                if (infinito == true){
                    Intent intent = new Intent(CuentaAtras.this, RandomInfinito.class);
                    startActivity(intent);
                }
                else{
                    if(randomFlash == true && dificil == false){
                        Intent intent = new Intent(CuentaAtras.this, RandomLevel.class);
                        startActivity(intent);
                    }

                    if(randomFlash == true && dificil == true){
                        Intent intent = new Intent(CuentaAtras.this, RandomDificil.class);
                        startActivity(intent);
                    }

                    if(randomFlash == false && dificil == false){
                        Intent intent = new Intent(CuentaAtras.this, FlashLevel.class);
                        startActivity(intent);
                    }
                    if(randomFlash == false && dificil == true){
                        Intent dificilFlash = new Intent(CuentaAtras.this, FlashDificil.class);
                        startActivity(dificilFlash);
                    }

                }




            }
        };

        timer.start();
    }




}