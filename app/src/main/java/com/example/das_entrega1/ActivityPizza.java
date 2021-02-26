package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivityPizza extends AppCompatActivity {

    ListView listView;
    AdaptadorListView adaptador;
    TextView nombreCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {




        int[] equipos = {R.drawable.arroz, R.drawable.ensalada, R.drawable.espagueti,R.drawable.especialidad};
        String[] nombres = {"Pizza margarita", "Pizza bolognesa", "Pizza carbonara", "Pizza 4 Quesos"};

        int[] equipos2 = {R.drawable.arroz, R.drawable.arroz, R.drawable.arroz, R.drawable.arroz};
        String[] nombres2 = {"Ensalada mixta", "Ensalada tropical", "Ensalada de pasta","Ensalada campera"};

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza);

        listView = (ListView) findViewById(R.id.lvPizza);
        nombreCategoria = (TextView) findViewById(R.id.nombreCategoria);

        Bundle extras= getIntent().getExtras();
        if (extras != null){
            int categoria = extras.getInt("categoria");
            if (categoria==0){
                nombreCategoria.setText("Pizzas");
                adaptador = new AdaptadorListView(getApplicationContext(), nombres, equipos);
                listView.setAdapter(adaptador);
            }
            else if (categoria==1){
                nombreCategoria.setText("Ensaladas");
                adaptador = new AdaptadorListView(getApplicationContext(), nombres2, equipos2);
                listView.setAdapter(adaptador);
            }

        }








        /*
        String texto="Los elegidos son: ";
        SparseBooleanArray elegidos= listView.getCheckedItemPositions();
        for(int i=0;i<elegidos.size();i++){
            if(elegidos.valueAt(i)==true){
                String s = ((TextView) listView.getChildAt(i)).getText().toString();
                texto = texto+s+" ";
            }
        }
        Log.d("etiqueta", texto);
        //System.out.println(texto);


         */




        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Log.d("etiqueta", ((TextView)view.findViewById(R.id.textView2)).getText().toString());
                Intent i = new Intent(MainActivity.this, Activity2.class);
                startActivityForResult(i,123);  //Se definen códigos para distinguir distinta llamadas
                //startActivity(i);
            }
        });




    }



    //Se ejecuta al volver de la otra actividad
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TRATAR EL RESULTADO:
        if (requestCode == 123 && resultCode == RESULT_OK) {
            String color = data.getStringExtra("color");
            listView = (ListView)findViewById(R.id.lista);
            listView.setBackgroundColor(Color.parseColor("#"+ color));
            adaptador.setBackgroundColor(Color.parseColor("#"+ color));
        }
    }
    */

    }


    public void onClickPedido(View v){
        DialogFragment df = new DialogoPostre();
        df.show(getSupportFragmentManager(),"postre");


    }

}