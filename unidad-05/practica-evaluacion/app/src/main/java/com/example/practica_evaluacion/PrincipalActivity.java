package com.example.practica_evaluacion;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        final ListView miLista = findViewById(R.id.miLista);

        // Creo los datos
        ArrayList<DatosPrincipal> datos = new ArrayList<>();
        datos.add(new DatosPrincipal(R.drawable.vengadores));
        datos.add(new DatosPrincipal(R.drawable.xmen));

        // Creo el adaptador
        AdaptadorPrincipal adaptador = new AdaptadorPrincipal(this, datos);
        miLista.setAdapter(adaptador);

        // Inserto el listener
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }
}