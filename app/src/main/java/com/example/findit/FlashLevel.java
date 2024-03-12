package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FlashLevel extends AppCompatActivity {

    ImageButton amaNar;
    ImageButton ballena;
    ImageButton blanquimorao;
    ImageButton callabo;
    ImageButton dori;

    Animation animation;

    ImageView numerin;
    CountDownTimer timer;
    private int cont=12;

    private boolean ama;
    private boolean ball;
    private boolean blanq;
    private boolean call;
    private boolean dor;

    private long startTime;

    private long elapsedTime;

    public long getElapsedTime() {
        return elapsedTime;
    }
    List<ImageButton> botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_level);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        amaNar = findViewById(R.id.pezAmarilloNaranja);
        ballena = findViewById(R.id.ballenaButton);
        blanquimorao = findViewById(R.id.blanquimoraoButton);
        callabo = findViewById(R.id.callaboButton);
        dori = findViewById(R.id.doriButton);

        animation = AnimationUtils.loadAnimation(this, R.anim.blink);

        botones = new ArrayList<>();

        ama = false;
        ball = false;
        blanq = false;
        call = false;
        dor = false;

        botones.add(amaNar);
        botones.add(ballena);
        botones.add(blanquimorao);
        botones.add(callabo);
        botones.add(dori);


        randomizarPosicion(botones);


        for (ImageButton pez:botones) {
            desaparecerPez(pez);
        }

        numerin = findViewById(R.id.contador);


        timer = new CountDownTimer(12000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                switch (cont){
                    case 11:
                        numerin.setImageResource(R.drawable.ochocho);
                        break;

                    case 10:
                        numerin.setImageResource(R.drawable.sietesiete);
                        break;

                    case 9:
                        numerin.setImageResource(R.drawable.seiseis);
                        break;

                    case 8:
                        numerin.setImageResource(R.drawable.cincocinco);
                        break;
                    case 7:
                        numerin.setImageResource(R.drawable.cuatrocuatro);
                        break;

                    case 6:
                        numerin.setImageResource(R.drawable.trestres);
                        break;

                    case 5:
                        numerin.setImageResource(R.drawable.dosdos);
                        break;

                    case 4:
                        numerin.setImageResource(R.drawable.unouno);
                        break;

                    case 3:
                    case 2:
                    case 1:

                        numerin.setVisibility(View.INVISIBLE);

                        for (ImageButton boton:botones) {
                            boton.setEnabled(false);
                        }

                        if (!ama){
                            amaNar.animate().alpha(1).setDuration(0).start();
                            amaNar.startAnimation(animation);
                        }
                        if (!ball){
                            ballena.animate().alpha(1).setDuration(0).start();
                            ballena.startAnimation(animation);
                        }
                        if (!blanq){
                            blanquimorao.animate().alpha(1).setDuration(0).start();
                            blanquimorao.startAnimation(animation);
                        }
                        if (!call){
                            callabo.animate().alpha(1).setDuration(0).start();
                            callabo.startAnimation(animation);
                        }
                        if (!dor){
                            dori.animate().alpha(1).setDuration(0).start();
                            dori.startAnimation(animation);
                        }
                        break;
                }
                cont = cont-1;
                if(ama && ball && blanq && call && dor){
                    timer.cancel();
                    elapsedTime = System.currentTimeMillis() - startTime;
                    Intent intent = new Intent(FlashLevel.this, FlashEnhorabuena.class);
                    intent.putExtra("elapsedTime", elapsedTime);
                    startActivity(intent);
                }
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(FlashLevel.this, CuentaCero.class);
                startActivity(intent);
            }
        };

        timer.start();
        startTime = System.currentTimeMillis();



    }

    public void randomizarPosicion(List<ImageButton> peces){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        for(ImageButton pez : peces){
            pez.setPadding(0,0,0,0);
            pez.setBackground(null);
            int pezWidth = pez.getMeasuredWidth();
            int pezHeight = pez.getMeasuredHeight();


            int dx = (int) (Math.random()*(displaymetrics.widthPixels- pezWidth));
            int dy = (int) (Math.random()*(displaymetrics.heightPixels- pezHeight));

            dx = 3*dx/4;
            dy = 3*dy/4;

            pez.setX(dx);
            pez.setY(dy);
        }


    }

    public void desaparecerPez(ImageButton pez){
        pez.animate().alpha(0).setDuration(10000).start();

    }

    public void alTocarAmarillo(View pez){
//        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        ama = true;
    }

    public void alTocarBallena(View pez){
//        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);

        ball = true;
    }

    public void alTocarBlanquimorao(View pez){
//        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        blanq = true;
    }

    public void alTocarCallabo(View pez){
//        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        call = true;
    }

    public void alTocarDori(View pez){
//        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        dor = true;
    }




    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }


}