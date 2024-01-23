package com.example.ejercicio_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo las variables
        final Button guardar = findViewById(R.id.btnGuardar);
        final Button recuperar = findViewById(R.id.btnRecuperar);
        final EditText myEditText = findViewById(R.id.myEditTxt);
        final TextView myTextView = findViewById(R.id.myTextView);

        // Guardo el texto del Edit Text en el archivo de memoria interna
        guardar.setOnClickListener(view -> {
            try {
                OutputStreamWriter miFichero =
                        new OutputStreamWriter(openFileOutput("fichero.txt", Context.MODE_PRIVATE));
                miFichero.write(myEditText.getText().toString());
                myTextView.setText("");
                miFichero.close();
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al escribir fichero en memoria interna");
            }
        });

        // Cargo el texto del Edit Text en el archivo de memoria interna
        recuperar.setOnClickListener(view -> {
            try {
                BufferedReader miFichero =
                        new BufferedReader(new InputStreamReader(openFileInput("fichero.txt")));
                myTextView.setText(miFichero.readLine());
                miFichero.close();
            } catch (Exception e) {
                Log.e("Ficheros","Error al leer desde la memoria interna");
            }
        });


    }
}