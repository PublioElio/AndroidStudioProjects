package com.example.ejercicio_09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnMostrar = findViewById(R.id.btnMostrar);
        final Button btnEliminar = findViewById(R.id.btnEliminar);
        final Button btnAgregar = findViewById(R.id.btnAgregar);
        final EditText editTextFechaLanzamiento = findViewById(R.id.editTextFechaLanzamiento);
        final EditText editTextVersion = findViewById(R.id.editTextVersion);

        

    }
}