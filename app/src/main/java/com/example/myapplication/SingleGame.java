package com.example.myapplication;

import android.app.Activity;

public class SingleGame extends Juego {

    public SingleGame(Activity juegoContext){
        super(juegoContext);
    }

    public void setTime(){
        this.time = 10000;
    }


    public void setTitle(){
        this.juegoContext.setTitle("Single Game");
    }

    public void cargarPalabras(){
        this.palabras.add("este");
        this.palabras.add("es");
        this.palabras.add("un");
        this.palabras.add("single");
        this.palabras.add("game");

    }
}
