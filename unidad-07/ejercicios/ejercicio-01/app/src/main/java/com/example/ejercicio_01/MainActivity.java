package com.example.ejercicio_01;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText etSuperior = findViewById(R.id.etSuperior);
        final EditText etInferior = findViewById(R.id.etInferior);

        final TextView tvSuperior = findViewById(R.id.tvSuperior);
        final TextView tvInferior = findViewById(R.id.tvInferior);

        final Button btnGuardar = findViewById(R.id.btnGuardar);
        final Button btnRecuperar = findViewById(R.id.btnRecuperar);

        btnGuardar.setOnClickListener(view -> {
            // Obtengo la referencia de la colección
            SharedPreferences preferences =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();

            editor.putString("superior", etSuperior.getText().toString());
            editor.putString("inferior", etInferior.getText().toString());
            etSuperior.setText("");
            etInferior.setText("");
            editor.commit();
        });

        btnRecuperar.setOnClickListener(view -> {
            SharedPreferences preferences =
                    getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
            String superior = preferences.getString("superior", "");
            String inferior = preferences.getString("inferior", "");
            tvSuperior.setText(superior);
            tvInferior.setText(inferior);
        });

    }
}