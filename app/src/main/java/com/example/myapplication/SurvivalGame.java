package com.example.myapplication;

import android.app.Activity;

public class SurvivalGame extends Juego {

    public SurvivalGame(Activity juegoContext){
        super(juegoContext);
    }

    public void setTime(){
        this.time = 5000;
    }

    public void setTitle(){
        this.juegoContext.setTitle("Survival Game");
    }

    public void cargarPalabras(){
        this.palabras.add("en");
        this.palabras.add("cambio");
        this.palabras.add("este");
        this.palabras.add("survival");
        this.palabras.add("es");
    }
}
