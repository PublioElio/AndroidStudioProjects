package com.example.unidad_05_apuntes_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero el id del listado
        ListView listado = findViewById(R.id.miLista);

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
        listado.addHeaderView(miCabecera);

        // Creo el adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        listado.setAdapter(adaptador);

        // Inserto el listener
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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