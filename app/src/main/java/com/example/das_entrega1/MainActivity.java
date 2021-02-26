package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast pruebaCommit = Toast.makeText(this,"Prueba Commit",Toast.LENGTH_SHORT);
    }
}