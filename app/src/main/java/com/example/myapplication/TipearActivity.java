package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.myapplication.constantes.TipoJuegosConstant;


public class TipearActivity extends AppCompatActivity {

    public final static String TAG = "TipearActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipear);

        Juego nuevoJuego;

        Intent gameIntent = getIntent();
        int tipoJuego = gameIntent.getExtras().getInt("tipoJuego");


        switch (tipoJuego){
            case TipoJuegosConstant.SINGLE:
                nuevoJuego = new SingleGame(this);
                break;
            case TipoJuegosConstant.SURVIVAL:
                nuevoJuego = new SurvivalGame(this);
                break;
            default:
                Log.d(TAG, "No se eligio ningun juego");
                nuevoJuego = new SingleGame(this);
                break;
        }

        nuevoJuego.inicializar();
        nuevoJuego.setNextWord();
        nuevoJuego.setListeners();
    }




}
