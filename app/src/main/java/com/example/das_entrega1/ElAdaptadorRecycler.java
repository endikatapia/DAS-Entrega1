package com.example.das_entrega1;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.RecyclerView;

public class ElAdaptadorRecycler extends RecyclerView.Adapter<ElViewHolder> {
    private String[] losnombres;
    private int[] lasimagenes;
    private boolean[] seleccionados;

    public ElAdaptadorRecycler (String[] nombres, int[] imagenes)
    {
        losnombres=nombres;
        lasimagenes=imagenes;
        seleccionados=new boolean[nombres.length];
    }

    @NonNull
    @Override
    public ElViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View elLayoutDeCadaItem= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,null);
        ElViewHolder evh = new ElViewHolder(elLayoutDeCadaItem);
        evh.seleccion = seleccionados;
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ElViewHolder holder, int position) {
        holder.eltexto.setText(losnombres[position]);
        holder.laimagen.setImageResource(lasimagenes[position]);

    }

    @Override
    public int getItemCount() {
        return losnombres.length;
    }
}
