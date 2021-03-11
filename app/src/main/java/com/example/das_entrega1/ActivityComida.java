package com.example.das_entrega1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class ActivityComida extends AppCompatActivity implements FragmentLVMultipleChoice.listenerDelFragment{

    ListView listView;
    ArrayAdapter eladaptador;
    ArrayList<String> comidas = new ArrayList<>();
    double precio;
    private String[] datosPizza;
    private String[] ingredientesPizza;
    private String[] datosEnsalada;
    private String[] ingredientesEnsalada;
    private String[] datosArroz;
    private String[] ingredientesArroz;
    private String[] datosEspagueti;
    private String[] ingredientesEspagueti;
    private String[] datosEspecialidad;
    private String[] ingredientesEspecialidad;




    //DATOS PIZZA(CATEGORIA 0)
    //String[] datosPizza={"Pizza Margarita", "Pizza Boloñesa", "Pizza Carbonara", "Pizza 4 Quesos","Pizza Napolitana","Pizza Atun","Pizza Sorrento","Pizza Tropical"};
    int[] comidaPizza={R.drawable.pizzamargarita,R.drawable.pizzabolognesa,R.drawable.pizzacarbonara,R.drawable.pizza4quesos,R.drawable.pizzanapolitana,R.drawable.pizzaatun,R.drawable.pizzasorrento,R.drawable.pizzatropical};
    //String[] ingredientesPizza={"mozzarella, tomate","mozzarella, orégano, salsa bolognesa","mozzarella, tomate, beicon, huevo, cebolla","tomate, orégano, 4 quesos","mozzarella, tomate, orégano, anchoas, aceitunas","mozzarella, tomate, orégano, atún","jamón serrano, tomate, rúcula, láminas de parmesano","mozzarella, tomate, orégano, jamón, piña"};
    double[] preciosPizza = {10,13.50,13.50,12.50,11.50,12.50,13,13.50};

    //DATOS ENSALADA(CATEGORIA 1)
    //String[] datosEnsalada={"Ensalada Mixta", "Ensalada Tropical", "Ensalada de Pasta","Ensalada Campera","Ensalada Capresse","Ensalada Fruti di mare"};
    int[] comidaEnsalada={R.drawable.ensaladamixta,R.drawable.ensaladatropical,R.drawable.ensaladapasta,R.drawable.ensaladacampera,R.drawable.ensaladacapresse,R.drawable.ensaladafdmare};
    //String[] ingredientesEnsalada={"atún, espárragos, cebolla, tomate, lechuga","piña, manzana, maíz, tomate, lechuga, jamón, salsa rosa","pasta, atún, maíz, aceitunas, cebolla, salsa rosa","patata, maíz, nueces, lechuga, salsa rosa","mozzarella de búfala, tomate, orégano, aceitunas, alcaparras","mejillones, gambas, palitos de cangrejo, tomate, lechuga y salsa rosa"};
    double[] preciosEnsalada = {7,9,9,10,9,9.5};

    //DATOS ARROZ(CATEGORIA 2)
    //String[] datosArroz={"Risotto de Setas","Risotto Marinero","Risotto 4 Quesos"};
    int[] comidaArroz={R.drawable.arrozsetas,R.drawable.risottomarinero,R.drawable.risotto4quesos};
    //String[] ingredientesArroz={"caldo de pollo, arroz carnaroli, setas funghi, cebolla, rucula","arroz arborio, cebolla, caldo de pescado, mejillones, calamar, cáscara de limón","arroz bomba, gorgonzola, gouda, chedar, parmesano"};
    double[] preciosArroz = {10,10,11};

    //DATOS ESPAGUETI(CATEGORIA 3)
    //String[] datosEspagueti={"Espagueti al Pesto","Espagueti Boloñesa","Espagueti Carbonara","Espagueti Siciliana","Espagueti con Gambas"};
    int[] comidaEspagueti={R.drawable.espaguetipesto,R.drawable.espaguetibolonesa,R.drawable.espagueticarbonara,R.drawable.espaguetisicilia,R.drawable.espaguetigambas};
    //String[] ingredientesEspagueti={"espagueti, albahaca, parmesano, piñones","salsa bolognesa, parmesano","nata, bacon, parmesano, huevo","anchoas, alcaparras, aceitunas, salsa de tomate","gambas, ajo, picante"};
    double[] preciosEspagueti = {9,9,9,10,11};

    //DATOS ESPECIALIDAD(CATEGORIA 4)
    //String[] datosEspecialidad={"Lasagna de Carne","Ravioli de Setas","Tagliatelle al Andrea","Carpaccio de Carne","Provolone a la Plancha"};
    int[] comidaEspecialidad={R.drawable.lasagnacarne,R.drawable.raviolisetas,R.drawable.tagliatele,R.drawable.carpaccocarne,R.drawable.provolone};
    //String[] ingredientesEspecialidad={"carne picada, placas de canelón, salsa de tomate, cebolla, queso en lonchas, leche","harina de trigo, huevos, setas frescas, cebolla","nata, jamón serrano, espinacas, queso ricotta","solomillo de ternera, alcaparras, queso Parmesano, mostaza en grano","queso provolone, tomate, orégano seco, albahaca fresca"};
    double[] preciosEspecialidad = {10.50,11,10,12,9};


    OutputStreamWriter fichero;
    TextView userr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //establecer idioma
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String idioma = prefs.getString("idiomapref", "es");

        Locale nlocale = new Locale(idioma);
        Locale.setDefault(nlocale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(nlocale);
        configuration.setLayoutDirection(nlocale);

        Context context = getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());



        if (idioma.equals("es")) {
            datosPizza = new String[]{"Pizza Margarita", "Pizza Boloñesa", "Pizza Carbonara", "Pizza 4 Quesos", "Pizza Napolitana", "Pizza Atun", "Pizza Sorrento", "Pizza Tropical"};
            ingredientesPizza = new String[]{"mozzarella, tomate", "mozzarella, orégano, salsa bolognesa", "mozzarella, tomate, beicon, huevo, cebolla", "tomate, orégano, 4 quesos", "mozzarella, tomate, orégano, anchoas, aceitunas", "mozzarella, tomate, orégano, atún", "jamón serrano, tomate, rúcula, láminas de parmesano", "mozzarella, tomate, orégano, jamón, piña"};

            datosEnsalada= new String[]{"Ensalada Mixta", "Ensalada Tropical", "Ensalada de Pasta", "Ensalada Campera", "Ensalada Capresse", "Ensalada Fruti di mare"};
            ingredientesEnsalada= new String[]{"atún, espárragos, cebolla, tomate, lechuga", "piña, manzana, maíz, tomate, lechuga, jamón, salsa rosa", "pasta, atún, maíz, aceitunas, cebolla, salsa rosa", "patata, maíz, nueces, lechuga, salsa rosa", "mozzarella de búfala, tomate, orégano, aceitunas, alcaparras", "mejillones, gambas, palitos de cangrejo, tomate, lechuga y salsa rosa"};

            datosArroz= new String[]{"Risotto de Setas", "Risotto Marinero", "Risotto 4 Quesos"};
            ingredientesArroz= new String[]{"caldo de pollo, arroz carnaroli, setas funghi, cebolla, rucula", "arroz arborio, cebolla, caldo de pescado, mejillones, calamar, cáscara de limón", "arroz bomba, gorgonzola, gouda, chedar, parmesano"};

            datosEspagueti= new String[]{"Espagueti al Pesto", "Espagueti Boloñesa", "Espagueti Carbonara", "Espagueti Siciliana", "Espagueti con Gambas"};
            ingredientesEspagueti= new String[]{"espagueti, albahaca, parmesano, piñones", "salsa bolognesa, parmesano", "nata, bacon, parmesano, huevo", "anchoas, alcaparras, aceitunas, salsa de tomate", "gambas, ajo, picante"};

            datosEspecialidad= new String[]{"Lasagna de Carne", "Ravioli de Setas", "Tagliatelle al Andrea", "Carpaccio de Carne", "Provolone a la Plancha"};
            ingredientesEspecialidad= new String[]{"carne picada, placas de canelón, salsa de tomate, cebolla, queso en lonchas, leche", "harina de trigo, huevos, setas frescas, cebolla", "nata, jamón serrano, espinacas, queso ricotta", "solomillo de ternera, alcaparras, queso Parmesano, mostaza en grano", "queso provolone, tomate, orégano seco, albahaca fresca"};


        } else if (idioma.equals("en")){
            datosPizza = new String[]{"Pizza Margarita", "Pizza Bolognese", "Pizza Carbonara", "Pizza 4 Cheese", "Pizza Neapolitan", "Pizza Tuna", "Pizza Sorrento", "Pizza Tropical"};
            ingredientesPizza = new String[]{"mozzarella, tomato", "mozzarella, oregano, bolognese sauce", "mozzarella, tomato, bacon, egg, onion", "tomato, oregano, 4 cheeses", "mozzarella, tomato, oregano, anchovies, olives", "mozzarella, tomato, oregano, tuna","serrano ham, tomato, arugula, parmesan slices","mozzarella, tomato, oregano, ham, pineapple"};

            datosEnsalada= new String[]{"Mixed Salad", "Tropical Salad", "Pasta Salad", "Country Salad", "Capresse Salad", "Fruti di mare Salad"};
            ingredientesEnsalada= new String[]{"tuna, asparagus, onion, tomato, lettuce", "pineapple, apple, corn, tomato, lettuce, ham, pink sauce", "pasta, tuna, corn, olives, onion, pink sauce", "potato, corn, walnuts , lettuce, pink sauce","buffalo mozzarella, tomato, oregano, olives, capers","mussels, prawns, crab sticks, tomato, lettuce and pink sauce"};

            datosArroz= new String[]{"Mushroom Risotto", "Sailor Risotto", "4 Cheese Risotto"};
            ingredientesArroz= new String[]{"chicken broth, carnaroli rice, funghi mushrooms, onion, rocket", "arborio rice, onion, fish broth, mussels, squid, lemon peel", "bomba rice, gorgonzola, gouda, chedar, parmesan"};

            datosEspagueti= new String[]{"Spaghetti with Pesto", "Spaghetti Bolognese", "Spaghetti Carbonara", "Sicilian Spaghetti", "Spaghetti with Prawns"};
            ingredientesEspagueti= new String[]{"spaghetti, basil, parmesan, pine nuts", "bolognese sauce, parmesan", "cream, bacon, parmesan, egg", "anchovies, capers, olives, tomato sauce", "prawns, garlic, spicy"};

            datosEspecialidad= new String[]{"Meat Lasagna", "Mushroom Ravioli", "Tagliatelle al Andrea", "Meat Carpaccio", "Grilled Provolone"};
            ingredientesEspecialidad= new String[]{"minced meat, cannelloni plates, tomato sauce, onion, sliced cheese, milk", "wheat flour, eggs, fresh mushrooms, onion", "cream, serrano ham, spinach, ricotta cheese", "beef tenderloin, capers, Parmesan cheese, grain mustard","provolone cheese, tomato, dried oregano, fresh basil"};


        } else if (idioma.equals("it")){
            datosPizza = new String[]{"Pizza Margarita", "Pizza Bolognese", "Pizza Carbonara", "Pizza 4 Formaggi", "Pizza Napoletana", "Pizza Tonno", "Pizza Sorrento", "Pizza Tropicale"};
            ingredientesPizza = new String[]{"mozzarella, pomodoro", "mozzarella, origano, ragù alla bolognese", "mozzarella, pomodoro, pancetta, uovo, cipolla", "pomodoro, origano, 4 formaggi", "mozzarella, pomodoro, origano, acciughe, olive", "mozzarella, pomodoro, origano, tonno","prosciutto serrano, pomodoro, rucola, scaglie di parmigiano","mozzarella, pomodoro, origano, prosciutto, ananas"};

            datosEnsalada= new String[]{"Insalata Mista", "Insalata Tropicale", "Insalata di Pasta", "Insalata Country", "Insalata Capresse", "Insalata Fruti di Mare"};
            ingredientesEnsalada= new String[]{"tonno, asparagi, cipolla, pomodoro, lattuga", "ananas, mela, mais, pomodoro, lattuga, prosciutto, salsa rosa", "pasta, tonno, mais, olive, cipolla, salsa rosa", "patata, mais, noci , lattuga, salsa rosa ","mozzarella di bufala, pomodoro, origano, olive, capperi","cozze, gamberi, bastoncini di granchio, pomodoro, lattuga e salsa rosa"};

            datosArroz= new String[]{"Risotto ai funghi", "Risotto alla marinara", "Risotto ai 4 formaggi"};
            ingredientesArroz= new String[]{"brodo di pollo, riso carnaroli, funghi funghi, cipolla, rucola", "riso arborio, cipolla, brodo di pesce, cozze, calamari, scorza di limone", "riso bomba, gorgonzola, gouda, chedar, parmigiano"};

            datosEspagueti= new String[]{"Spaghetti al Pesto", "Spaghetti alla Bolognese", "Spaghetti alla Carbonara", "Spaghetti Siciliani", "Spaghetti ai Gamberi"};
            ingredientesEspagueti= new String[]{"spaghetti, basilico, parmigiano, pinoli", "ragù alla bolognese, parmigiano", "panna, pancetta, parmigiano, uovo", "acciughe, capperi, olive, salsa di pomodoro", "gamberi, aglio, piccante"};

            datosEspecialidad= new String[]{"Lasagna di carne", "Ravioli ai funghi", "Tagliatelle al Andrea", "Carpaccio di carne", "Provolone alla griglia"};
            ingredientesEspecialidad= new String[]{"carne macinata, cannelloni, salsa di pomodoro, cipolla, formaggio a fette, latte", "farina di frumento, uova, funghi freschi, cipolla", "panna, prosciutto serrano, spinaci, ricotta", "filetto di manzo, capperi, Parmigiano, senape di grano","provola, pomodoro, origano secco, basilico fresco"};

        }



        setContentView(R.layout.activity_comida);
        listView=findViewById(R.id.lv);
        userr=findViewById(R.id.userr);
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






    @RequiresApi(api = Build.VERSION_CODES.O)
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
        }
        String platos = String.join(", ", comidas);

        //si el string contiene al menos una letra, es decir, que se haya pedido un plato
        if ( ! platos.isEmpty()) {
            Toast.makeText(this,"Has añadido a tu pedido: "+platos,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No has añadido nada",Toast.LENGTH_SHORT).show();
        }





        //AÑADIR A UN FICHERO LO DEL ARRAYLIST<STRING> comidas PARA SABER QUE ES LO QUE VA PIDIENDO EL CLIENTE

        try {
            fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_APPEND));
            for (int z=0;z<comidas.size();z++) {
                if (comidas.get(z).equals("Pizza Margarita")){ precio= 10; }
                else if (comidas.get(z).equals("Pizza Boloñesa")  || comidas.get(z).equals("Pizza Bolognese")){ precio=13.50; }
                else if (comidas.get(z).equals("Pizza Carbonara")){ precio=13.50; }
                else if (comidas.get(z).equals("Pizza 4 Quesos") || comidas.get(z).equals("Pizza 4 Cheese") || comidas.get(z).equals("Pizza 4 Formaggi")){ precio=12.50; }
                else if (comidas.get(z).equals("Pizza Napolitana") || comidas.get(z).equals("Pizza Neapolitan") || comidas.get(z).equals("Pizza Napoletana")){ precio=11.50; }
                else if (comidas.get(z).equals("Pizza Atun") || comidas.get(z).equals("Pizza Tuna") || comidas.get(z).equals("Pizza Tonno")){ precio=12.50; }
                else if (comidas.get(z).equals("Pizza Sorrento")){ precio=13; }
                else if (comidas.get(z).equals("Pizza Tropical") || comidas.get(z).equals("Pizza Tropicale")){ precio=13.50; }

                else if (comidas.get(z).equals("Ensalada Mixta") || comidas.get(z).equals("Mixed Salad") || comidas.get(z).equals("Insalata Mista")){ precio=7; }
                else if (comidas.get(z).equals("Ensalada Tropical") || comidas.get(z).equals("Tropical Salad") || comidas.get(z).equals("Insalata Tropicale")){ precio=9; }
                else if (comidas.get(z).equals("Ensalada de Pasta") || comidas.get(z).equals("Pasta Salad") || comidas.get(z).equals("Insalata di Pasta")){ precio=9; }
                else if (comidas.get(z).equals("Ensalada Campera") || comidas.get(z).equals("Country Salad") || comidas.get(z).equals("Insalata Country")){ precio=10; }
                else if (comidas.get(z).equals("Ensalada Capresse") || comidas.get(z).equals("Capresse Salad") || comidas.get(z).equals("Insalata Capresse")){ precio=9; }
                else if (comidas.get(z).equals("Ensalada Fruti di mare") || comidas.get(z).equals("Fruti di mare Salad") || comidas.get(z).equals("Insalata Fruti di Mare")){ precio=9.5; }

                else if (comidas.get(z).equals("Risotto de Setas") || comidas.get(z).equals("Mushroom Risotto") || comidas.get(z).equals("Risotto ai funghi")){ precio=10; }
                else if (comidas.get(z).equals("Risotto Marinero") || comidas.get(z).equals("Sailor Risotto") || comidas.get(z).equals("Risotto alla marinara")){ precio=10; }
                else if (comidas.get(z).equals("Risotto 4 Quesos") || comidas.get(z).equals("4 Cheese Risotto") || comidas.get(z).equals("Risotto ai 4 formaggi")){ precio=11; }

                else if (comidas.get(z).equals("Espagueti al Pesto") || comidas.get(z).equals("Spaghetti with Pesto") || comidas.get(z).equals("Spaghetti al Pesto")){ precio=9; }
                else if (comidas.get(z).equals("Espagueti Boloñesa") || comidas.get(z).equals("Spaghetti Bolognese") || comidas.get(z).equals("Spaghetti alla Bolognese")){ precio=9; }
                else if (comidas.get(z).equals("Espagueti Carbonara") || comidas.get(z).equals("Spaghetti Carbonara") || comidas.get(z).equals("Spaghetti alla Carbonara")){ precio=9; }
                else if (comidas.get(z).equals("Espagueti Siciliana") || comidas.get(z).equals("Sicilian Spaghetti") || comidas.get(z).equals("Spaghetti Siciliani")){ precio=10; }
                else if (comidas.get(z).equals("Espagueti con Gambas") || comidas.get(z).equals("Spaghetti with Prawns") || comidas.get(z).equals("Spaghetti ai Gamberi")){ precio=11; }

                else if (comidas.get(z).equals("Lasagna de Carne") || comidas.get(z).equals("Meat Lasagna") || comidas.get(z).equals("Lasagna di carne")){ precio=10.5; }
                else if (comidas.get(z).equals("Ravioli de Setas") || comidas.get(z).equals("Mushroom Ravioli") || comidas.get(z).equals("Ravioli ai funghi")){ precio=11; }
                else if (comidas.get(z).equals("Tagliatelle al Andrea")){ precio=10; }
                else if (comidas.get(z).equals("Carpaccio de Carne") || comidas.get(z).equals("Meat Carpaccio") || comidas.get(z).equals("Carpaccio di carne")){ precio=12; }
                else if (comidas.get(z).equals("Provolone a la Plancha") || comidas.get(z).equals("Grilled Provolone") || comidas.get(z).equals("Provolone alla griglia")){ precio=9; }


                fichero.write(comidas.get(z)+"; Precio: "+ precio +System.lineSeparator());
            }
            //System.out.println("Precio TOTAL: " + precioTotal);
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo el fichero");
        }



        Intent i2 = new Intent(ActivityComida.this,MainActivity.class);
        //Guardar lo que habia en MainActivity con el flag FLAG_ACTIVITY_REORDER_TO_FRONT
        i2.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(i2);


    }












}