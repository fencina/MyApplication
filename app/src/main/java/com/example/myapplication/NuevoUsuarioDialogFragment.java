package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;

import java.util.ArrayList;

public class NuevoUsuarioDialogFragment extends DialogFragment {

    public static ArrayList<Usuario> usuariosCreados;
    public static Activity abmUsuarioActivity = new AbmUsuarioActivity();

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {



        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.dialog_nuevo_usuario)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // guardar usuario
                        Usuario usuario = new Usuario();

                        EditText editTextUser = (EditText) abmUsuarioActivity.findViewById(R.id.username);
                        String textUser = editTextUser.getText().toString();

                        EditText editTextPassword = (EditText) abmUsuarioActivity.findViewById(R.id.password);
                        String textPassword = editTextPassword.getText().toString();

                        usuario.user = textUser;
                        usuario.password = textPassword;

                        usuariosCreados.add(usuario);

                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
