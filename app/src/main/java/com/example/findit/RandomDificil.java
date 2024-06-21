package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomDificil extends AppCompatActivity {

    ImageButton barco;
    ImageButton cubo;
    ImageButton elefante;
    ImageButton flor;
    ImageButton globo;
    ImageButton helicoptro;
    ImageButton piramide;
    ImageButton robot;
    ImageButton tambor;
    boolean barcoBool;
    boolean cuboBool;
    boolean elefanteBool;
    boolean florBool;
    boolean globoBool;
    boolean helicoptroBool;
    boolean piramideBool;
    boolean robotBool;
    boolean tamborBool;
    boolean dificil = true;

    List<ImageButton> juguetes;
    Animation animation;

    ImageView numerin;
    ImageView numerinNiveles;

    CountDownTimer timer;
    private int cont=9;
    private int niveles = 0;

    MusicManager musicManager;

    ConstraintLayout constraintLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_dificil);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        musicManager = MusicManager.getInstance(this);

        musicManager.stopMusic();

        musicManager.startMusic(this,R.raw.aleatorio);

        constraintLayout = findViewById(R.id.layoutDificil);

        barco = findViewById(R.id.barco);
        cubo = findViewById(R.id.cubo);
        elefante = findViewById(R.id.elefante);
        flor = findViewById(R.id.flor);
        globo = findViewById(R.id.globo);
        helicoptro = findViewById(R.id.helicoptro);
        piramide = findViewById(R.id.piramide);
        robot = findViewById(R.id.robot);
        tambor = findViewById(R.id.tambor);

        barcoBool = cuboBool = elefanteBool = florBool = globoBool = helicoptroBool = piramideBool = robotBool = tamborBool = false;

        numerin = findViewById(R.id.contador);
        numerinNiveles = findViewById(R.id.nivelesSuperados);


        juguetes = new ArrayList<>();

        juguetes.add(barco);
        juguetes.add(cubo);
        juguetes.add(elefante);
        juguetes.add(flor);
        juguetes.add(globo);
        juguetes.add(helicoptro);
        juguetes.add(piramide);
        juguetes.add(robot);
        juguetes.add(tambor);

        randomizarTamaños(juguetes);
        randomizarPosicion(juguetes);


        timer = new CountDownTimer(9000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counterTime(cont, numerin);
                cont = cont-1;

                if(barcoBool && cuboBool && elefanteBool && florBool && globoBool && helicoptroBool && piramideBool && robotBool && tamborBool){
                    niveles++;
                    cambiarFondo(constraintLayout);
                    cambiarNumerinNiveles(niveles, numerinNiveles);
                    barcoBool = cuboBool = elefanteBool = florBool = globoBool = helicoptroBool = piramideBool = robotBool = tamborBool = false;
                    randomizarTamaños(juguetes);
                    randomizarPosicion(juguetes);
                    aparecerJuguete(juguetes);
                }


            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(RandomDificil.this, RandomCuentaCero.class);
                intent.putExtra("dificil", dificil);
                intent.putExtra("niveles", niveles);
                startActivity(intent);


            }
        };

        timer.start();
    }

    public void randomizarPosicion(List<ImageButton> peces){
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        for(ImageButton pez : peces){
            int pezWidth = pez.getMeasuredWidth();
            int pezHeight = pez.getMeasuredHeight();


            int dx = (int) (Math.random()*(displaymetrics.widthPixels- pezWidth));
            int dy = (int) (Math.random()*(displaymetrics.heightPixels- pezHeight));

            if (dx > displaymetrics.widthPixels - pezWidth){
                dx = dx - pezWidth;
            }
            if (dy > displaymetrics.heightPixels - pezHeight){
                dy = dy - pezHeight;
            }

            dx = 8*dx/10;
            dy = 8*dy/10;

            pez.setX(dx);
            pez.setY(dy);
        }


    }

    public void aparecerJuguete(List<ImageButton> juguetes){
        for (ImageButton juguete:juguetes) {
            juguete.setVisibility(View.VISIBLE);
        }
    }

    public void alTocarBarco(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        barcoBool = true;
    }
    public void alTocarCubo(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        cuboBool = true;
    }
    public void alTocarFlor(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        florBool = true;
    }
    public void alTocarElefante(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        elefanteBool = true;
    }
    public void alTocarGlobo(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        globoBool = true;
    }
    public void alTocarHelicoptro(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        helicoptroBool = true;
    }
    public void alTocarPiramide(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        piramideBool = true;
    }
    public void alTocarRobot(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        robotBool = true;
    }
    public void alTocarTambor(View juguete){
        MusicManager.getInstance(getApplicationContext()).playButtonSound(R.raw.wood);
        juguete.setVisibility(View.GONE);
        tamborBool = true;
    }

    public void counterTime(int cont, ImageView numerin){
        switch (cont){
            case 8:
                numerin.setImageResource(R.drawable.ochocho);
                break;

            case 7:
                numerin.setImageResource(R.drawable.sietesiete);
                break;

            case 6:
                numerin.setImageResource(R.drawable.seiseis);
                break;

            case 5:
                numerin.setImageResource(R.drawable.cincocinco);
                break;
            case 4:
                numerin.setImageResource(R.drawable.cuatrocuatro);
                break;

            case 3:
                numerin.setImageResource(R.drawable.trestres);
                break;

            case 2:
                numerin.setImageResource(R.drawable.dosdos);
                break;

            case 1:
                numerin.setImageResource(R.drawable.unouno);
                break;


        }
    }

    public void cambiarNumerinNiveles(int numerin, ImageView numerinNiveles){
        switch (numerin){
            case 0:
                numerinNiveles.setImageResource(R.drawable.cero);
                break;
            case 1:
                numerinNiveles.setImageResource(R.drawable.uno);
                break;
            case 2:
                numerinNiveles.setImageResource(R.drawable.dos);
                break;
            case 3:
                numerinNiveles.setImageResource(R.drawable.tres);
                break;
            case 4:
                numerinNiveles.setImageResource(R.drawable.cuatro);
                break;
            case 5:
                numerinNiveles.setImageResource(R.drawable.cinco);
                break;
            case 6:
                numerinNiveles.setImageResource(R.drawable.seis);
                break;
            case 7:
                numerinNiveles.setImageResource(R.drawable.siete);
                break;
            case 8:
                numerinNiveles.setImageResource(R.drawable.ocho);
                break;
            case 9:
                numerinNiveles.setImageResource(R.drawable.nueve);
                break;
            case 10:
                numerinNiveles.setImageResource(R.drawable.icon);
                break;
        }
    }

    public void randomizarTamaños(List<ImageButton> peces){
        for (ImageButton pez:peces) {
            ViewGroup.LayoutParams params = pez.getLayoutParams();
            params.width = 150 + (int) (Math.random() * 100) ;
            params.height = 150 + (int) (Math.random() * 100) ;
            pez.setLayoutParams(params);
        }

    }

    public void cambiarFondo(ConstraintLayout constraintLayout){

        String[] drawablesList = {
                "avermovil", "aver2movil", "aver3movil", "aver4movil"
        };

        int randomIndex = new Random().nextInt(drawablesList.length);

        String selectedDrawable = drawablesList[randomIndex];

        int resourceId = getResources().getIdentifier(selectedDrawable, "drawable", getPackageName());

        constraintLayout.setBackgroundResource(resourceId);

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}