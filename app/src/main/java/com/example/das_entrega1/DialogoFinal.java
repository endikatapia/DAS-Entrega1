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
        //estilo de dialogo definido en styles.xml
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String fin = getString(R.string.finPedido);
        String pof = getString(R.string.finOpostre);
        String finzar = getString(R.string.finzar);
        String vps = getString(R.string.volverPostres);
        builder.setTitle(fin);
        builder.setMessage(pof);
        builder.setPositiveButton(finzar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                miListener.alpulsarFinalizar();

            }
        });

        builder.setNegativeButton(vps, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        //Al pulsar fuera o al dar al boton de atras no se cancela el dialogo
        setCancelable(false);
        return builder.create();
    }
}

