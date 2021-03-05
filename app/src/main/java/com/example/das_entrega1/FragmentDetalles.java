package com.example.das_entrega1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentDetalles extends Fragment {

    ListView lv;
    Button pedir;




    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragmentdetalles,container,false);
        //pedir=(Button)v.findViewById(R.id.pedir);
        return v;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //TextView nombre= getView().findViewById(R.id.nombre);
        //TextView titulos= getView().findViewById(R.id.titulos);
        //ImageView imagen = getView().findViewById(R.id.imagenV);




    }


    public void hacerAlgo(String elemento, int imageCode, int titulos) {



        TextView nombre= getView().findViewById(R.id.nombre);
        nombre.setText(elemento);

        ImageView imagen = getView().findViewById(R.id.imagenV);
        imagen.setImageResource(imageCode);

        TextView titulo= getView().findViewById(R.id.titulos);
        titulo.setText("Titulos: " + titulos);

    }




}