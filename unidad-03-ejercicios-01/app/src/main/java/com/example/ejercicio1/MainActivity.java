package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ejercicio12);

        // la parte de abajo está comentada, porque es la del ejercicio nº10 - descomentar para ese ejercicio
        /*
        // Busco el id de la primera etiqueta
        EditText name = (EditText) findViewById(R.id.editTxt1);
        // Recupero el id de la segunda etiqueta
        EditText pass = (EditText) findViewById(R.id.editTxt2);
        // Recupero el botón
        final Button myButton = findViewById(R.id.btn1);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name.setText("");
                pass.setText("");
            }
        });*/

        // --------------- EJERCICIO Nº12 ----------------------------

        // Primero voy a capturar todos los checkboxes y guardarlos en variables
        final CheckBox lectura = findViewById(R.id.lectura);
        final CheckBox teatro = findViewById(R.id.teatro);
        final CheckBox senderismo = findViewById(R.id.senderismo);
        final CheckBox fotografia = findViewById(R.id.fotografia);
        final CheckBox escritura = findViewById(R.id.escritura);
        final CheckBox voleibol = findViewById(R.id.voleibol);
        final CheckBox escalada = findViewById(R.id.escalada);
        final CheckBox trail = findViewById(R.id.trail);

        // Ahora vamos a guardar todos los radio groups
        final RadioGroup generos = findViewById(R.id.gGeneros);
        final RadioGroup deportes = findViewById(R.id.gDeportes);

        // Guardamos los botones
        final Button reset = findViewById(R.id.reset);
        final Button accept = findViewById(R.id.accept);

        // Y por último, la caja de texto
        final TextView resumenAficiones = findViewById(R.id.resumenAficiones);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Si la visibilidad de la caja de texto es INVISIBLE, le añado el texto de cada checkbox y la pongo visible
                if (resumenAficiones.getVisibility() == View.INVISIBLE) {
                    if (lectura.isChecked()) {
                        resumenAficiones.append(lectura.getText() + "\n");
                    }
                    if (teatro.isChecked()) {
                        resumenAficiones.append(teatro.getText() + "\n");
                    }
                    if (senderismo.isChecked()) {
                        resumenAficiones.append(senderismo.getText() + "\n");
                    }
                    if (fotografia.isChecked()) {
                        resumenAficiones.append(fotografia.getText() + "\n");
                    }
                    if (escritura.isChecked()) {
                        resumenAficiones.append(escritura.getText() + "\n");
                    }
                    if (voleibol.isChecked()) {
                        resumenAficiones.append(voleibol.getText() + "\n");
                    }
                    if (escalada.isChecked()) {
                        resumenAficiones.append(escalada.getText() + "\n");
                    }
                    if (trail.isChecked()) {
                        resumenAficiones.append(trail.getText() + "\n");
                    }
                    int generosId = generos.getCheckedRadioButtonId();
                    if (generosId != -1) {
                        RadioButton generoRadioButton = findViewById(generosId);
                        resumenAficiones.append("Género literario favorito: " + generoRadioButton.getText() + "\n");
                    }
                    int deportesId = deportes.getCheckedRadioButtonId();
                    if (deportesId != -1) {
                        RadioButton deporteRadioButton = findViewById(deportesId);
                        resumenAficiones.append("Deporte favorito: " + deporteRadioButton.getText() + "\n");
                    }
                    resumenAficiones.setVisibility(View.VISIBLE);
                }
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Desmarco los checkboxes, reseteo la caja de texto y la pongo invisible
                lectura.setChecked(false);
                teatro.setChecked(false);
                senderismo.setChecked(false);
                fotografia.setChecked(false);
                escritura.setChecked(false);
                voleibol.setChecked(false);
                escalada.setChecked(false);
                trail.setChecked(false);
                generos.clearCheck();
                deportes.clearCheck();
                resumenAficiones.setText("Tus aficiones son:\n");
                resumenAficiones.setVisibility(View.INVISIBLE);
            }
        });
    }
}