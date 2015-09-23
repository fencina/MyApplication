package com.example.myapplication;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TipearActivity extends AppCompatActivity {

    public static ArrayList<String> palabras = new ArrayList<>();
    public static Integer posicion = 0;
    public static String palabraActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipear);
        cargarPalabras();

        TextView palabra = (TextView) findViewById(R.id.palabra);
        palabraActual = getRandomWord();
        palabra.setText(palabraActual);
//        posicion++;

        EditText tipeado = (EditText) findViewById(R.id.texto_tipeado);

        tipeado.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    new CountDownTimer(10000, 1000) {

                        public void onTick(long millisUntilFinished) {
                            TextView reloj = (TextView) findViewById(R.id.reloj);
                            reloj.setText("Quedan " + millisUntilFinished  / 1000 + " segundos");
                        }

                        public void onFinish() {
                            TextView reloj = (TextView) findViewById(R.id.reloj);

                            if ( palabras.size() != 0) {
                                TextView palabra = (TextView) findViewById(R.id.palabra);
                                String nuevaPalabra = getRandomWord();
                                palabra.setText(nuevaPalabra);
                                palabraActual = nuevaPalabra;
//                                posicion++;
                                this.start();
                            } else reloj.setText("Terminado!");

                        }
                    }.start();

                }
            }
        });

        tipeado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (  s.toString().equals( palabraActual )){
                    TextView palabra = (TextView) findViewById(R.id.palabra);
                    palabra.setText("Bien!");
                    TextView tipeado = (TextView) findViewById(R.id.texto_tipeado);
                    tipeado.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void cargarPalabras(){
        palabras.add("hola");
        palabras.add("chau");
        palabras.add("android");
        palabras.add("mensaje");
    }

    public String getRandomWord(){
        Integer posicion = new Random().nextInt(palabras.size());
        String palabraRandom = palabras.get(posicion);
        palabras.remove(palabraRandom);

        return palabraRandom;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
