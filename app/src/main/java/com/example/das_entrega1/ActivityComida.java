package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ActivityComida extends AppCompatActivity implements FragmentLVMultipleChoice.listenerDelFragment{

    ListView listView;
    AdaptadorListView adaptador;
    String[] datos={"Pizza 1","Pizza 2","Pizza 3","Pizza 4","Pizza 5"};
    int[] comida={R.drawable.arrozz,R.drawable.ensalada,R.drawable.esp,R.drawable.las,R.drawable.pizza};
    //String[] ingredientes={"Tomate,aguacate...","rucula,..."};
    //double[] precios = {12.7,....};
    int[] titulos = {23,54,65,8,10};

    String[] datos2={"Ensalada 1","Ensalada 2","Ensalada 3","Ensalada 4","Ensalada 5"};
    int[] comida2={R.drawable.arrozz,R.drawable.arrozz,R.drawable.arrozz,R.drawable.arrozz,R.drawable.arrozz};
    //String[] ingredientes={"Tomate,aguacate...","rucula,..."};
    //double[] precios = {12.7,....};
    int[] titulos2 = {23,54,65,8,10};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comida);
        listView=findViewById(R.id.lv);
    }



    public void ponerLista(){

        Bundle extras= getIntent().getExtras();
        if (extras != null){
            int categoria = extras.getInt("categoria");
            if (categoria==0){
                //nombreCategoria.setText("Pizzas");
                ArrayAdapter eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datos);
                //ArrayAdapter eladaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,datos);
                listView.setAdapter(eladaptador);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datos[position],comida[position],titulos[position]);
                        System.out.println(datos[position]);
                    }
                });
            }
            else if (categoria==1){
                //nombreCategoria.setText("Ensaladas");
                ArrayAdapter eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datos2);
                //ArrayAdapter eladaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,datos);
                listView.setAdapter(eladaptador);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datos2[position],comida2[position],titulos2[position]);
                        System.out.println(datos2[position]);
                    }
                });
            }

        }
    }


    public void seleccionarElemento(String nombreEquipo, int imagen, int titulos){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE){
            //EL OTRO FRAGMENT EXISTE
            System.out.println("-------------HORIZONTAL---------");
            FragmentDetalles elotro=(FragmentDetalles) getSupportFragmentManager().findFragmentById(R.id.fragmentFotoIndv);
            elotro.hacerAlgo(nombreEquipo,imagen,titulos);
        }
        else{
            //EL OTRO FRAGMENT NO EXISTE, HAY QUE LANZAR LA ACTIVIDAD QUE LO CONTIENE
            System.out.println("-------------VERTICAL---------");

            Intent i= new Intent(ActivityComida.this,ActivityDetalles.class);
            i.putExtra("equipo",nombreEquipo);
            i.putExtra("imagen",imagen);
            i.putExtra("titulos",titulos);
            startActivity(i);
        }


    }






}