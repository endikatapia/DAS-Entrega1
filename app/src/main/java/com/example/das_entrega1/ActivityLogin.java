package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class ActivityLogin extends AppCompatActivity implements  DialogoLogin.ListenerdelDialogo {



    ImageView logo;
    EditText usuario;
    EditText contraseña;
    miBD gestorDB;
    OutputStreamWriter fichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //establecer el idioma que había guardado en las preferencias --> por defecto: castellano
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String idioma = prefs.getString("idiomapref", "es");

        Locale nlocale = new Locale(idioma);
        Locale.setDefault(nlocale);
        Configuration configuration = getBaseContext().getResources().getConfiguration();
        configuration.setLocale(nlocale);
        configuration.setLayoutDirection(nlocale);

        Context context = getBaseContext().createConfigurationContext(configuration);
        getBaseContext().getResources().updateConfiguration(configuration, context.getResources().getDisplayMetrics());


        setContentView(R.layout.activity_login);


        //se crea el fichero donde se van a ir guardando los platos pedidos en modo privado,
        //es decir, cada vez que el usuario entre se le creara el fichero de nuevo.
        try {
            fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_PRIVATE));
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo el fichero");
        }


        //Buscar elementos del layout
        logo=(ImageView) findViewById(R.id.logo);
        usuario = (EditText) findViewById(R.id.usuario);
        //contraseña con puntos ocultos: android:inputType="textPassword"
        contraseña = (EditText) findViewById(R.id.contraseña);

        //establecer a la ImageView el logo del restaurante encontrado en R.drawable
        logo.setImageResource(R.drawable.logorestaurante);

        //obtener un objeto de tipo SQLiteDatabase desde ActivityLogin
        gestorDB = new miBD (this, "Usuarios", null, 1);

    }

    public void onClickLogin(View v){
        System.out.println("Clickado LOGIN");

        String user = usuario.getText().toString();
        String password = contraseña.getText().toString();
        //Comprobar si el usuario esta en la BBDD y si mete la contraseña correcta implementados en la clase miBD
        boolean estaEnBD = gestorDB.esta(user);
        boolean contraseñaCorrecta = gestorDB.contraseñaCorrecta(user,password);
        //Si no esta en la BBDD le preguntamos si lo quiere registrar en la BBDD mediante el dialogo DialogoLogin
        if (!estaEnBD) {
            System.out.println("El usuario "+ user+ " no esta en la BBDD");
            //Si no ha tecleado nada en usuario o contraseña --> Toast (aviso)
            //el usuario y la contraseña deben contener al menos un caracter
            if (user.matches("") || password.matches("") ) {
                System.out.println("La Contraseña o el Usuario debe tener al menos 1 caracter");
                String almenos1caracter = getString(R.string.almenos1caracter);

                //TOAST PERSONALIZADO con layout_toast.xml
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toast_layout_root)); //inflamos la vista con el layout

                TextView text = (TextView) layout.findViewById(R.id.text);
                text.setText(almenos1caracter); // le indicamos el texto

                Toast toast = new Toast(this);
                toast.setDuration(Toast.LENGTH_SHORT); //duracion corta
                toast.setView(layout); //le establecemos el layout al Toast
                toast.show(); //lo enseñamos

            }
            else{ //si ha tecleado algo en los 2 campos --> DialogoLogin con contructor (String user)
                DialogFragment df = new DialogoLogin(user);
                df.show(getSupportFragmentManager(), "login");
            }
        }
        //Si existe, miramos a ver si ha metido bien la contraseña
        //Si la ha metido mal lanzamos un Toast avisandole de que ha introducido mal la contraseña
        else if (estaEnBD && !contraseñaCorrecta){
            System.out.println("El usuario "+ user +" existe pero la contraseña es incorrecta");
            String usudialogo = getString(R.string.usudialogo);
            String existePero = getString(R.string.existePero);

            //TOAST PERSONALIZADO con layout_toast.xml
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toast_layout_root));

            TextView text = (TextView) layout.findViewById(R.id.text);
            text.setText(usudialogo+ usuario.getText().toString()+ existePero);

            Toast toast = new Toast(this);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
        //Si ha introducido bien la contraseña --> intent a MainActivity (a la carta)
        else if (estaEnBD && contraseñaCorrecta) {
            System.out.println("El usuario "+user+ " existe Y la contraseña es correcta --> MAIN ACTIVITY");
            Intent intentMainActivity = new Intent(ActivityLogin.this,MainActivity.class);
            //guardamos el usuario para que salga en MainActivity
            intentMainActivity.putExtra("usuario",user);
            startActivity(intentMainActivity);

        }




    }

    //Cuando el usuario pulse si --> el cliente se añadira a la BBDD Y le avisara con un Toast
    //este metodo implementa al listener del DialogFragment DialogoLogin
    @Override
    public void alpulsarSi(){
        //insertar al usuario en la BBDD pasandole de parametros el usuario y la contraseña --> hecho en miBD
        gestorDB.insertarEnLaBBDD(usuario.getText().toString(),contraseña.getText().toString());
        String añad = getString(R.string.aña);
        //Toast.makeText(ActivityLogin.this,usuario.getText().toString()+añad,Toast.LENGTH_SHORT).show();

        //TOAST PERSONALIZADO con layout_toast.xml
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.layout_toast, (ViewGroup) findViewById(R.id.toast_layout_root));

        TextView text = (TextView) layout.findViewById(R.id.text);
        text.setText(usuario.getText().toString()+añad);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();

    }




}