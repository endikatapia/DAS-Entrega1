package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import java.util.Locale;

public class ActivityPreferencias extends AppCompatActivity {

    private String comidaPref;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //IDIOMA
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

        setContentView(R.layout.activity_preferencias);

        //Obtenemos las preferencias
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
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
        startActivity(i);


    }

}