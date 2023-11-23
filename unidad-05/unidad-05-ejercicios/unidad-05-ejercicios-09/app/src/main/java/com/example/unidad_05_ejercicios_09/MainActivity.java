package com.example.unidad_05_ejercicios_09;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

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
        CheckBox checkBox = elemento.findViewById(R.id.checkBox);
        checkBox.

        ImageView imagen = elemento.findViewById(R.id.imagen);
        imagen.setImageResource(datos.get(position).getImagen());

        TextView texto = elemento.findViewById(R.id.texto);
        texto.setText(datos.get(position).getTexto());

        return elemento;
    }
}