package com.example.practica_exam_adiaz.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.practica_exam_adiaz.R;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter {

    ArrayList<Datos> datos;

    public Adaptador(Context context, ArrayList<Datos> datos) {
        super(context, R.layout.elemento, datos);
        this.datos = datos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elemento, parent, false);

        ImageView portada = elemento.findViewById(R.id.imageViewPortada);
        portada.setImageResource(datos.get(position).getImagen());

        TextView titulo = elemento.findViewById(R.id.textViewTitulo);
        titulo.setText(datos.get(position).getTitulo());

        return elemento;
    }
}
