package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class RandomCuentaCero extends AppCompatActivity {

    private boolean dificil;
    private boolean randomFlash;
    private int niveles;
    TextView nivelesTexto;

    MusicManager musicManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_cuenta_cero);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        musicManager = MusicManager.getInstance(this);

        musicManager.stopMusic();

        musicManager.startMusic(this,R.raw.enhorabuenabien);

        randomFlash = true;

        nivelesTexto = findViewById(R.id.textView);

        dificil = getIntent().getBooleanExtra("dificil", dificil);
        niveles = getIntent().getIntExtra("niveles", niveles);

        if(niveles == 0){
            nivelesTexto.setText("Se acabó el tiempo!");
        }
        else {
            nivelesTexto.setText("Conseguiste superar " + niveles + " niveles! Quieres volver a intentarlo?");
        }

    }

    public void goInicio(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goCuentaAtrasRandom(View view){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.buttonbien);
        Intent intent = new Intent(this, CuentaAtras.class);
        intent.putExtra("dificil", dificil);
        intent.putExtra("randomFlash", randomFlash);
        startActivity(intent);
    }
}