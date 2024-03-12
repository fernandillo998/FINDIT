package com.example.findit;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class RandomInfinito extends AppCompatActivity {

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
    ImageView numerinNiveles;
    ImageView decenasNumerinNiveles;

    CountDownTimer timer;
    private int niveles = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_infinito);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

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

        numerinNiveles = findViewById(R.id.nivelesSuperados);
        decenasNumerinNiveles = findViewById(R.id.nivelesSuperadosDecenas);
        decenasNumerinNiveles.setVisibility(View.GONE);


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

        timer = new CountDownTimer(99999000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {


                if(barcoBool && cuboBool && elefanteBool && florBool && globoBool && helicoptroBool && piramideBool && robotBool && tamborBool){
                    niveles++;
                    cambiarNumerinNiveles(niveles, numerinNiveles, decenasNumerinNiveles);
                    barcoBool = cuboBool = elefanteBool = florBool = globoBool = helicoptroBool = piramideBool = robotBool = tamborBool = false;
                    randomizarTamaños(juguetes);
                    randomizarPosicion(juguetes);
                    cambiarImagen(juguetes);
                    aparecerJuguete(juguetes);
                }
                if (niveles > 99){
                    timer.cancel();
                    Intent intent = new Intent(RandomInfinito.this, RandomCuentaCero.class);
                    intent.putExtra("dificil", dificil);
                    intent.putExtra("niveles", niveles);
                    startActivity(intent);
                }


            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(RandomInfinito.this, RandomCuentaCero.class);
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
        juguete.setVisibility(View.GONE);
        barcoBool = true;
    }
    public void alTocarCubo(View juguete){
        juguete.setVisibility(View.GONE);
        cuboBool = true;
    }
    public void alTocarFlor(View juguete){
        juguete.setVisibility(View.GONE);
        florBool = true;
    }
    public void alTocarElefante(View juguete){
        juguete.setVisibility(View.GONE);
        elefanteBool = true;
    }
    public void alTocarGlobo(View juguete){
        juguete.setVisibility(View.GONE);
        globoBool = true;
    }
    public void alTocarHelicoptro(View juguete){
        juguete.setVisibility(View.GONE);
        helicoptroBool = true;
    }
    public void alTocarPiramide(View juguete){
        juguete.setVisibility(View.GONE);
        piramideBool = true;
    }
    public void alTocarRobot(View juguete){
        juguete.setVisibility(View.GONE);
        robotBool = true;
    }
    public void alTocarTambor(View juguete){
        juguete.setVisibility(View.GONE);
        tamborBool = true;
    }


    public void cambiarNumerinNiveles(int numerin, ImageView numerinNiveles, ImageView decenasNumerinNiveles){
        if (numerin > 9){
            decenasNumerinNiveles.setVisibility(View.VISIBLE);
        }
        int decenas = numerin/10;
        int unidades = numerin%10;

//        Toast toast = Toast.makeText(this, "Decena: " + decenas + " Unidad: " + unidades, Toast.LENGTH_SHORT);
//        toast.show();

        switch (unidades){
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

        }
        switch (decenas){
            case 0:
                decenasNumerinNiveles.setImageResource(R.drawable.cero);
                break;
            case 1:
                decenasNumerinNiveles.setImageResource(R.drawable.uno);
                break;
            case 2:
                decenasNumerinNiveles.setImageResource(R.drawable.dos);
                break;
            case 3:
                decenasNumerinNiveles.setImageResource(R.drawable.tres);
                break;
            case 4:
                decenasNumerinNiveles.setImageResource(R.drawable.cuatro);
                break;
            case 5:
                decenasNumerinNiveles.setImageResource(R.drawable.cinco);
                break;
            case 6:
                decenasNumerinNiveles.setImageResource(R.drawable.seis);
                break;
            case 7:
                decenasNumerinNiveles.setImageResource(R.drawable.siete);
                break;
            case 8:
                decenasNumerinNiveles.setImageResource(R.drawable.ocho);
                break;
            case 9:
                decenasNumerinNiveles.setImageResource(R.drawable.nueve);
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

    public void goInicio(View view){
        Intent flashScreen = new Intent(this, MainActivity.class);
        startActivity(flashScreen);
    }

    public void cambiarImagen(List<ImageButton> peces){

        String[] drawablesList = {
                "amarillonaranja", "ballena", "blanquimorao", "calalbo", "cero",
                "cerocero", "cinco", "cincocinco", "cuatro",
                "cuatrocuatro", "dori", "dos", "dosdos", "estrellazul",
                  "moraopez", "naranja",
                "nueve", "nuevenueve", "ocho", "ochocho", "paul", "payaso",
                "pezazul", "pezrallas", "seis", "seiseis", "siete", "sietesiete",
                "tres", "trestres", "uno", "unouno", "zzbarco", "zzcubo",
                "zzelefante", "zzflor",  "zzglobo", "zzhelicoptro",
                "zzpiramide", "zzrobot", "zztambor"
        };

        for (ImageButton pez:peces) {
            int randomIndex = new Random().nextInt(drawablesList.length);

            String selectedDrawable = drawablesList[randomIndex];

            int resourceId = getResources().getIdentifier(selectedDrawable, "drawable", getPackageName());

            pez.setImageResource(resourceId);
        }
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {

    }
}
