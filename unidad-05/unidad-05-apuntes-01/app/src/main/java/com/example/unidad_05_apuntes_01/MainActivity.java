package com.example.unidad_05_apuntes_01;

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
        final ListView listaOpciones = findViewById(R.id.listaOpciones);
        String[] datos = {"Opción 1", "Opción 2", "Opción 3"};

        // Creo el adaptador
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);

        // Asigno el adaptador
        listaOpciones.setAdapter(adaptador);

        // Defino el manejador de eventos
        listaOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listado, View view, int position, long id) {
                // Modo cutre, ¡no usar! >:(
                Toast.makeText(MainActivity.this, "Este es el método del mal >:( - "
                        + datos[position], Toast.LENGTH_LONG).show();
                // Modo 2: obtenemor el elemento del listado - esta es buena
                Toast.makeText(MainActivity.this, "Este es el método 2 - "
                        + listado.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                // Modo 3: obtenemos el elemento del adaptador - esta también es buena
                Toast.makeText(MainActivity.this, "Este es el método 3 - "
                        + listado.getAdapter().getItem(position), Toast.LENGTH_LONG).show();
            }
        });

    }
}