package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityDetalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        FragmentDetalles elotro = (FragmentDetalles) getSupportFragmentManager().findFragmentById(R.id.fragmentFotoIndv);
        String plato=getIntent().getStringExtra("nombre");
        int imagen = getIntent().getIntExtra("imagen",0);
        String ingredientes = getIntent().getStringExtra("ingredientes");
        double precio = getIntent().getDoubleExtra("precio",0);
        elotro.setDatos(plato,imagen,ingredientes,precio);
    }
}