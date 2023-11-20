package com.example.unidad_05_ejercicios_06;

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
        // Creo los datos
        Datos[] datos = new Datos[]{
                new Datos("Bruce Campbell", "969 856 952"),
                new Datos("Darío Argento", "652 896 231"),
                new Datos("John Carpenter", "986 562 353"),
                new Datos("George A. Romero", "985 265 337"),
                new Datos("Sam Raimi", "689 235 645")
        };

        // Incorporo la cabecera al listado
        View miCabecera = getLayoutInflater().inflate(R.layout.cabecera, null);
        lista1.addHeaderView(miCabecera);

        // Creo el adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        lista1.setAdapter(adaptador);

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
    }
}