package com.example.ejercicio1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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


    }
}