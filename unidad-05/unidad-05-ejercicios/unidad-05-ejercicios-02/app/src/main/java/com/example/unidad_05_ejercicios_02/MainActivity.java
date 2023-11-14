package com.example.unidad_05_ejercicios_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Recupero el elemento del layout y defino los valores del listado
        final ListView listaSeries = findViewById(R.id.SeriesList);
        String[] datos = {"The Wire", "Succession", "The Expanse", "Daredevil", "WandaVision"};

        // Creo el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        // Asigno el adaptador
        listaSeries.setAdapter(adaptador);

        // Defino el manejador de eventos
        listaSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listado, View view, int position, long id) {

            }
        });
    }
}