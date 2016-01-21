package com.example.myapplication;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.myapplication.constantes.TipoJuegosConstant;

public class MenuPrincipalActivity extends AppCompatActivity {

    public static MenuPrincipal menu;
    public static AnimationDrawable animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        menu = new MenuPrincipal(this);

        ImageView animationImage = (ImageView) findViewById(R.id.animationView);
        animationImage.setBackgroundResource(R.drawable.animation_test);

        animation = (AnimationDrawable) animationImage.getBackground();
        animation.start();
    }

    public void startSingleGame(View view){
        menu.startGame(TipoJuegosConstant.SINGLE);
    }

    public void startSurvivalGame(View view){
        menu.startGame(TipoJuegosConstant.SURVIVAL);
    }

    public void verPuntajes(View view){
        Intent puntajesIntent = new Intent(this,PuntajesActivity.class);
        startActivity(puntajesIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_principal, menu);
        return true;
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
