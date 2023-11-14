package com.example.unidad_05_ejercicios_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero el elemento del layout y defino los valores del listado
        final ListView lista1 = findViewById(R.id.lista1);
        final ListView lista2 = findViewById(R.id.lista2);
        String[] datos = {"Opción 1", "Opción 2", "Opción 3"};

        // Creo el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        // Asigno el adaptador
        lista1.setAdapter(adaptador);
        lista2.setAdapter(adaptador);
    }
}