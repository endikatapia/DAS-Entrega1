package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;




public class MainActivity extends AppCompatActivity {

    String[] idiomas =  { "Castellano", "Ingles", "Italiano"};
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


        Spinner spin = (Spinner) findViewById(R.id.idiomas);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,idiomas);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        spin.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position==0){
                            System.out.println("Castellano");
                        }
                        else if (position==1){
                            System.out.println("Ingles");
                        }
                        else if (position==2){
                            System.out.println("Italiano");
                        }


                    }
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });



    }
}