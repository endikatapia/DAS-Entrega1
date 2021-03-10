package com.example.das_entrega1;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.DialogFragment;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

public class ActivityPostre extends AppCompatActivity implements FragmentLVMultipleChoice.listenerDelFragment,DialogoFinal.ListenerdelDialogo {

    ListView listView;
    ArrayAdapter eladaptador;
    ArrayList<String> postres = new ArrayList<>();
    OutputStreamWriter fichero;
    BufferedReader ficherointerno;
    private String[] partesPlato;
    double precio;
    miBD gestorDB;
    Intent intentVerPedido;
    Button botonFinalizar;

    String[] datosPostre={"Profiteroles", "Tarta de queso", "Tiramisú", "Panna cotta"};
    int[] comidaPostre={R.drawable.profiteroles,R.drawable.tartaqueso,R.drawable.tiramisu,R.drawable.pannacotta};
    String[] ingredientesPostre={"leche, mantequilla, harina, huevos, limón, canela, azúcar","galletas, nata, azucar, queso, mermelada","queso mascarpone, yemas, azúcar glass, cacao en polvo, café fuerte","nata, azúcar, gelatina, vainilla, canela"};
    double[] preciosPostre = {5,4,6.5,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postre);
        listView=findViewById(R.id.lv);

        botonFinalizar = (Button) findViewById(R.id.buttonFinalizar);
        //para guardar en la BD el pedido
        gestorDB = new miBD (this, "Pedidos", null, 1);
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


    //BOTON
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onClickFinalizar(View v){
        System.out.println("Pulsado FINALIZAR PEDIDO");
        DialogFragment df = new DialogoFinal();
        df.show(getSupportFragmentManager(),"final");

    }


    //DIALOG
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void alpulsarFinalizar() {
        //DESABILITAR BOTON tanto horizontal como vertical
        botonFinalizar.setClickable(false);


        //PRIMERO GUARDAR EN EL FICHERO LOS POSTRES QUE ESTAN SELECCIONADOS
        //Coger los valores que se han seleccionado de las listView
        this.guardarEnElFichero();


        //GUARDAR EN LA DB EL INT PK AUTOINCREMENto idPedido, STRING DE ELEMENTOS PEDIDOS, double PRECIO TOTAL
        this.guardarEnLaBBDD();

        //MIRAR LO DEL SALDO DEL CLIENTE??????



        //LANZAR LA NOTIFICACION DE QUE HA ACABADO EL PEDIDO Y DAR OPCION DE VOLVER A LA CARTA O VER EL PEDIDO
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

        //fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_PRIVATE)); --> al darle a realizar otro pedido
        try {
            //al darle a finalizar el fichero se vacia
            fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_PRIVATE));
            fichero.close();
        }
        catch  (IOException e) {
            System.out.println("Error");
        }



        //INTENT PARA VER EL PEDIDO
        //La otra opcion ver ultimo pedido --> recorrer desde la BD pedidos y mostralo en una nueva actividad
        //intentVolverCarta.putExtra("id",2);
        PendingIntent intentEnNot2 = PendingIntent.getActivity(this, 1, intentVerPedido, PendingIntent.FLAG_UPDATE_CURRENT);



        //configurar notificacion
        elBuilder.setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.logorestaurante))
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle("Ristorante Endika")
                .setContentText("HAS FINALIZADO TU PEDIDO")
                .setSubText("Pedido finalizado")
                .setVibrate(new long[]{0, 1000, 500, 1000})
                .addAction(android.R.drawable.ic_input_add,"Ver tu pedido", intentEnNot2)
                .setAutoCancel(true); //cancelar la notificacion al dar click


        //lanzar notificacion
        elManager.notify(1, elBuilder.build());




    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void guardarEnLaBBDD() {
        double precioTotal=0;
        ArrayList<String> elementosPedidosConRepetidos = new ArrayList<>();

        try {
            //abrimos el fichero para leerlo
             ficherointerno = new BufferedReader(new InputStreamReader(openFileInput("ficheroPedido.txt")));

            String linea;
            while ((linea = ficherointerno.readLine()) != null) {

                //leemos las lineas del fichero --> Panna cotta; Precio: 6.0
                System.out.println("LINEA EN EL FICHERO: " + linea);
                //partimos la linea en 2 cachos partiendo de ;
                partesPlato=linea.split(";");
                elementosPedidosConRepetidos.add(partesPlato[0]);
                //nos quedamos con la parte de Precio: 6.0 y la volvemos a partir en el " ".
                String[] precioArray = partesPlato[1].split(" ");
                //nos quedamos con la parte del numero (6.0)
                String precioInd = precioArray[2];
                System.out.println("precio parte numero: " + precioInd);
                //lo parseamos a Double
                double precioIndv = Double.parseDouble(precioInd);
                //se va sumando el precio total
                precioTotal = precioTotal+precioIndv;
            }


            //guardar elementosPedidos sin repetidos y precio total en la BD Pedidos
            //Para que no aparezcan repetidos usar LinkedHashSet, ya que, solo nos interesa
            //guardar en la BD los platos diferentes que ha pedido sin importar la cantidad.
            //En el precio total si que se sumaran todos los elementos (aunque esten repetidos)
            Set<String> s = new LinkedHashSet<String>(elementosPedidosConRepetidos);
            String elementosPedidosSinRepeticion = String.join(", ", s);;

            System.out.println("Elementos pedidos:" + elementosPedidosSinRepeticion);
            System.out.println("PRECIO TOTAL: " +precioTotal);

            intentVerPedido = new Intent(ActivityPostre.this,ActivityPedido.class);
            intentVerPedido.putExtra("elementos",elementosPedidosSinRepeticion);
            intentVerPedido.putExtra("precio",precioTotal);

            //guardarlos en la BD
            gestorDB.guardarPedido(elementosPedidosSinRepeticion,precioTotal);

        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void guardarEnElFichero(){
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
        }
        String postres_s = String.join(", ", postres);

        //si el string contiene al menos una letra, es decir, que se haya pedido un plato
        if ( ! postres_s.isEmpty()) {
            Toast.makeText(this,"Has añadido a tu pedido: "+postres_s,Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"No has añadido ningún postre",Toast.LENGTH_SHORT).show();
        }

        try {
            fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_APPEND));
            for (int z=0;z<postres.size();z++) {
                if (postres.get(z).equals("Profiteroles")){ precio= 5; }
                else if (postres.get(z).equals("Tarta de queso")){ precio=4; }
                else if (postres.get(z).equals("Tiramisú")){ precio=6.50; }
                else if (postres.get(z).equals("Panna cotta")){ precio=6; }


                fichero.write(postres.get(z)+"; Precio: "+ precio +System.lineSeparator());
            }
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo el fichero");
        }

    }


}