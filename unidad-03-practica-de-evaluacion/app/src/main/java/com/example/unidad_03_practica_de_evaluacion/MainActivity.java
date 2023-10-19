package com.example.unidad_03_practica_de_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // portada();
         activity_main();
        // encuesta();

    }

    private void portada() { // esta es la portada de la aplicación
        setContentView(R.layout.portada);

        final Button acceder = findViewById(R.id.acceder);
        final Button encuesta = findViewById(R.id.encuesta);

    }

    private void encuesta() {
    }

    private void activity_main() {
        setContentView(R.layout.activity_main);

        final EditText nombre = findViewById(R.id.nombre);
        final EditText fecha = findViewById(R.id.fNacimiento);
        final Button aceptar = findViewById(R.id.aceptar);
        final TextView resultado = findViewById(R.id.resultado);
        final CheckBox recordatorio = findViewById(R.id.recordatorio);

        aceptar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                resultado.setVisibility(View.VISIBLE);
                if(nombre.getText().toString().isEmpty()){
                    resultado.setText("ERROR: introduzca un nombre válido");
                } else if (fecha.getText().toString().isEmpty()) {
                    resultado.setText("ERROR: introduzca una fecha válida");
                } else {
                    resultado.setText("¡Hola, " + nombre.getText() + "! Has nacido el '"
                            + fecha.getText() + "'.");
                    if(recordatorio.isChecked()){
                        resultado.append("Se ha creado un recordatorio.");
                    }
                }
            }
        });

    }
}