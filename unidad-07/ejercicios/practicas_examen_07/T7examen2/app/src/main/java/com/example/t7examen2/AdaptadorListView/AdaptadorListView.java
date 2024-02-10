package com.example.t7examen2.AdaptadorListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t7examen2.R;

import java.util.ArrayList;

public class AdaptadorListView extends BaseAdapter {

    private ArrayList<DatosListView> datos;
    private Context contexto;

    public AdaptadorListView(Context contexto, ArrayList<DatosListView> datos){
        super();
        this.contexto = contexto;
        this.datos = datos;
    }

    public AdaptadorListView(View.OnClickListener onClickListener, ArrayList<DatosListView> datos) {
        super();
        this.contexto = contexto;
        this.datos = datos;
    }

    @Override
    public View getView(int posicion, View view, ViewGroup parent){

        LayoutInflater mostrado = LayoutInflater.from(contexto);
        View elemento = mostrado.inflate(R.layout.elemento_l, parent, false);

        ImageView avatar = elemento.findViewById(R.id.avatar);
        avatar.setImageResource(datos.get(posicion).getAvatar());

        TextView nombre = (TextView) elemento.findViewById(R.id.nombre);
        nombre.setText(datos.get(posicion).getNombre());

        TextView telefono = (TextView) elemento.findViewById(R.id.telefono);
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
