package com.example.unidad_05_apuntes_09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero el id del listado
        final ListView listado = findViewById(R.id.listado);

        // Defino los datos
        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos("Texto 1", false));
        datos.add(new Datos("Texto 2", false));
        datos.add(new Datos("Texto 3", false));
        datos.add(new Datos("Texto 4", false));

        // Crear adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        listado.setAdapter(adaptador);


    }
}