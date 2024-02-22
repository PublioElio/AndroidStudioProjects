package com.example.examen_cp.spinner;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.examen_cp.R;

import java.util.ArrayList;

public class AdapterSpinner extends BaseAdapter {
    private final ArrayList<DatosSpinner> datos;
    private final Context contexto;
    public AdapterSpinner(Context contexto, ArrayList<DatosSpinner> datos){
        super();
        this.contexto = contexto;
        this.datos = datos;
    }
    @Override
    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater mostrado = LayoutInflater.from(contexto);
        @SuppressLint("ViewHolder") View elemento = mostrado.inflate(R.layout.elementos_spinner, parent, false);

        ImageView avatar = elemento.findViewById(R.id.spinnerAvatar);
        avatar.setImageResource(datos.get(posicion).getAvatar());

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
