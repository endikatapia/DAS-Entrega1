package com.example.das_entrega1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

public class ElViewHolder extends RecyclerView.ViewHolder {
    public TextView eltexto;
    public ImageView laimagen;
    public boolean[] seleccion;
    //public CardView cardView;

    public ElViewHolder(@NonNull View itemView) {
        super(itemView);
        eltexto = itemView.findViewById(R.id.texto);
        laimagen = itemView.findViewById(R.id.foto);
        //cardView = itemView.findViewById(R.id.cardView);



        //PREFERENCIAS ????
        /*
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(itemView.getContext());
        String comidaPref = prefs.getString("comidapref", "Pizza");
        if (comidaPref.equals("Pizza")){
            eltexto.setTypeface(null, Typeface.BOLD_ITALIC);
            eltexto.setTextColor(Color.GREEN);
        }
        else if (comidaPref.equals("Ensalada")){
            eltexto.setTypeface(null, Typeface.BOLD_ITALIC);
            eltexto.setTextColor(Color.RED);
        }

         */

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(itemView.getContext(),ActivityComida.class);
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





        /*
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

                 *7
            }
        });

*/






    }

}

