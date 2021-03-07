package com.example.das_entrega1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ActivityPedido extends AppCompatActivity {

    TextView tvPedido;
    TextView tvPrecio;
    miBD gestorDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido);
        tvPedido = (TextView) findViewById(R.id.textViewPedido);
        tvPrecio = (TextView) findViewById(R.id.textViewPrecio);

        //para hacer el select en la BD Pedidos
        gestorDB = new miBD (this, "Pedidos", null, 1);

        Bundle extras= getIntent().getExtras();
        if (extras != null){
            String elementos = extras.getString("elementos");
            tvPedido.setText(elementos);

            double precioT = extras.getDouble("precio");
            tvPrecio.setText(tvPrecio.getText()+" " + precioT + "€");
            }
    }



}