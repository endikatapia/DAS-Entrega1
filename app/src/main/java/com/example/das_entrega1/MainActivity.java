package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView lalista = findViewById(R.id.rv);


        int[] categorias= {R.drawable.pizza, R.drawable.ensalada, R.drawable.arroz,R.drawable.espagueti, R.drawable.especialidad};
        String[] nombres={"Pizzas", "Ensaladas", "Arroces", "Espagueti", "Especialidad"};
        ElAdaptadorRecycler eladaptador = new ElAdaptadorRecycler(nombres,categorias);
        lalista.setAdapter(eladaptador);



        //GRID LAYOUT
        GridLayoutManager elLayoutRejillaIgual= new GridLayoutManager(this,2,GridLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutRejillaIgual);
    }
}