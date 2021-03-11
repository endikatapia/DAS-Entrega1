package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ActivityPedido extends AppCompatActivity {

    TextView tvPrecio;
    miBD gestorDB;
    private String[] partesPlato;
    private double precio;
    ArrayAdapter eladaptador;
    ListView lv2;
    ArrayList<Double> precios = new ArrayList<>();

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

        setContentView(R.layout.activity_pedido);



        lv2 = findViewById(R.id.lv2);
        tvPrecio = (TextView) findViewById(R.id.textViewPrecio);

        //para hacer el select en la BD Pedidos
        gestorDB = new miBD (this, "Pedidos", null, 1);

        Bundle extras= getIntent().getExtras();
        if (extras != null){

            //para cerrar la notificacion al pulsar ver pedido
            int id=extras.getInt("id");
            NotificationManager elManager = (NotificationManager)
                    getSystemService(Context.NOTIFICATION_SERVICE);
            elManager.cancel(id);

            String elementos = extras.getString("elementos");
            //partimos el string en cada plato
            partesPlato=elementos.split(", ");

            for (int i=0; i<partesPlato.length;i++){
                System.out.println("Plato:" + partesPlato[i]);
                if (partesPlato[i].equals("Pizza Margarita")){ precio= 10; }
                else if (partesPlato[i].equals("Pizza Boloñesa") || partesPlato[i].equals("Pizza Bolognese")){ precio=13.50; }
                else if (partesPlato[i].equals("Pizza Carbonara")){ precio=13.50; }
                else if (partesPlato[i].equals("Pizza 4 Quesos") || partesPlato[i].equals("Pizza 4 Cheese") || partesPlato[i].equals("Pizza 4 Formaggi")){ precio=12.50; }
                else if (partesPlato[i].equals("Pizza Napolitana") || partesPlato[i].equals("Pizza Neapolitan") || partesPlato[i].equals("Pizza Napoletana")){ precio=11.50; }
                else if (partesPlato[i].equals("Pizza Atun") || partesPlato[i].equals("Pizza Tuna") || partesPlato[i].equals("Pizza Tonno")){ precio=12.50; }
                else if (partesPlato[i].equals("Pizza Sorrento")){ precio=13; }
                else if (partesPlato[i].equals("Pizza Tropical") || partesPlato[i].equals("Pizza Tropicale")){ precio=13.50; }

                else if (partesPlato[i].equals("Ensalada Mixta") || partesPlato[i].equals("Mixed Salad") || partesPlato[i].equals("Insalata Mista")){ precio=7; }
                else if (partesPlato[i].equals("Ensalada Tropical") || partesPlato[i].equals("Tropical Salad") || partesPlato[i].equals("Insalata Tropicale")){ precio=9; }
                else if (partesPlato[i].equals("Ensalada de Pasta") || partesPlato[i].equals("Pasta Salad") || partesPlato[i].equals("Insalata di Pasta")){ precio=9; }
                else if (partesPlato[i].equals("Ensalada Campera") || partesPlato[i].equals("Country Salad") || partesPlato[i].equals("Insalata Country")){ precio=10; }
                else if (partesPlato[i].equals("Ensalada Capresse") || partesPlato[i].equals("Capresse Salad") || partesPlato[i].equals("Insalata Capresse")){ precio=9; }
                else if (partesPlato[i].equals("Ensalada Fruti di mare") || partesPlato[i].equals("Fruti di mare Salad") || partesPlato[i].equals("Insalata Fruti di Mare")){ precio=9.5; }

                else if (partesPlato[i].equals("Risotto de Setas") || partesPlato[i].equals("Mushroom Risotto") || partesPlato[i].equals("Risotto ai funghi")){ precio=10; }
                else if (partesPlato[i].equals("Risotto Marinero") || partesPlato[i].equals("Sailor Risotto") || partesPlato[i].equals("Risotto alla marinara")){ precio=10; }
                else if (partesPlato[i].equals("Risotto 4 Quesos") || partesPlato[i].equals("4 Cheese Risotto") || partesPlato[i].equals("Risotto ai 4 formaggi")){ precio=11; }

                else if (partesPlato[i].equals("Espagueti al Pesto") || partesPlato[i].equals("Spaghetti with Pesto") || partesPlato[i].equals("Spaghetti al Pesto")){ precio=9; }
                else if (partesPlato[i].equals("Espagueti Boloñesa") || partesPlato[i].equals("Spaghetti Bolognese") || partesPlato[i].equals("Spaghetti alla Bolognese")){ precio=9; }
                else if (partesPlato[i].equals("Espagueti Carbonara") || partesPlato[i].equals("Spaghetti Carbonara") || partesPlato[i].equals("Spaghetti alla Carbonara")){ precio=9; }
                else if (partesPlato[i].equals("Espagueti Siciliana") || partesPlato[i].equals("Sicilian Spaghetti") || partesPlato[i].equals("Spaghetti Siciliani")){ precio=10; }
                else if (partesPlato[i].equals("Espagueti con Gambas") || partesPlato[i].equals("Spaghetti with Prawns") || partesPlato[i].equals("Spaghetti ai Gamberi")){ precio=11; }

                else if (partesPlato[i].equals("Lasagna de Carne") || partesPlato[i].equals("Meat Lasagna") || partesPlato[i].equals("Lasagna di carne")){ precio=10.5; }
                else if (partesPlato[i].equals("Ravioli de Setas") || partesPlato[i].equals("Mushroom Ravioli") || partesPlato[i].equals("Ravioli ai funghi")){ precio=11; }
                else if (partesPlato[i].equals("Tagliatelle al Andrea")){ precio=10; }
                else if (partesPlato[i].equals("Carpaccio de Carne") || partesPlato[i].equals("Meat Carpaccio") || partesPlato[i].equals("Carpaccio di carne")){ precio=12; }
                else if (partesPlato[i].equals("Provolone a la Plancha") || partesPlato[i].equals("Grilled Provolone") || partesPlato[i].equals("Provolone alla griglia")){ precio=9; }

                else if (partesPlato[i].equals("Profiteroles")){ precio= 5; }
                else if (partesPlato[i].equals("Tarta de queso") || partesPlato[i].equals("Cheesecake")){ precio=4; }
                else if (partesPlato[i].equals("Tiramisú") || partesPlato[i].equals("Tiramisu")){ precio=6.50; }
                else if (partesPlato[i].equals("Panna cotta")){ precio=6;}
                precios.add(precio);
            }

            eladaptador= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_2,android.R.id.text1,partesPlato){
                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    View vista= super.getView(position, convertView, parent);
                    TextView lineaprincipal=(TextView) vista.findViewById(android.R.id.text1);
                    TextView lineasecundaria=(TextView) vista.findViewById(android.R.id.text2);
                    //linea principal poner el plato
                    lineaprincipal.setText(partesPlato[position]);
                    //secundaria el precio
                    lineasecundaria.setText("Precio: " + String.valueOf(precios.get(position)) + "€");

                    return vista;
                }
            };
            lv2.setAdapter(eladaptador);

            double precioT = extras.getDouble("precio");
            tvPrecio.setText(tvPrecio.getText()+": " + precioT + "€");
            }
    }

    public void onClickIrCarta(View v){
        Intent intentVolverCarta = new Intent(ActivityPedido.this,MainActivity.class);
        //Guardar lo que habia en MainActivity con el flag FLAG_ACTIVITY_REORDER_TO_FRONT
        intentVolverCarta.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intentVolverCarta);
    }

    //al pulsar atras que se minimize
    @Override
    public void onBackPressed() {
        this.moveTaskToBack(true);
    }



}