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
        this.palabras.add("survival");
        this.palabras.add("prolog");
        this.palabras.add("haskell");
        this.palabras.add("scala");
        this.palabras.add("spring");
        this.palabras.add("symfony");
        this.palabras.add("node");
        this.palabras.add("express");
        this.palabras.add("angular");
        this.palabras.add("jquery");
        this.palabras.add("javascript");
    }
}
