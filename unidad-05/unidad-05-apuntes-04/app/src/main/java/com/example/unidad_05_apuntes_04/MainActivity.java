package com.example.unidad_05_apuntes_04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defino los datos
        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos("Línea superior 1", "Línea inferior 1"));
        datos.add(new Datos("Línea superior 2", "Línea inferior 2"));
        datos.add(new Datos("Línea superior 3", "Línea inferior 3"));

        // Incorporo el elemento ListView
        ListView listado = findViewById(R.id.miLista);

        // Defino el adaptador
        Adaptador miAdaptador = new Adaptador(this, datos);
        listado.setAdapter(miAdaptador);

        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = ((Datos) parent.getItemAtPosition(position)).getTexto1();
                Toast.makeText(MainActivity.this, "Has pulsado: " + seleccionado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}