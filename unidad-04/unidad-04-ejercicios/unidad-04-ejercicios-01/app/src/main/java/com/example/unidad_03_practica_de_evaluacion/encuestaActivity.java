package com.example.unidad_03_practica_de_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class encuestaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta_activity);

        final Button portada = findViewById(R.id.portada);
        final Button volver = findViewById(R.id.volver);

        // Creo el Spinner con sus elementos
        final Spinner miSpinner = findViewById(R.id.miSpinner);
        String[] elementosSpinner = {"De 0 a 10", "De 10 a 20", "De 20 a 30", "Más de 30"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementosSpinner);
        miSpinner.setAdapter(adapter);

        // Inicializo el resto de elementos que voy a utilizar
        final Switch boletin = findViewById(R.id.boletin);
        final RatingBar valoracion = findViewById(R.id.valoracion);
        final TextView resultadoEncuesta = findViewById(R.id.resultadoEncuesta);
        final TextView notificacionBoletin = findViewById(R.id.notificacionBoletin);


        boletin.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if(boletin.isChecked()){
                notificacionBoletin.setVisibility(View.VISIBLE);
            } else {
                notificacionBoletin.setVisibility(View.INVISIBLE);
            }
        });

        valoracion.setOnRatingBarChangeListener((ratingBar, rating, fromUser) -> {
            resultadoEncuesta.setText("¡Gracias por tu valoración!");
            resultadoEncuesta.setVisibility(View.VISIBLE);
        });

        portada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(encuestaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(encuestaActivity.this, recordatorioActivity.class);
                startActivity(intent);
            }
        });

    }
}