package com.example.examen_cp.listview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.examen_cp.R;

import java.util.ArrayList;

public class AdapterListView extends BaseAdapter {

    private final ArrayList<DatosListView> datos;
    private final Context contexto;
    public AdapterListView(Context contexto, ArrayList<DatosListView> datos){
        super();
        this.contexto = contexto;
        this.datos = datos;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater mostrado = LayoutInflater.from(contexto);
        @SuppressLint("ViewHolder") View elemento = mostrado.inflate(R.layout.elementos_listview,
                parent, false);

        ImageView avatar = elemento.findViewById(R.id.listViewAvatar);
        avatar.setImageResource(datos.get(posicion).getAvatar());

        TextView nombre = elemento.findViewById(R.id.listViewNombre);
        nombre.setText(datos.get(posicion).getNombre());

        TextView telefono = elemento.findViewById(R.id.listViewTelefono);
        telefono.setText(datos.get(posicion).getTelefono());

        return elemento;
    }

    @Override
    public int getCount(){
        return datos.size();
    }

    @Override
    public DatosListView getItem(int posicion){return datos.get(posicion);}

    @Override
    public long getItemId(int posicion){
        return posicion;
    }
}
