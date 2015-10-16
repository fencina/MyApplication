package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.myapplication.constantes.TipoJuegosConstant;

import org.w3c.dom.Text;

public class ResultadosActivity extends AppCompatActivity {

    public static final String  TAG = "ResultadosActivty";
    public int tipoJuego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        Intent resultadosIntent = getIntent();
        int palabrasEscritas = resultadosIntent.getExtras().getInt("palabrasEscritas");
        tipoJuego = resultadosIntent.getExtras().getInt("tipoJuego");
        long tiempoTotal = resultadosIntent.getExtras().getLong("tiempoTotal");

        TextView resultadoText = (TextView) findViewById(R.id.resultado);
        resultadoText.setText("Escribiste "+palabrasEscritas+" palabras en "+tiempoTotal/1000+" segundos");

    }

    public void irMenuPrincipal(View view){
        Intent menuIntent = new Intent(this, MenuPrincipalActivity.class);
        startActivity(menuIntent);
    }

    public void volverAJugar(View view){
        MenuPrincipal menu = new MenuPrincipal(this);

        switch (tipoJuego){
            case TipoJuegosConstant.SINGLE:
                menu.startSingleGame(view);
                break;
            case TipoJuegosConstant.SURVIVAL:
                menu.startSurvivalGame(view);
                break;
            default:
                Log.d(TAG, "No me llego ningun tipo de juego");
                break;
        }

    }

}
