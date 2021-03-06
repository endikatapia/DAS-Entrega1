package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ActivityPostre extends AppCompatActivity implements FragmentLVMultipleChoice.listenerDelFragment {

    ListView listView;
    ArrayAdapter eladaptador;
    ArrayList<String> comidas = new ArrayList<>();

    String[] datosPostre={"Profiteroles", "Tarta de queso", "Tiramisú", "Panna cotta"};
    int[] comidaPostre={R.drawable.profiteroles,R.drawable.tartaqueso,R.drawable.tiramisu,R.drawable.pannacotta};
    String[] ingredientesPostre={"leche, mantequilla, harina, huevos, limón, canela, azúcar","galletas, nata, azucar, queso, mermelada","queso mascarpone, yemas, azúcar glass, cacao en polvo, café fuerte","nata, azúcar, gelatina, vainilla, canela"};
    double[] preciosPostre = {5,4,6.5,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postre);
        listView=findViewById(R.id.lv);


    }



    public void seleccionarElemento(String nombreComida, int imagen, String ingredientes,double precio){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            //EL OTRO FRAGMENT EXISTE
            System.out.println("-------------HORIZONTAL---------");
            FragmentDetalles elotro=(FragmentDetalles) getSupportFragmentManager().findFragmentById(R.id.fragmentFotoIndv);
            elotro.setDatos(nombreComida,imagen,ingredientes,precio);
        }
        else{
            //EL OTRO FRAGMENT NO EXISTE, HAY QUE LANZAR LA ACTIVIDAD QUE LO CONTIENE
            System.out.println("-------------VERTICAL---------");

            Intent i= new Intent(ActivityPostre.this,ActivityDetalles.class);
            i.putExtra("nombre",nombreComida);
            i.putExtra("imagen",imagen);
            i.putExtra("ingredientes",ingredientes);
            i.putExtra("precio",precio);
            startActivity(i);
        }


    }


    @Override
    public void ponerLista() {
        eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datosPostre);
        listView.setAdapter(eladaptador);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                seleccionarElemento(datosPostre[position],comidaPostre[position],ingredientesPostre[position],preciosPostre[position]);
                System.out.println(datosPostre[position]);
            }
        });
    }

    public void onClickFinalizar(View v){
        //LANZAR LA NOTIFICACION DE QUE HA ACABADO EL PEDIDO Y DAR OPCION DE VOLVER A LA CARTA O VER EL PEDIDO

    }





}