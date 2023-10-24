package com.example.unidad_04_apuntes_02;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle estadoAlmacenado) {
        super.onCreate(estadoAlmacenado);
        setContentView(R.layout.activity_main);

        if (estadoAlmacenado != null) {
            // Recuperamos una variable que se almacena con el estado
            String cadena = estadoAlmacenado.getString("VARIABLE_ALMACENADA");
            Toast.makeText(this, "Método onCreate(). Se recupera la variable almacenada: "
                    + cadena, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle estado) {
        super.onSaveInstanceState(estado);
        // Añadimos una variable que se almacena con el estado
        estado.putString("VARIABLE_ALMACENADA", "Texto guardado");
        Toast.makeText(this, "El estado de la actividad se ha guardado", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "El sistema ha terminado la actividad", Toast.LENGTH_SHORT).show();
    }
}