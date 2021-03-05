package com.example.das_entrega1;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdaptadorListView extends BaseAdapter {


    private Context contexto;
    private LayoutInflater inflater;
    private String[] datos;
    private int[] imagenes;
    SparseBooleanArray mCheckStates;
    private ArrayList<String> clikados = new ArrayList<>();

    public AdaptadorListView(Context pcontext, String[] pdatos, int[] pimagenes) {
        contexto = pcontext;
        datos = pdatos;
        imagenes = pimagenes;
        mCheckStates = new SparseBooleanArray(datos.length);
        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return datos.length;
    }

    @Override
    public Object getItem(int i) {
        return datos[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view=inflater.inflate(R.layout.fila,null);
        TextView nombretv= (TextView) view.findViewById(R.id.textView2);
        ImageView img=(ImageView) view.findViewById(R.id.imageView);
        CheckBox chckBox = (CheckBox) view.findViewById(R.id.checkBox);

        final int fila=i;

        chckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = nombretv.getText().toString();
                Toast.makeText(contexto, "Checkbox "+fila+" clicked!", Toast.LENGTH_SHORT).show();
                clikados.add(nombre);
                for (int z=0; z<clikados.size();z++){
                    System.out.println("z*****: " + clikados.get(z));
                }
            }
        });










        nombretv.setText(datos[i]);
        img.setImageResource(imagenes[i]);
        return view;

        /*final ViewHolder holder;
        holder = new ViewHolder();
        view=inflater.inflate(R.layout.fila,null);

        holder.ck1 = (CheckBox)  view.findViewById(R.id.checkBox);


        holder.ck1.setChecked(false);

        if (mCheckStates.get(i))
            holder.ck1.setChecked(true);
        else
            holder.ck1.setChecked(false);

        holder.ck1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.ck1.isChecked())
                    mCheckStates.append(i,true);
                else
                    mCheckStates.append(i,false);
            }
        });

        for (int j=0; j<mCheckStates.size();j++){
            System.out.println("jjjj***+ " + mCheckStates.get(j));
        }

        return view;


         */






        /*
         //final int fila=i;
        elboton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("etiqueta", "Se ha pulsado el botón de la fila:"+fila);
                Intent in = new Intent(contexto,Activity3.class);
                in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                in.putExtra("backgroundColor",backgroundColor);
                in.putExtra("imageCode",imagenes[i]);
                contexto.startActivity(in);
            }
        });
         */



    }


}