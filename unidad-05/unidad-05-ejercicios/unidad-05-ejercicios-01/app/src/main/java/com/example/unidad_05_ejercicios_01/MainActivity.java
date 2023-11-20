package com.example.unidad_05_ejercicios_01;

import androidx.appcompat.app.AppCompatActivity;

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
        final ListView lista1 = findViewById(R.id.lista1);
        final ListView lista2 = findViewById(R.id.lista2);
        // Creo los datos
        Datos[] datos = new Datos[]{
                new Datos("Línea superior 1", "Línea inferior 1"),
                new Datos("Línea superior 2", "Línea inferior 2"),
                new Datos("Línea superior 3", "Línea inferior 3"),
                new Datos("Línea superior 4", "Línea inferior 4"),
                new Datos("Línea superior 5", "Línea inferior 5")
        };

        // Incorporo la cabecera al listado
        View miCabecera = getLayoutInflater().inflate(R.layout.cabecera, null);
        lista1.addHeaderView(miCabecera);
        lista2.addHeaderView(miCabecera);

        // Creo el adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        lista1.setAdapter(adaptador);
        lista2.setAdapter(adaptador);

        // Inserto el listener
        lista1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != miCabecera) {
                    String seleccionado = ((Datos) parent.getItemAtPosition(position)).getTexto1();
                    Toast.makeText(MainActivity.this, "Has pulsado: " + seleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });


        lista2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != miCabecera) {
                    String seleccionado = ((Datos) parent.getItemAtPosition(position)).getTexto1();
                    Toast.makeText(MainActivity.this, "Has pulsado: " + seleccionado, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}