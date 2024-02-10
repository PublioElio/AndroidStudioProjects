package com.example.t7examen2.AdaptadorSpinner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.t7examen2.R;

import java.util.ArrayList;

public class AdaptadorSpinner extends BaseAdapter {

    private ArrayList<DatosSpinner> datos;
    private Context contexto;

    public AdaptadorSpinner(Context contexto, ArrayList<DatosSpinner> datos){
        super();
        this.contexto = contexto;
        this.datos = datos;
    }

    public AdaptadorSpinner(View.OnClickListener onClickListener, ArrayList<DatosSpinner> datos) {
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
