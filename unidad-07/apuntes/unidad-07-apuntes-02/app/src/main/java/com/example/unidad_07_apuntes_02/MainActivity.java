package com.example.unidad_07_apuntes_02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnPreferencias = findViewById(R.id.btnPreferencias);
        final Button btnConsultar = findViewById(R.id.btnConsultar);

        btnPreferencias.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OpcionesActivity.class)));

        btnConsultar.setOnClickListener(view -> {
            SharedPreferences prefs =
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this);

            Log.i("Preferencias",
                    "Opción 1" + prefs.getBoolean("opcion1", false));
            Log.i("Preferencias",
                    "Opción 2" + prefs.getString("opcion2", ""));
            Log.i("Preferencias",
                    "Opción 3" + prefs.getString("opcion3", ""));
        });
    }
}