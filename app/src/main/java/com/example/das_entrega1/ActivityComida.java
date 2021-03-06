package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class ActivityComida extends AppCompatActivity implements FragmentLVMultipleChoice.listenerDelFragment{

    ListView listView;
    ArrayAdapter eladaptador;
    ArrayList<String> comidas = new ArrayList<>();

    //DATOS PIZZA(CATEGORIA 0)
    String[] datosPizza={"Pizza Margarita", "Pizza Boloñesa", "Pizza Carbonara", "Pizza 4 Quesos","Pizza Napolitana","Pizza Atun","Pizza Sorrento","Pizza Tropical"};
    int[] comidaPizza={R.drawable.pizzamargarita,R.drawable.pizzabolognesa,R.drawable.pizzacarbonara,R.drawable.pizza4quesos,R.drawable.pizzanapolitana,R.drawable.pizzaatun,R.drawable.pizzasorrento,R.drawable.pizzatropical};
    String[] ingredientesPizza={"mozzarella, tomate","mozzarella, orégano, salsa bolognesa","mozzarella, tomate, beicon, huevo, cebolla","tomate, orégano, 4 quesos","mozzarella, tomate, orégano, anchoas, aceitunas","mozzarella, tomate, orégano, atún","jamón serrano, tomate, rúcula, láminas de parmesano","mozzarella, tomate, orégano, jamón, piña"};
    double[] preciosPizza = {10,13.50,13.50,12.50,11.50,12.50,13,13.50};

    //DATOS ENSALADA(CATEGORIA 1)
    String[] datosEnsalada={"Ensalada Mixta", "Ensalada Tropical", "Ensalada de Pasta","Ensalada Campera","Ensalada Capresse","Ensalada Fruti di mare"};
    int[] comidaEnsalada={R.drawable.ensaladamixta,R.drawable.ensaladatropical,R.drawable.ensaladapasta,R.drawable.ensaladacampera,R.drawable.ensaladacapresse,R.drawable.ensaladafdmare};
    String[] ingredientesEnsalada={"atún, espárragos, cebolla, tomate, lechuga","piña, manzana, maíz, tomate, lechuga, jamón, salsa rosa","pasta, atún, maíz, aceitunas, cebolla, salsa rosa","patata, maíz, nueces, lechuga, salsa rosa","mozzarella de búfala, tomate, orégano, aceitunas, alcaparras","mejillones, gambas, palitos de cangrejo, tomate, lechuga y salsa rosa"};
    double[] preciosEnsalada = {7,9,9,10,10,9,9.5};

    //DATOS ARROZ(CATEGORIA 2)
    String[] datosArroz={"Risotto de Setas","Risotto Marinero","Risotto 4 Quesos"};
    int[] comidaArroz={R.drawable.arrozsetas,R.drawable.risottomarinero,R.drawable.risotto4quesos};
    String[] ingredientesArroz={"caldo de pollo, arroz carnaroli, setas funghi, cebolla, rucula","arroz arborio, cebolla, caldo de pescado, mejillones, calamar, cáscara de limón","arroz bomba, gorgonzola, gouda, chedar, parmesano"};
    double[] preciosArroz = {10,10,11};

    //DATOS ESPAGUETI(CATEGORIA 3)
    String[] datosEspagueti={"Espagueti al Pesto","Espagueti Boloñesa","Espagueti Carbonara","Espagueti Siciliana","Espagueti con Gambas"};
    int[] comidaEspagueti={R.drawable.espaguetipesto,R.drawable.espaguetibolonesa,R.drawable.espagueticarbonara,R.drawable.espaguetisicilia,R.drawable.espaguetigambas};
    String[] ingredientesEspagueti={"espagueti, albahaca, parmesano, piñones","salsa bolognesa, parmesano","nata, bacon, parmesano, huevo","anchoas, alcaparras, aceitunas, salsa de tomate","gambas, ajo, picante"};
    double[] preciosEspagueti = {9,9,9,10,11};

    //DATOS ESPECIALIDAD(CATEGORIA 4)
    String[] datosEspecialidad={"Lasagna de Carne","Ravioli de Setas","Tagliatelle al Andrea","Carpaccio de Carne","Provolone a la Plancha"};
    int[] comidaEspecialidad={R.drawable.lasagnacarne,R.drawable.raviolisetas,R.drawable.tagliatele,R.drawable.carpaccocarne,R.drawable.provolone};
    String[] ingredientesEspecialidad={"carne picada, placas de canelón, salsa de tomate, cebolla, queso en lonchas, leche","harina de trigo, huevos, setas frescas, cebolla","nata, jamón serrano, espinacas, queso ricotta","solomillo de ternera, alcaparras, queso Parmesano, mostaza en grano","queso provolone, tomate, orégano seco, albahaca fresca"};
    double[] preciosEspecialidad = {10.50,11,10,12,9};




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
                //PIZZA
                //Creamos el ArrayAdapter con la posibilidad de elegir mas de un item
                eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datosPizza);
                listView.setAdapter(eladaptador);

                //cuando clickamos en un item se selecciona el checkbox y nos sale informacion acerca de el plato
                //se gestiona en la función seleccionarElemento de esta misma clase
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datosPizza[position],comidaPizza[position],ingredientesPizza[position],preciosPizza[position]);
                        System.out.println(datosPizza[position]);
                        //comidas.add(datos[position]);
                    }
                });


            }

            else if (categoria==1){
                //ENSALADA
                eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datosEnsalada);
                listView.setAdapter(eladaptador);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datosEnsalada[position],comidaEnsalada[position],ingredientesEnsalada[position],preciosEnsalada[position]);
                        System.out.println(datosEnsalada[position]);
                    }
                });
            }

            else if (categoria==2){
                //ARROZ
                eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datosArroz);
                listView.setAdapter(eladaptador);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datosArroz[position],comidaArroz[position],ingredientesArroz[position],preciosArroz[position]);
                        System.out.println(datosArroz[position]);
                        //comidas.add(datos3[position]);
                    }
                });
            }

            else if (categoria==3){
                //ESPAGUETI
                eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datosEspagueti);
                listView.setAdapter(eladaptador);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datosEspagueti[position],comidaEspagueti[position],ingredientesEspagueti[position],preciosEspagueti[position]);
                        System.out.println(datosEspagueti[position]);
                        //comidas.add(datos4[position]);
                    }
                });
            }


            else if (categoria==4){
                //ESPECIALIDAD
                eladaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice,datosEspecialidad);
                listView.setAdapter(eladaptador);

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                        seleccionarElemento(datosEspecialidad[position],comidaEspecialidad[position],ingredientesEspecialidad[position],preciosEspecialidad[position]);
                        System.out.println(datosEspecialidad[position]);
                        //comidas.add(datos5[position]);

                    }
                });
            }




        }
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

            Intent i= new Intent(ActivityComida.this,ActivityDetalles.class);
            i.putExtra("nombre",nombreComida);
            i.putExtra("imagen",imagen);
            i.putExtra("ingredientes",ingredientes);
            i.putExtra("precio",precio);
            startActivity(i);
        }


    }






    public void onClickAnadirYvolver(View v){
        //https://stackoverflow.com/questions/3996938/why-is-listview-getcheckeditempositions-not-returning-correct-values
        //Coger los valores que se han seleccionado de las listView
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i)) {
                    String item = listView.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                    Log.i("TAG",item + " was selected");
                    //añadirlos a el arraylist
                    comidas.add(item);
                }
            }
        }

        //ver los platos seleccionados del arraylist
        for (int z=0;z<comidas.size();z++) {
            System.out.println("PLATO: " + comidas.get(z));
            Toast.makeText(this,"Has añadido a tu pedido: "+comidas.get(z),Toast.LENGTH_SHORT).show();
        }



        //AÑADIR A UN FICHERO LO DEL ARRAYLIST<STRING> comidas PARA SABER QUE ES LO QUE VA PIDIENDO EL CLIENTE
        Intent i2 = new Intent(ActivityComida.this,MainActivity.class);
        //i2.putExtra("comidas",comidas);
        startActivity(i2);


    }












}