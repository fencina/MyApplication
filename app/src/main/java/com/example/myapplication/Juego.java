package com.example.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        timer = new CountDownTimer(time, 1000) {


            public void onTick(long millisUntilFinished) {
                relojText.setText("Quedan " + millisUntilFinished / 1000 + " segundos");
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

    public boolean tipeoCorrectamente(CharSequence tipeado){
        return tipeado.toString().equals(palabraActual);
    }

    public boolean quedanPalabras(){
        return palabras.size() > 0;
    }
}
