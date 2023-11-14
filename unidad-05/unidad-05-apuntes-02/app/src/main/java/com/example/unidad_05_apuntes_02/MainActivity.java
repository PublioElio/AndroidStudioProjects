package com.example.unidad_05_apuntes_02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero id del listado
        GridView listado = findViewById(R.id.myGrid);

        // Defino la fuente de datos
        final String datos[] = {"Elemento 1", "Elemento 2", "Elemento 3", "Elemento 4", "Elemento 5"};

        // Creo el adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);

        // Asigno el manejador de eventos
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Obtenemos el texto del elemento pulsado
                // Modo 1:
                Toast.makeText(MainActivity.this, "Has pulsado "+ parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                // Modo 2:
                Toast.makeText(MainActivity.this, "Modo 2. Has pulsado " + parent.getAdapter().getItem(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}