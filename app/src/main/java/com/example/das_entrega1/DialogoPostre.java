package com.example.das_entrega1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoPostre extends DialogFragment {


    ListenerdelDialogo miListener;

    public interface ListenerdelDialogo {
        void alpulsarSi();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        miListener =(ListenerdelDialogo) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Continuar con el pedido");
        builder.setMessage("¿Desea ir a los Postres o se le ha olvidado pedir algo de la carta?");
        builder.setPositiveButton("!POSTRES!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                miListener.alpulsarSi();

            }
        });

        builder.setNegativeButton("Volver a la carta", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //Al pulsar fuera o al dar al boton de atras no se cancela el dialogo
        setCancelable(false);
        return builder.create();
    }
}
