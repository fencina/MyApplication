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
        this.palabras.add("juego");
        this.palabras.add("hola");
        this.palabras.add("chau");
        this.palabras.add("mensaje");
        this.palabras.add("android");
        this.palabras.add("php");
        this.palabras.add("java");
        this.palabras.add("ruby");
        this.palabras.add("python");
        this.palabras.add("pascal");
        this.palabras.add("smalltalk");

    }
}
