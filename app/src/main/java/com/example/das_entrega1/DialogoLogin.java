package com.example.das_entrega1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoLogin extends DialogFragment {


    ListenerdelDialogo miListener;
    private String usuario;

    public DialogoLogin(String user) {
        usuario=user;
    }

    public interface ListenerdelDialogo {
        void alpulsarSi();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        miListener =(ListenerdelDialogo) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("El usuario "+ usuario + " no existe");
        builder.setMessage("¿Deseas Registrarte?");
        builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo gestiona la actividad ActivityLogin
                miListener.alpulsarSi();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Al pulsar NO no hace nada
            }
        });

        //Al pulsar fuera o al dar al boton de atras no se cancela el dialogo
        setCancelable(false);
        return builder.create();
    }
}
