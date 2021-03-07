package com.example.das_entrega1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogoFinal extends DialogFragment {


    ListenerdelDialogo miListener;

    public interface ListenerdelDialogo {
        void alpulsarFinalizar();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        miListener =(ListenerdelDialogo) getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Finalizar pedido");
        builder.setMessage("¿Desea finalizar el pedido o se le ha olvidado pedir algún postre?");
        builder.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                miListener.alpulsarFinalizar();

            }
        });

        builder.setNegativeButton("Volver a los postres", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //Al pulsar fuera o al dar al boton de atras no se cancela el dialogo
        setCancelable(false);
        return builder.create();
    }
}

