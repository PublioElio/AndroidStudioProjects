package com.example.unidad_03_practica_de_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class recordatorioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recordatorio_activity);
        setContentView(R.layout.recordatorio_activity);

        // Delacración de elementos
        final EditText nombre = findViewById(R.id.nombre);
        final EditText fecha = findViewById(R.id.fNacimiento);
        final CheckBox recordatorio = findViewById(R.id.recordatorio);
        final Button aceptar = findViewById(R.id.aceptar);
        final TextView resultado = findViewById(R.id.resultado); // este TextView estará oculto hasta que el usuario pulse el botón

        aceptar.setOnClickListener(view -> {
            resultado.setVisibility(View.VISIBLE);
            if (nombre.getText().toString().isEmpty())
                resultado.setText("ERROR: introduzca un nombre válido");
            else if (fecha.getText().toString().isEmpty())
                resultado.setText("ERROR: introduzca una fecha válida");
            else {
                resultado.setText("¡Hola, " + nombre.getText() + "! Has nacido el '"
                        + fecha.getText() + "'.");
                if (recordatorio.isChecked())
                    resultado.append("Se ha creado un recordatorio.");
            }
        });
    }
}