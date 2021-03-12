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

        //establecer idioma
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String idioma = prefs.getString("idiomapref", "es");
        //establecer idioma
        Locale nlocale = new Locale(idioma);
        Locale.setDefault(nlocale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(nlocale);
        configuration.setLayoutDirection(nlocale);

        Context context = getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());

        setContentView(R.layout.activity_main);


        setSupportActionBar(findViewById(R.id.toolbar));

        bienvenido= findViewById(R.id.textViewBienvenido);
        userr = findViewById(R.id.userr);


        //cuando rotas se mantiene el nombre
        if (savedInstanceState != null) {
            user = savedInstanceState.getString("usuario");
            System.out.println("Usuario: " + user);
            userr.setText(user);
            userr.setTypeface(null, Typeface.BOLD);
        }else{//tratamos el nombre de usuario que viene de la otra aplicacion
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
                user = extras.getString("usuario");
                userr.setText(user);
                userr.setTypeface(null, Typeface.BOLD);
            }
        }


        lalista = findViewById(R.id.rv);


        String piz = getString(R.string.pizzas);
        String ens = getString(R.string.ensaladas);
        String ar = getString(R.string.arroces);
        String espa = getString(R.string.espaguetis);
        String espec = getString(R.string.especialidad);
        String[] nombres={piz, ens, ar, espa, espec};

        //Con preferencias
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //String comidaPref = prefs.getString("comidapref", "Pizza");
        if (prefs.contains("comidapref")) {
            comidaPref = prefs.getString("comidapref", null);

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
                //Si no tiene ninguna preferencia se pondran toas con fondo normal
                categorias = new int[]{R.drawable.pizza, R.drawable.ensalada, R.drawable.arrozz, R.drawable.esp, R.drawable.las};
                eladaptador = new ElAdaptadorRecycler(nombres, categorias);
                lalista.setAdapter(eladaptador);

        }



        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        lalista.setLayoutManager(elLayoutLineal);

        /*
        //GRID LAYOUT
        GridLayoutManager elLayoutRejillaIgual= new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutRejillaIgual);
       */


    } //final onCreate



    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        user  = userr.getText().toString();
        outState.putString("usuario", user);


/*
        String localizacion = getResources().getConfiguration().locale.toLanguageTag();
        System.out.println("LOC" + localizacion);
        outState.putString("localizacion",localizacion);

 */



    }

    /*
    //Se ejecuta al volver de la otra actividad
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TRATAR EL RESULTADO:
        if (requestCode == 123 && resultCode == RESULT_OK) {
            String user = data.getStringExtra("usuario");
            bienvenido.setText(bienvenido.getText().toString()+user);
            bienvenido.setTypeface(null, Typeface.BOLD_ITALIC);
        }
    }

     */


    //AL HACER CLICK EN CONTINUAR PEDIDO
    public void onClickContinuar(View v){
        System.out.println("Pulsado CONTINUAR CON EL PEDIDO");
        DialogFragment df = new DialogoPostre();
        df.show(getSupportFragmentManager(),"postre");
    }

    public void alpulsarSi(){
        Intent i2 = new Intent(MainActivity.this,ActivityPostre.class);
        startActivity(i2);

    }



    //MENU DE TOOLBAR CON PREFERENCIAS
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.opcion1: {
                System.out.println("Ajustes");
                Intent intentPreferencias = new Intent(MainActivity.this,ActivityPreferencias.class);
                intentPreferencias.putExtra("usuario",user);
                startActivity(intentPreferencias);


                break;

            }
            case R.id.opcion2: {
                //INTENT IMPLICITO --> ABRE EL NAVEGADOR para mas informacion sobre la comida italiana
                System.out.println("Información sobre comida Italiana");
                Intent intentInfo = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.thefork.com/es/gastronomia-italia/"));
                startActivity(intentInfo);


                break;

            }
        }
        return super.onOptionsItemSelected(item);
    }


    //al pulsar atras que se minimize
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }





}