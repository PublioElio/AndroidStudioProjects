package com.example.unidad_05_ejercicios_08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private ArrayList<Datos> datos;
    private Context contexto;

    public Adaptador(Context contexto, ArrayList<Datos> datos) {
        super();
        this.datos = datos;
        this.contexto = contexto;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return datos.get(position);
    }

    @Override
    public long getItemId(int position) {
        // devuelve la fila
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(contexto);
        View elemento = mostrado.inflate(R.layout.elementos,parent,false);

        ImageView imagen = elemento.findViewById(R.id.imagen);
        imagen.setImageResource(datos.get(position).getImagen());

        TextView texto1 = elemento.findViewById(R.id.texto);
        texto1.setText(datos.get(position).getTexto());

        return elemento;
    }
}

