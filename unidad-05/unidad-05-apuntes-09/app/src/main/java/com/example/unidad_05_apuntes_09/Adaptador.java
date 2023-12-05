package com.example.unidad_05_apuntes_09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter<Datos> {

    private ArrayList<Datos> datos;

    public Adaptador(Context context, ArrayList<Datos> datos) {
        super(context, R.layout.elementos, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elementos, parent, false);

        TextView texto = elemento.findViewById(R.id.etiqueta);
        texto.setText(datos.get(position).getTexto());



        return super.getView(position, convertView, parent);
    }
}
