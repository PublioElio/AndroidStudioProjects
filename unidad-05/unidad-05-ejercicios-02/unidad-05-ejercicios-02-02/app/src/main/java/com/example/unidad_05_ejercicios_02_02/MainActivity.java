package com.example.unidad_05_ejercicios_02_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    // Creo menú desde XML
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String opcion = item.getTitle().toString();
        final TextView texto = findViewById(R.id.texto);
        switch (opcion) {
            case "LUNES":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "MARTES":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "MIÉRCOLES":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "JUEVES":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "VIERNES":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "SÁBADO":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "DOMINGO":
                texto.setText("Pulsado " + item.getTitle().toString().toLowerCase());
                break;
            case "ENERO":
                texto.setText("Pulsado el mes de " + item.getTitle().toString().toLowerCase());
                break;
            case "FEBRERO":
                texto.setText("Pulsado el mes de " + item.getTitle().toString().toLowerCase());
                break;
            case "MARZO":
                texto.setText("Pulsado el mes de " + item.getTitle().toString().toLowerCase());
                break;
            case "ABRIL":
                texto.setText("Pulsado el mes de " + item.getTitle().toString().toLowerCase());
                break;
            default:
                texto.setText("Pulse una opción del menú");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}