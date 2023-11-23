package com.example.unidad_05_ejercicios_07;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter {

    private ArrayList<Datos> datos;

    public Adaptador(Context context, ArrayList<Datos> datos) {
        super(context, R.layout.elementos, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflo el elemento
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elementos,parent,false);

        // Asigno contenido al elemento
        ImageView imagen = elemento.findViewById(R.id.imagen1);
        imagen.setImageResource(datos.get(position).getImagen());

        TextView texto1 = elemento.findViewById(R.id.miTexto1);
        texto1.setText(datos.get(position).getTexto1());

        TextView texto2 = elemento.findViewById(R.id.miTexto2);
        texto2.setText(datos.get(position).getTexto2());

        return elemento;
    }
}
