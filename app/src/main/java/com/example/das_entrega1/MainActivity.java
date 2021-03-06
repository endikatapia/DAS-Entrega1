package com.example.das_entrega1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements DialogoPostre.ListenerdelDialogo {

    TextView bienvenido;
    RecyclerView lalista;
    ElAdaptadorRecycler eladaptador;
    int[] categorias;
    String comidaPref;
    String user;
    TextView userr;
    SharedPreferences prefs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //establecer el idioma que había guardado en las preferencias --> por defecto: castellano
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String idioma = prefs.getString("idiomapref", "es");

        Locale nlocale = new Locale(idioma);
        Locale.setDefault(nlocale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(nlocale);
        configuration.setLayoutDirection(nlocale);

        Context context = getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);

        //poner la toolbar personalizada
        setSupportActionBar(findViewById(R.id.toolbar));

        //TextViews de bienvenida al usuario
        bienvenido= findViewById(R.id.textViewBienvenido);
        userr = findViewById(R.id.userr);


        //cuando rotas, minimizas o en general, se hace onDestroy, se debe mantener el nombre del usuario
        //Para ello, lo recuperamos con savedInstanceState.
        if (savedInstanceState != null) {
            user = savedInstanceState.getString("usuario");
            System.out.println("Usuario: " + user);
            userr.setText(user);
            userr.setTypeface(null, Typeface.BOLD); //letra negrita para el nombre
        }
        //tratamos el nombre de usuario que viene de la actividad ActivityLogin
        //en este caso solo entrara aqui la primera vez que se cree esta actividad
        else{
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                user = extras.getString("usuario");
                userr.setText(user);
                userr.setTypeface(null, Typeface.BOLD); //letra negrita para el nombre
            }
        }
        //en ambos casos guardamos ese nombre y lo establecemos en el TextView userr

        //Se recoge el elemento de la interfaz gráfica
        //la lista (recycledView + cardView) que va a contener las categorias de comida
        lalista = findViewById(R.id.rv);


        //guardar en un array los nombres de las categorias
        String piz = getString(R.string.pizzas);
        String ens = getString(R.string.ensaladas);
        String ar = getString(R.string.arroces);
        String espa = getString(R.string.espaguetis);
        String espec = getString(R.string.especialidad);
        String[] nombres={piz, ens, ar, espa, espec};

        //Con las preferencias recuperamos la comida favorita del usuario
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //Por defecto no tendra ninguna marcada, por lo que le asignaremos null
        if (prefs.contains("comidapref")) {
            comidaPref = prefs.getString("comidapref", null);
            //Se cargara laLista en funcion de la comida favorita del usuario
            //Se crea el adaptador con los datos a mostrar y se asigna al RecyclerView
            if (comidaPref.equals("Pizza")) {
                //si la preferencia del cliente son las pizzas la imagen del cardView pizza se pondra con fondo verde
                categorias = new int[]{R.drawable.pizzaprefs, R.drawable.ensalada, R.drawable.arrozz, R.drawable.esp, R.drawable.las};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);
            } else if (comidaPref.equals("Ensalada")) {
                //si la preferencia del cliente son las ensaladas la imagen del cardView ensalada se pondra con fondo verde
                categorias = new int[]{R.drawable.pizza, R.drawable.ensaladaprefs, R.drawable.arrozz, R.drawable.esp, R.drawable.las};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);
            } else if (comidaPref.equals("Arroz")) {
                //si la preferencia del cliente es el arroz la imagen del cardView arroz se pondra con fondo verde
                categorias = new int[]{R.drawable.pizza, R.drawable.ensalada, R.drawable.arrozprefs, R.drawable.esp, R.drawable.las};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);
            } else if (comidaPref.equals("Espagueti")) {
                //si la preferencia del cliente son las espagueti la imagen del cardView espagueti se pondra con fondo verde
                categorias = new int[]{R.drawable.pizza, R.drawable.ensalada, R.drawable.arrozz, R.drawable.espprefs, R.drawable.las};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);
            } else if (comidaPref.equals("Especialidad")) {
                //si la preferencia del cliente es la especialidad la imagen del cardView especialidad se pondra con fondo verde
                categorias = new int[]{R.drawable.pizza, R.drawable.ensalada, R.drawable.arrozz, R.drawable.esp, R.drawable.lasprefs};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);
            }
        }
        else {//if (comidaPref==null){ //Sin preferencias
                //Si no tiene ninguna preferencia se pondran todas con fondo normal
                categorias = new int[]{R.drawable.pizza, R.drawable.ensalada, R.drawable.arrozz, R.drawable.esp, R.drawable.las};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);

        }

        //se utiliza un LinearLayout horizontal con scroll donde los elementos se muestran de forma lineal horizontal.
        //setLayoutManager establece lalista con los cardViews
        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        lalista.setLayoutManager(elLayoutLineal);


    } //final onCreate



    //guardar el usuario en el Bundle cuando se haga onDestroy
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        user  = userr.getText().toString();
        outState.putString("usuario", user);

    }


    //AL HACER CLICK EN CONTINUAR PEDIDO (boton) --> enseñar Dialogo DiologoPostre
    public void onClickContinuar(View v){
        System.out.println("Pulsado CONTINUAR CON EL PEDIDO");
        DialogFragment df = new DialogoPostre();
        df.show(getSupportFragmentManager(),"postre");
    }

    //al pulsar si en el dialogo ir a DiologoPostre
    public void alpulsarSi(){
        Intent i2 = new Intent(MainActivity.this,ActivityPostre.class);
        startActivity(i2);
    }


    //MENU DE TOOLBAR CON PREFERENCIAS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu); //inflar el menu definido en menu.xml
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            //primera opcion de ajustes, para establecer preferencias
            //entre ellas la comida favorita y el idioma preferido
            case R.id.opcion1: {
                System.out.println("Ajustes");
                Intent intentPreferencias = new Intent(MainActivity.this,ActivityPreferencias.class);
                intentPreferencias.putExtra("usuario",user); //le pasamos el usuario como extra a la actividad de las prefs
                startActivity(intentPreferencias);

                break;

            }
            //INTENT IMPLICITO --> ABRE EL NAVEGADOR para mas informacion sobre la comida italiana con Intent.ACTION_VIEW
            case R.id.opcion2: {
                System.out.println("Información sobre comida Italiana");
                Intent intentInfo = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.thefork.com/es/gastronomia-italia/"));
                startActivity(intentInfo);

                break;

            }
        }
        return super.onOptionsItemSelected(item);
    }


    //al pulsar el boton de atras la actividad se minimiza
    //Asi evitaremos incongruencias en la pila de actividades
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }


}