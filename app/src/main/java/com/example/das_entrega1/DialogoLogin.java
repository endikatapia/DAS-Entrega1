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
        //estilo de dialogo definido en styles.xml
        //AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(),R.style.AlertDialogCustom);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        //recogemos el usuario que nos viene de parametro desde el constructor
        String usudialogo = getString(R.string.usudialogo);
        String noexiste = getString(R.string.noexiste);
        String registro = getString(R.string.registrar);
        String si = getString(R.string.si);
        String no = getString(R.string.no);
        builder.setTitle(usudialogo + usuario + noexiste);
        builder.setMessage(registro);
        builder.setPositiveButton(si, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Lo gestiona la actividad ActivityLogin
                miListener.alpulsarSi();
            }
        });

        builder.setNegativeButton(no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Al pulsar NO no hace nada
            }
        });

        //Al pulsar fuera o al dar al boton de atras no se cancela el dialogo
        setCancelable(false);
        //para que al rotar el dialog no pete
        setRetainInstance(true);
        return builder.create();
    }
}
