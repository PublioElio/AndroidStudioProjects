package com.example.ejercicio_08;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Adaptador extends ArrayAdapter {

    private ArrayList<Dibujante> dibujantes;

    public Adaptador(Context context, ArrayList<Dibujante> dibujantes) {
        super(context, R.layout.elementos, dibujantes);
        this.dibujantes = dibujantes;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflo el elemento
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elementos,parent,false);

        // Asigno contenido al elemento
        TextView dibujanteId = elemento.findViewById(R.id.dibujanteId);
        dibujanteId.setText(String.valueOf(dibujantes.get(position).getId()));

        TextView dibujanteNombre = elemento.findViewById(R.id.dibujanteNombre);
        dibujanteNombre.setText(dibujantes.get(position).getNombre());

        TextView dibujanteApellidos = elemento.findViewById(R.id.dibujanteApellidos);
        dibujanteApellidos.setText(dibujantes.get(position).getApellidos());

        return elemento;
    }

}
