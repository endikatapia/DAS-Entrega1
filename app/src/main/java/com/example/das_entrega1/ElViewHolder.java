package com.example.das_entrega1;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ElViewHolder extends RecyclerView.ViewHolder {
    public TextView eltexto;
    public ImageView laimagen;
    public boolean[] seleccion;

    public ElViewHolder(@NonNull View itemView) {
        super(itemView);
        eltexto = itemView.findViewById(R.id.texto);
        laimagen = itemView.findViewById(R.id.foto);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(itemView.getContext(),ActivityPizza.class);
                if(getAdapterPosition()==0) {
                    i1.putExtra("categoria",0);
                    itemView.getContext().startActivity(i1);
                }else if (getAdapterPosition()==1) {
                    i1.putExtra("categoria",1);
                    itemView.getContext().startActivity(i1);
                }
                else if (getAdapterPosition()==2) {
                    i1.putExtra("categoria",2);
                    itemView.getContext().startActivity(i1);
                }
                else if (getAdapterPosition()==3) {
                    i1.putExtra("categoria",3);
                    itemView.getContext().startActivity(i1);
                }
                else if (getAdapterPosition()==4) {
                    i1.putExtra("categoria",4);
                    itemView.getContext().startActivity(i1);
                }

                /*
                //Si esta seleccionado
                if (seleccion[getAdapterPosition()] == true) {
                    seleccion[getAdapterPosition()] = false;
                    laimagen.setBackgroundColor(Color.WHITE);

                } else { //Si no esta seleccionado
                    seleccion[getAdapterPosition()] = true;
                    laimagen.setBackgroundColor(Color.BLUE);
                }

                 */
            }
        });
    }

}

