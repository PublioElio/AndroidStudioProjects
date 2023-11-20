package com.example.unidad_05_ejercicios_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Defino el elemento TextView
        final TextView selection = findViewById(R.id.txtSelection);

        // Recupero el elemento Spinner del layout y defino los valores del listado
        final Spinner listaSeries = findViewById(R.id.SeriesSpinner);
        String[] datos = {"The Wire", "Succession", "Dirk Gently", "The Expanse", "Daredevil",
                "Bojack Horseman", "The Last Of Us", "WandaVision", "Arcane", "Love, Death & Robots"};
        // Creo el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        // Asigno el adaptador
        listaSeries.setAdapter(adaptador);

        // Defino el manejador de eventos
        listaSeries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection.setText(adaptador.getItem(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}