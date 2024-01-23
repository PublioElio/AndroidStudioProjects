package com.example.ejercicio_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    }
}