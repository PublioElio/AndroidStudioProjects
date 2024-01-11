package com.example.unidad_07_apuntes_01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button guardar = findViewById(R.id.btnGuardar);
        final Button borrar = findViewById(R.id.btnBorrar);
        final Button cargar = findViewById(R.id.btnCargar);
        final EditText etNombre = findViewById(R.id.etNombre);
        final EditText etCorreo = findViewById(R.id.etCorreo);

        guardar.setOnClickListener(view -> {
            // Obtengo la referencia de la colección
            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = prefs.edit();

            editor.putString("nombre", etNombre.getText().toString());
            editor.putString("email", etCorreo.getText().toString());
            editor.commit();
        });

        cargar.setOnClickListener(view -> {
            // Obtengo la referencia de la preferencia
            SharedPreferences prefs =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            String nombre = prefs.getString("nombre", "");
            String correo = prefs.getString("email", "");

            etNombre.setText(nombre);
            etCorreo.setText(correo);
        });

        borrar.setOnClickListener(view -> {
            etNombre.setText("");
            etCorreo.setText("");
        });
    }
}