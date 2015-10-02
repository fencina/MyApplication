package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
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
    public static CircleProgressBar circleProgressBar;
    public CountDownTimer timer;
    public TextView palabraText;
    public TextView relojText;
    public EditText tipeadoEditText;
    public int time; //en milisegundos

    public Juego(Activity juegoContext){
        this.juegoContext = juegoContext;
    }

    public void inicializar(){
        setTitle();
        setTime();
        cargarPalabras();
        crearElementosVista();
        crearTimer();
    }

    public abstract void setTime();
    public abstract void setTitle();
    public abstract void cargarPalabras();

    public void crearElementosVista(){
        relojText = (TextView) juegoContext.findViewById(R.id.reloj);
        palabraText = (TextView) juegoContext.findViewById(R.id.palabra);
        tipeadoEditText = (EditText) juegoContext.findViewById(R.id.texto_tipeado);
    }

    public void crearTimer(){

        circleProgressBar = (CircleProgressBar) juegoContext.findViewById(R.id.custom_progressBar);
        circleProgressBar.setMax(time);

        timer = new CountDownTimer(time, 1000) {

            public void onTick(long millisUntilFinished) {
                relojText.setText("Quedan " + millisUntilFinished / 1000 + " segundos");

//                circleProgressBar.setProgress(millisUntilFinished / 1000);
                circleProgressBar.setProgressWithAnimation(time - millisUntilFinished);
            }

            public void onFinish() {
                if ( quedanPalabras() ) {
                    setNextWord();
                    this.start();
                    tipeadoEditText.setText("");
                } else{
                    relojText.setText("Terminado!");
                }

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
            timer.cancel();
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
                    timer.start();
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
                    setNextWord();
                    timer.start();
                    tipeadoEditText.setText("");
                } else if (tipeoCorrectamente(tipeadoHastaAhora) && !quedanPalabras()) {
                    timer.cancel();
                    relojText.setText("Ganaste!");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
}
