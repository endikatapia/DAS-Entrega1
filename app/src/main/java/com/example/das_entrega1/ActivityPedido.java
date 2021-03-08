package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivityPedido extends AppCompatActivity {

    TextView tvPedido;
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
        setContentView(R.layout.activity_pedido);
        //tvPedido = (TextView) findViewById(R.id.textViewPedido);
        lv2 = findViewById(R.id.lv2);
        tvPrecio = (TextView) findViewById(R.id.textViewPrecio);

        //para hacer el select en la BD Pedidos
        gestorDB = new miBD (this, "Pedidos", null, 1);

        Bundle extras= getIntent().getExtras();
        if (extras != null){
            //String elementos = extras.getString("elementos");
            //tvPedido.setText(elementos);

            String elementos = extras.getString("elementos");
            //partimos el string en cada plato
            partesPlato=elementos.split(", ");

            for (int i=0; i<partesPlato.length;i++){
                System.out.println("Plato:" + partesPlato[i]);
                if (partesPlato[i].equals("Pizza Margarita")){ precio= 10; }
                else if (partesPlato[i].equals("Pizza Boloñesa")){ precio=13.50; }
                else if (partesPlato[i].equals("Pizza Carbonara")){ precio=13.50; }
                else if (partesPlato[i].equals("Pizza 4 Quesos")){ precio=12.50; }
                else if (partesPlato[i].equals("Pizza Napolitana")){ precio=11.50; }
                else if (partesPlato[i].equals("Pizza Atun")){ precio=12.50; }
                else if (partesPlato[i].equals("Pizza Sorrento")){ precio=13; }
                else if (partesPlato[i].equals("Pizza Tropical")){ precio=13.50; }
                else if (partesPlato[i].equals("Ensalada Mixta")){ precio=7; }
                else if (partesPlato[i].equals("Ensalada Tropical")){ precio=9; }
                else if (partesPlato[i].equals("Ensalada de Pasta")){ precio=9; }
                else if (partesPlato[i].equals("Ensalada Campera")){ precio=10; }
                else if (partesPlato[i].equals("Ensalada Capresse")){ precio=9; }
                else if (partesPlato[i].equals("Ensalada Fruti di mare")){ precio=9.5; }
                else if (partesPlato[i].equals("Risotto de Setas")){ precio=10; }
                else if (partesPlato[i].equals("Risotto Marinero")){ precio=10; }
                else if (partesPlato[i].equals("Risotto 4 Quesos")){ precio=11; }
                else if (partesPlato[i].equals("Espagueti al Pesto")){ precio=9; }
                else if (partesPlato[i].equals("Espagueti Boloñesa")){ precio=9; }
                else if (partesPlato[i].equals("Espagueti Carbonara")){ precio=9; }
                else if (partesPlato[i].equals("Espagueti Siciliana")){ precio=10; }
                else if (partesPlato[i].equals("Espagueti con Gambas")){ precio=11; }
                else if (partesPlato[i].equals("Lasagna de Carne")){ precio=10.5; }
                else if (partesPlato[i].equals("Ravioli de Setas")){ precio=11; }
                else if (partesPlato[i].equals("Tagliatelle al Andrea")){ precio=10; }
                else if (partesPlato[i].equals("Carpaccio de Carne")){ precio=12; }
                else if (partesPlato[i].equals("Provolone a la Plancha")){ precio=9; }
                else if (partesPlato[i].equals("Profiteroles")){ precio= 5; }
                else if (partesPlato[i].equals("Tarta de queso")){ precio=4; }
                else if (partesPlato[i].equals("Tiramisú")){ precio=6.50; }
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



}