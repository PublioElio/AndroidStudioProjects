package com.example.t7examen.adaptador;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t7examen.R;

import java.util.ArrayList;

public class Adaptador extends BaseAdapter {

    private final ArrayList<Datos> datos;
    private final Context contexto;

    public Adaptador(Context contexto, ArrayList<Datos> datos){
        super();
        this.contexto = contexto;
        this.datos = datos;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater mostrado = LayoutInflater.from(contexto);
        @SuppressLint("ViewHolder") View elemento = mostrado.inflate(R.layout.elemento, parent, false);

        ImageView icono = elemento.findViewById(R.id.imagen);
        icono.setImageResource(datos.get(posicion).getImagen());

        TextView nombre = elemento.findViewById(R.id.nombre);
        nombre.setText(datos.get(posicion).getNombre());

        return elemento;
    }

    @Override
    public int getCount(){
        return datos.size();
    }

    @Override
    public Object getItem(int posicion){return datos.get(posicion);}

    @Override
    public long getItemId(int posicion){
        return posicion;
    }
}
