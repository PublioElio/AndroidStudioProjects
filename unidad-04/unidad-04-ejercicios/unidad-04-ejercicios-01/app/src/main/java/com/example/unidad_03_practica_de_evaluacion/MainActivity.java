package com.example.unidad_03_practica_de_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // la activity_main es la portada de la aplicación

        // Un botón lo voy a hacer con el método onClick()
        // -más abajo- y otro capturando el evento, para practicar
        final Button encuesta = findViewById(R.id.encuesta);

        encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, encuestaActivity.class);
                startActivity(intent);
            }
        });
    }

    public void onClick(View view){
        Intent intent = new Intent(this, recordatorioActivity.class);
        startActivity(intent);
    }
}