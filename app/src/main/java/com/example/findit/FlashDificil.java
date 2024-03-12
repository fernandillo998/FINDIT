package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class FlashDificil extends AppCompatActivity {
    private boolean dificil;

    ImageButton amaNar;
    ImageButton ballena;
    ImageButton blanquimorao;
    ImageButton callabo;
    ImageButton dori;
    ImageButton estrella;
    ImageButton moraoPez;
    ImageButton payaso;
    ImageButton pezralla;

    Animation animation;

    ImageView numerin;
    CountDownTimer timer;
    private int cont=12;

    private boolean ama;
    private boolean ball;
    private boolean blanq;
    private boolean call;
    private boolean dor;
    private boolean estrellaBool;
    private boolean moraoBool;
    private boolean payasoBool;
    private boolean pezrallaBool;
    private long startTime;

    private long elapsedTime;

    public long getElapsedTime() {
        return elapsedTime;
    }
    List<ImageButton> botones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_dificil);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        dificil = true;

        amaNar = findViewById(R.id.pezAmarilloNaranja);
        ballena = findViewById(R.id.ballenaButton);
        blanquimorao = findViewById(R.id.blanquimoraoButton);
        callabo = findViewById(R.id.callaboButton);
        dori = findViewById(R.id.doriButton);
        estrella = findViewById(R.id.estrella);
        moraoPez = findViewById(R.id.morao);
        payaso = findViewById(R.id.payaso);
        pezralla = findViewById(R.id.pezralla);

        animation = AnimationUtils.loadAnimation(this, R.anim.blink);




        botones = new ArrayList<>();

        ama = false;
        ball = false;
        blanq = false;
        call = false;
        dor = false;
        estrellaBool = false;
        moraoBool = false;
        payasoBool = false;
        pezrallaBool = false;

        botones.add(pezralla);
        botones.add(amaNar);
        botones.add(payaso);
        botones.add(ballena);
        botones.add(blanquimorao);
        botones.add(moraoPez);
        botones.add(estrella);
        botones.add(callabo);
        botones.add(dori);


        randomizarPosicion(botones);
        randomizarTamaños(botones);



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
                        if (!estrellaBool){
                            estrella.animate().alpha(1).setDuration(0).start();
                            estrella.startAnimation(animation);
                        }
                        if (!moraoBool){
                            moraoPez.animate().alpha(1).setDuration(0).start();
                            moraoPez.startAnimation(animation);
                        }
                        if (!payasoBool){
                            payaso.animate().alpha(1).setDuration(0).start();
                            payaso.startAnimation(animation);
                        }
                        if (!pezrallaBool){
                            pezralla.animate().alpha(1).setDuration(0).start();
                            pezralla.startAnimation(animation);
                        }
                        break;
                }
                cont = cont-1;
                if(ama && ball && blanq && call && dor && estrellaBool && payasoBool && moraoBool && pezrallaBool){
                    timer.cancel();

                    elapsedTime = System.currentTimeMillis() - startTime;
                    Intent intent = new Intent(FlashDificil.this, FlashEnhorabuena.class);
                    intent.putExtra("elapsedTime", elapsedTime);
                    intent.putExtra("dificil", dificil);
                    startActivity(intent);
                }
            }
            @Override
            public void onFinish() {
                Intent intent = new Intent(FlashDificil.this, CuentaCero.class);
                intent.putExtra("dificil", dificil);
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

    public void alTocarEstrella(View pez){
        //        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        estrellaBool = true;
    }

    public void alTocarMorao(View pez){
        //        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        moraoBool = true;
    }

    public void alTocarPayaso(View pez){
        //        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        payasoBool = true;
    }

    public void alTocarPezralla(View pez){
        //        pez.animate().alpha(1).setDuration(0).start();
//        pez.setEnabled(false);
        pez.setVisibility(View.GONE);
        pezrallaBool = true;
    }

    public void randomizarTamaños(List<ImageButton> peces){
        for (ImageButton pez:peces) {
            ViewGroup.LayoutParams params = pez.getLayoutParams();
            params.width = 100 + (int) (Math.random() * 150) ;
            params.height = 100 + (int) (Math.random() * 150) ;
            pez.setLayoutParams(params);
        }

    }




    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}