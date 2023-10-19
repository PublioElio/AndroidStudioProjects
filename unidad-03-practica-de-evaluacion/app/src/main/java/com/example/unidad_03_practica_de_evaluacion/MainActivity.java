package com.example.unidad_03_practica_de_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portada);

        portada();
        // activity_main();
        // encuesta();

    }

    private void portada() { // esta es la portada de la aplicación
        setContentView(R.layout.portada);

        final Button acceder = findViewById(R.id.acceder);
        final Button encuesta = findViewById(R.id.encuesta);
        acceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setContentView(R.layout.activity_main);
            }
        });

        encuesta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.encuesta);
            }
        });

    }

    private void encuesta() {
    }

    private void activity_main() {

        final EditText nombre = findViewById(R.id.nombre);
        final EditText fecha = findViewById(R.id.fNacimiento);
        final Button aceptar = findViewById(R.id.aceptar);
        final TextView resultado = findViewById(R.id.resultado);



        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().toString().isEmpty() && fecha.getText().toString().isEmpty()) {
                    resultado.setText("ERROR. Debe escribir nombre y fecha válidos");
                }
            }
        });


    }
}