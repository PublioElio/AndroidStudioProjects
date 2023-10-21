package com.example.unidad_03_practica_de_evaluacion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        portada(); //la portada es dónde accede por primera vez el usuario
        // activity_main(); // este es el layout propuesto en el ejercicio
        // encuesta(); // este layout es la encuesta final a la que puede acceder el usuario

    }

    private void portada() {
        setContentView(R.layout.portada);
        // Declaro los dos elementos (por si hay que usarlos)
        final Button acceder = findViewById(R.id.acceder);
        final Button encuesta = findViewById(R.id.encuesta);

    }

    private void activity_main() {
        setContentView(R.layout.activity_main);

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

    private void encuesta() {
        setContentView(R.layout.encuesta);

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

    }
}