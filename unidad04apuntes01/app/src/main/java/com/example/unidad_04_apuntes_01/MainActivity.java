package com.example.unidad_04_apuntes_01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

/**
 * Este es un ejemplo del ciclo de vida
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Los métodos de llamada de más abajo los he generado automáticamente en el menú superior. Code -> Override Methods y escojo aquellos métodos que más me interesan

    // Definimos el método callback onStart de la Actividad
    @Override
    protected void onStart() {
        super.onStart();
        // Aquí deberíamos leer los datos de la última sesión de usuario para continuar la actividad donde la dejó el usuario
        Toast.makeText(this, "Se ejecuta el método onStart()", Toast.LENGTH_SHORT).show();
    }

    // Definimos el método callback onResume() de la actividad
    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Se ejecuta el método onResume()", Toast.LENGTH_SHORT).show();
    }

    // Definimos el método callback onPause()
    @Override
    protected void onPause() {
        super.onPause();
        // Deberíamos guardar la información para la siguiente sesión
        Toast.makeText(this, "Se ejecuta el método onPause()", Toast.LENGTH_SHORT).show();
    }

    // Definimos el método callback onStop() de la actividad
    @Override
    protected void onStop() {
        super.onStop();

        Toast.makeText(this, "Se ejecuta el método onStop()", Toast.LENGTH_SHORT).show();
    }

    // Definimos el método callback onRestart() de la actividad
    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this, "Se ejecuta el método onRestart()", Toast.LENGTH_SHORT).show();
    }

    // Definimos el método callback onDestroy() de la  actividad
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Se ejecuta el método onDestroy()", Toast.LENGTH_SHORT).show();
    }
}