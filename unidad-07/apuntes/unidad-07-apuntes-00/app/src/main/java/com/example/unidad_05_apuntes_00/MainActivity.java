package com.example.unidad_05_apuntes_00;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button guardar = findViewById(R.id.btnGuardar);
        final Button cargar = findViewById(R.id.btnCargar);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtengo la referencia de la colección de preferencias (archivo XML)
                // donde tengo o voy a guardar las preferencias
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                // Guardamos valores
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("nombre", "Peter Parker");
                editor.putString("email", "pparker@dailybugle.com");

                // Guardar los cambios
                editor.commit();
            }
        });

        cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtengo la referencia de la colección de preferencias (archivo XML)
                // donde tengo los datos
                SharedPreferences prefs =
                        getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

                String nombre = prefs.getString("nombre", "");
                String email = prefs.getString("email", "");

                Log.i("Preferencias", "Nombre " + nombre);
                Log.i("Preferencias", "Email " + email);
            }
        });



    }
}