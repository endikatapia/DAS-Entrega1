package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class ActivityPostre extends AppCompatActivity implements FragmentLVMultipleChoice.listenerDelFragment,DialogoFinal.ListenerdelDialogo {

    ListView listView;
    ArrayAdapter eladaptador;
    ArrayList<String> postres = new ArrayList<>();
    OutputStreamWriter fichero;

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
        System.out.println("Pulsado FINALIZAR PEDIDO");
        DialogFragment df = new DialogoFinal();
        df.show(getSupportFragmentManager(),"final");


        //Coger los valores que se han seleccionado de las listView
        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
        if (checkedItems != null) {
            for (int i=0; i<checkedItems.size(); i++) {
                if (checkedItems.valueAt(i)) {
                    String item = listView.getAdapter().getItem(checkedItems.keyAt(i)).toString();
                    Log.i("TAG",item + " was selected");
                    //añadirlos a el arraylist
                    postres.add(item);
                }
            }
        }

        //ver los platos seleccionados del arraylist
        for (int z=0;z<postres.size();z++) {
            System.out.println("POSTRE: " + postres.get(z));
            Toast.makeText(this,"Has añadido a tu pedido: "+postres.get(z),Toast.LENGTH_SHORT).show();
        }

        try {
            fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_APPEND));
            for (int z=0;z<postres.size();z++) {
                fichero.write(postres.get(z)+System.lineSeparator());
            }
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo el fichero");
        }

    }


    public void alpulsarFinalizar() {
        //LANZAR LA NOTIFICACION DE QUE HA ACABADO EL PEDIDO Y DAR OPCION DE VOLVER A LA CARTA O VER EL PEDIDO
        //fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_PRIVATE));
        NotificationManager elManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder elBuilder = new NotificationCompat.Builder(this, "IdCanal");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel elCanal = new NotificationChannel("IdCanal", "NombreCanal",
                    NotificationManager.IMPORTANCE_DEFAULT);
            elManager.createNotificationChannel(elCanal);


            elCanal.setDescription("Descripción del canal");
            elCanal.enableLights(true);
            elCanal.setLightColor(Color.RED);
            elCanal.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            elCanal.enableVibration(true);
        }

        /*
        Intent intent = new Intent(MainActivity.this,SegundaActividad.class);
        intent.putExtra("id",1);
        PendingIntent intentEnNot = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);


         */

        //configurar notificacion
        elBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logorestaurante))
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Ristorante Endika")
                .setContentText("HAS FINALIZADO TU PEDIDO")
                .setSubText("Pedido finalizado")
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .setAutoCancel(true);
                //.addAction(android.R.drawable.ic_input_add,"Añadir",intentEnNot);
        //.setContentIntent(intentEnNot);

        //lanzar notificacion
        elManager.notify(1, elBuilder.build());

    }
}