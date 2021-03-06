package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements DialogoPostre.ListenerdelDialogo {

    String[] idiomas =  { "Castellano", "Ingles", "Italiano"};
    TextView bienvenido;
    RecyclerView lalista;
    ElAdaptadorRecycler eladaptador;
    Spinner spin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setSupportActionBar(findViewById(R.id.toolbar));

        bienvenido= (TextView) findViewById(R.id.textViewBienvenido);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String user = extras.getString("usuario");
            bienvenido.setText(bienvenido.getText().toString()+user);
            bienvenido.setTypeface(null, Typeface.BOLD_ITALIC);
        }


        lalista = findViewById(R.id.rv);


        int[] categorias= {R.drawable.pizza, R.drawable.ensalada, R.drawable.arrozz,R.drawable.esp, R.drawable.las};
        String[] nombres={"Pizzas", "Ensaladas", "Arroces", "Espagueti", "Especialidad"};
        eladaptador = new ElAdaptadorRecycler(nombres,categorias);
        lalista.setAdapter(eladaptador);


        LinearLayoutManager elLayoutLineal= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        lalista.setLayoutManager(elLayoutLineal);

        /*
        //GRID LAYOUT
        GridLayoutManager elLayoutRejillaIgual= new GridLayoutManager(this,3,GridLayoutManager.VERTICAL,false);
        lalista.setLayoutManager(elLayoutRejillaIgual);


         */




        //SPINNER IDIOMAS
         spin= (Spinner) findViewById(R.id.idiomas);

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
                System.out.println("Preferencias");
                Intent intentPreferencias = new Intent(MainActivity.this,ActivityPreferencias.class);
                startActivity(intentPreferencias);


                break;

            }
        }
        return super.onOptionsItemSelected(item);
    }

}