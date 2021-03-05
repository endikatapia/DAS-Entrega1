package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ActivityDetalles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        FragmentDetalles elotro = (FragmentDetalles) getSupportFragmentManager().findFragmentById(R.id.fragmentFotoIndv);
        String equipo=getIntent().getStringExtra("equipo");
        int imagen = getIntent().getIntExtra("imagen",0);
        int titulos = getIntent().getIntExtra("titulos",0);
        elotro.hacerAlgo(equipo,imagen,titulos);
    }
}