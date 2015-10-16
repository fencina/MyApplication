package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.myapplication.utils.CircleProgressBar;

import java.util.ArrayList;
import java.util.Random;

public abstract class Juego {

    // jugadores
    // ganador
    // trampas
    // boosters
    public Activity juegoContext;
    public ArrayList<String> palabras = new ArrayList<>();
    public String palabraActual;
    public CountDownTimer timer;
    public CountDownTimer timerProgressBar;
    public int timePerWord; //en milisegundos
    public long tiempoTotalJuego; //en milisegundos
    public int palabrasEscritas = 0;
    public Chronometer cronometro;
    public int tipoJuego;

    // Elementos de la vista
    public TextView palabraText;
    public TextView relojText;
    public EditText tipeadoEditText;
    public ProgressBar circleProgressBar;

    public Juego(Activity juegoContext){
        this.juegoContext = juegoContext;
    }

    public void inicializar(){
        setTitle();
        setTimePerWord();
        setTipoJuego();
        cargarPalabras();
        crearElementosVista();
        crearTimer();
        crearProgressBarTimer();
        setCronometro();
    }

    public abstract void setTimePerWord();
    public abstract void setTipoJuego();
    public abstract void setTitle();
    public abstract void cargarPalabras();

    public void setCronometro(){
        cronometro = new Chronometer(juegoContext);
        cronometro.setBase(SystemClock.elapsedRealtime());
    }

    public void crearElementosVista(){
        relojText = (TextView) juegoContext.findViewById(R.id.reloj);
        palabraText = (TextView) juegoContext.findViewById(R.id.palabra);
        Typeface typeFace= Typeface.createFromAsset(juegoContext.getAssets(), "fonts/good_dog.otf");
        palabraText.setTypeface(typeFace);
        relojText.setTypeface(typeFace);

        tipeadoEditText = (EditText) juegoContext.findViewById(R.id.texto_tipeado);
    }

    public void crearTimer(){

        timer = new CountDownTimer(timePerWord, 1000) {

            public void onTick(long millisUntilFinished) {
                relojText.setText(""+ millisUntilFinished  / 1000 +"");
            }

            public void onFinish() {
                if ( quedanPalabras() ) {
                    setNextWord();
                    lanzarTimers();
                    tipeadoEditText.setText("");
                } else{
                    relojText.setText("Terminado!");
                    cronometro.stop();
                    tiempoTotalJuego = SystemClock.elapsedRealtime() - cronometro.getBase();
                    mostrarResultados();
                }

            }
        };
    }

    public void crearProgressBarTimer(){

        circleProgressBar = (ProgressBar) juegoContext.findViewById(R.id.circleProgressBar);
        circleProgressBar.setMax(timePerWord);

        timerProgressBar = new CountDownTimer(timePerWord, 50) {

            public void onTick(long millisUntilFinished) {
                circleProgressBar.setProgress((int) (timePerWord - millisUntilFinished));
            }

            public void onFinish() {

            }
        };
    }

    public void setNextWord(){
        if (quedanPalabras()){
            palabraActual = getRandomWord();
            palabras.remove(palabraActual);
            palabraText.setText(palabraActual);
        }
        else {
            pararTimers();
        }

    }

    public String getRandomWord(){
        Integer posicion = new Random().nextInt(palabras.size());
        String palabraRandom = palabras.get(posicion);

        return palabraRandom;
    }

    public void setListeners(){

        tipeadoEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    lanzarTimers();
                    cronometro.start();
                }
            }
        });

        tipeadoEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence tipeadoHastaAhora, int start, int before, int count) {

                colorearPalabra(tipeadoHastaAhora);

                if (tipeoCorrectamente(tipeadoHastaAhora) && quedanPalabras()) {
                    palabrasEscritas++;
                    setNextWord();
                    lanzarTimers();
                    tipeadoEditText.setText("");
                } else if (tipeoCorrectamente(tipeadoHastaAhora) && !quedanPalabras()) {
                    palabrasEscritas++;
                    pararTimers();
                    relojText.setText("Ganaste!");
                    cronometro.stop();
                    tiempoTotalJuego = SystemClock.elapsedRealtime() - cronometro.getBase();
                    mostrarResultados();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void lanzarTimers(){
        timer.start();
        timerProgressBar.start();
    }

    public void pararTimers(){
        timer.cancel();
        timerProgressBar.cancel();
    }

    private void colorearPalabra(CharSequence tipeadoHastaAhora){

        if (estaTipeandoCorrectamente(tipeadoHastaAhora)){
            colorearVerde(tipeadoHastaAhora);
        }
        else{
            colorearRojo(tipeadoHastaAhora);
        }

    }

    public void colorear(CharSequence tipeadoHastaAhora, ForegroundColorSpan color){
        SpannableString palabraColoreada = new SpannableString(palabraActual);

        if (tipeadoHastaAhora.length() > palabraColoreada.length())
            palabraColoreada.setSpan(color, 0, palabraColoreada.length(),Spannable.SPAN_INTERMEDIATE );
        else
            palabraColoreada.setSpan(color, 0, tipeadoHastaAhora.length(),Spannable.SPAN_INTERMEDIATE );

        palabraText.setText(palabraColoreada);
    }

    public void colorearVerde(CharSequence tipeadoHastaAhora){
        colorear(tipeadoHastaAhora, new ForegroundColorSpan(Color.parseColor("#9CCC65")));
    }

    public void colorearRojo(CharSequence tipeadoHastaAhora){

        colorear(tipeadoHastaAhora, new ForegroundColorSpan(Color.RED));

        Vibrator vibrator = (Vibrator) juegoContext.getSystemService(Context.VIBRATOR_SERVICE);
        //start vibration with repeated count, use -1 if you don't want to repeat the vibration

        if (!"".equals(tipeadoHastaAhora.toString()))
            vibrator.vibrate(50);

    }

    public boolean estaTipeandoCorrectamente(CharSequence tipeado){

        if (tipeado.length() > palabraActual.length() )
            return false;

        return tipeado.toString().equals(palabraActual.substring(0,tipeado.length() ));
    }

    public boolean tipeoCorrectamente(CharSequence tipeado){
        return tipeado.toString().equals(palabraActual);
    }

    public boolean quedanPalabras(){
        return palabras.size() > 0;
    }

    public void mostrarResultados(){
        Intent intentResultados = new Intent(juegoContext,ResultadosActivity.class);
        intentResultados.putExtra("palabrasEscritas",palabrasEscritas);
        intentResultados.putExtra("tiempoTotal",tiempoTotalJuego);
        intentResultados.putExtra("tipoJuego", tipoJuego);
        juegoContext.startActivity(intentResultados);
    }
}
