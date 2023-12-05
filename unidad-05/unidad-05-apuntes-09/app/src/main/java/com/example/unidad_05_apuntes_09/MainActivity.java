package com.example.unidad_05_apuntes_09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero el id del listado
        final ListView listado = findViewById(R.id.listado);

        // Defino los datos
        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos("Texto 1", false));
        datos.add(new Datos("Texto 2", true));
        datos.add(new Datos("Texto 3", false));
        datos.add(new Datos("Texto 4", true));

        // Crear adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        listado.setAdapter(adaptador);

        final Button aceptar = findViewById(R.id.btnAceptar);

        // Listener sobre el boton
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cont = 0;
                for(int i = 0; i< datos.size(); i++){
                    if(datos.get(i).isCheck()){
                        cont++;
                    }
                }
                final TextView texto = findViewById(R.id.texto);
                texto.setText("seleccionado: " + cont);
            }
        });
    }
}