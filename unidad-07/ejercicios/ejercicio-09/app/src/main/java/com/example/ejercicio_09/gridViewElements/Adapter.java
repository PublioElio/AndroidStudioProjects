package com.example.ejercicio_09.gridViewElements;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ejercicio_09.R;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private ArrayList<Datos> datos;
    private Context contexto;

    public Adapter(Context contexto, ArrayList<Datos> datos) {
        super();
        this.contexto = contexto;
        this.datos = datos;
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
    public long getItemId(int position) { return position; }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater mostrado = LayoutInflater.from(contexto);
        @SuppressLint("ViewHolder") View elemento = mostrado.inflate(R.layout.elementos,parent,
                false);

        TextView version = elemento.findViewById(R.id.textViewVersion);
        version.setText(datos.get(position).getVersion());

        TextView anyo = elemento.findViewById(R.id.textViewAnyo);
        anyo.setText(String.valueOf(datos.get(position).getYear()));

        return elemento;
    }
}
