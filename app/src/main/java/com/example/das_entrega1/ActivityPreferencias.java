package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

public class ActivityPreferencias extends AppCompatActivity {

    private String comidaPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //IDIOMA

        setContentView(R.layout.activity_preferencias);

        //Obtenemos las preferencias
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        comidaPref = prefs.getString("comidapref","Pizza");
    }


    public void onClickGuardar(View v){
        //Al clickar guardar se establecen las nuevas preferencias y vamos a MainActivity
        Intent i = new Intent(this,MainActivity.class);
        //recogemos el usuario desde MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String user = extras.getString("usuario");
            i.putExtra("usuario",user);
        }
        i.putExtra("comidaPref",comidaPref);
        //startActivityForResult(i,123);  //Se definen códigos para distinguir distinta llamadas
        startActivity(i);


    }

}