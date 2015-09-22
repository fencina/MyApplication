package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.myapplication.MESSAGE";
    public Integer editTextId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_my, menu);
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

    public void addName(View view){
        EditText editText = new EditText(this);
        editText.setLayoutParams(new LinearLayout.LayoutParams(300, 75));
        editText.setHint("Nombre");
        editText.setId(editTextId);
        editTextId++;

        LinearLayout myActivityView = (LinearLayout) findViewById(R.id.nombres);
        myActivityView.addView(editText);
    }

    public void send(View view){
        Intent intent = new Intent(this,DisplayMessageActivity.class);

        List<String> nombresList = new ArrayList<String>();

        for (int i = 0 ; i <= editTextId - 1 ; i++){
            EditText editText = (EditText) findViewById(i);
            String nombre = editText.getText().toString();
            nombresList.add(nombre);
        }



        intent.putStringArrayListExtra("nombresList",(ArrayList<String>) nombresList);
        startActivity(intent);

    }
}
