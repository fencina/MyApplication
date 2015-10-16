package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

import com.example.myapplication.constantes.TipoJuegosConstant;

public class MenuPrincipal {

    public static Activity context;

    public MenuPrincipal(Activity context){
        this.context = context;
    }

    public void startSingleGame(View view){
        startGame(TipoJuegosConstant.SINGLE);
    }

    public void startSurvivalGame(View view)
    {
        startGame(TipoJuegosConstant.SURVIVAL);
    }

    public void startGame(int tipoJuego){
        Intent gameIntent = new Intent(context, TipearActivity.class);

        gameIntent.putExtra("tipoJuego", tipoJuego);
        context.startActivity(gameIntent);
    }
}
