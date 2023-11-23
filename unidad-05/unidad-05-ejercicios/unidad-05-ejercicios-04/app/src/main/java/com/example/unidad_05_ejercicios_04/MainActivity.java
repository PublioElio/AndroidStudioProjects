package com.example.unidad_05_ejercicios_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Defino el elemento TextView
        final TextView selection = findViewById(R.id.txtSelection);

        // Recupero el elemento del layout y defino los valores del GridView
        final GridView listaSeries = findViewById(R.id.SeriesList);
        String[] datos = {"The Wire", "Succession", "Dirk Gently", "The Expanse", "Daredevil",
                "Bojack Horseman", "The Last Of Us", "WandaVision", "Arcane", "Love, Death & Robots"};

        // Creo el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        // Asigno el adaptador
        listaSeries.setAdapter(adaptador);

        // Defino el manejador de eventos
        listaSeries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listado, View view, int position, long id) {
                selection.setText(adaptador.getItem(position));
            }
        });

    }
}