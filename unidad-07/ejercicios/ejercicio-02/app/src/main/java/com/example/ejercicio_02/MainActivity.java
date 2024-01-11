package com.example.ejercicio_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnDefinir = findViewById(R.id.btnDefinir);
        final Button btnConsultar = findViewById(R.id.btnRecuperar);

        btnDefinir.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, OpcionesActivity.class)));

        btnConsultar.setOnClickListener(view -> {
            SharedPreferences preferences =
                    PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
            Log.i("Preferencias",
                    "Opción 1" + preferences.getBoolean("opcion1", false));
            Log.i("Preferencias",
                    "Opción 2" + preferences.getString("opcion2", ""));
            Log.i("Preferencias",
                    "Opción 3" + preferences.getString("opcion3", ""));
        });
    }
}