package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStreamWriter;

public class ActivityLogin extends AppCompatActivity implements  DialogoLogin.ListenerdelDialogo {



    ImageView logo;
    EditText usuario;
    EditText contraseña;
    miBD gestorDB;
    OutputStreamWriter fichero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //se crea el fichero donde se van a ir guardando los platos pedidos en modo privado
        //es decir, cada vez que el usuario entre se le creara de nuevo.
        try {
            fichero = new OutputStreamWriter(openFileOutput("ficheroPedido.txt", Context.MODE_PRIVATE));
            fichero.close();
        } catch (IOException e) {
            System.out.println("Error escribiendo el fichero");
        }


        //Buscar elementos del layout
        logo=(ImageView) findViewById(R.id.logo);
        usuario = (EditText) findViewById(R.id.usuario);
        //contraseña con puntos android:inputType="textPassword"
        contraseña = (EditText) findViewById(R.id.contraseña);

        //establecer a la ImageView el logo del restaurante encontrado en R.drawable
        logo.setImageResource(R.drawable.logorestaurante);

        //obtener un objeto de tipo SQLiteDatabase desde ActivityLogin
        gestorDB = new miBD (this, "Usuarios", null, 1);

    }

    public void onClickLogin(View v){
        System.out.println("Clickado LOGIN");
        //Comprobar si el usuario esta en la BBDD
        String user = usuario.getText().toString();
        String password = contraseña.getText().toString();
        //miramos si esta en la BBDD
        boolean estaEnBD = gestorDB.esta(user);
        boolean contraseñaCorrecta = gestorDB.contraseñaCorrecta(user,password);
        //Si no esta en la BBDD le preguntamos si lo quiere registrar en la BBDD mediante un dialogo
        if (!estaEnBD) {
            System.out.println("El usuario "+ user+ " no esta en la BBDD");
            //el usuario y la contraseña deben contener al menos un caracter
            if (user.matches("") || password.matches("") ) {
                System.out.println("La Contraseña o el Usuario debe tener al menos 1 caracter");
                Toast.makeText(ActivityLogin.this,"El usuario o la Contraseña deben tener al menos 1 caracter",Toast.LENGTH_SHORT).show();
            }
            else{
                DialogFragment df = new DialogoLogin(user);
                df.show(getSupportFragmentManager(), "etiqueta1");
            }
        }
        //Si existe, miramos a ver si ha metido bien la contraseña
        //Si la ha metido mal lanzamos un Toast avisandole de que ha introducido mal la contraseña
        else if (estaEnBD && !contraseñaCorrecta){
            System.out.println("El usuario "+ user +" existe pero la contraseña es incorrecta");
            Toast.makeText(ActivityLogin.this,"El ususario "+ usuario.getText().toString()+" existe, PERO la Contraseña es incorrecta",Toast.LENGTH_SHORT).show();
        }
        //Sino intent a MainActivity (a la carta)
        else if (estaEnBD && contraseñaCorrecta) {
            System.out.println("El usuario "+user+ " existe Y la contraseña es correcta --> MAIN ACTIVITY");
            Intent intentMainActivity = new Intent(ActivityLogin.this,MainActivity.class);
            //guardamos el usuario
            intentMainActivity.putExtra("usuario",user);
            startActivity(intentMainActivity);

        }




    }

    @Override
    public void alpulsarSi(){
        //insertar al usuario en la BBDD pasandole de parametros el usuario y la contraseña
        gestorDB.insertarEnLaBBDD(usuario.getText().toString(),contraseña.getText().toString());
        Toast.makeText(ActivityLogin.this,usuario.getText().toString()+" añadido",Toast.LENGTH_SHORT).show();
    }




}