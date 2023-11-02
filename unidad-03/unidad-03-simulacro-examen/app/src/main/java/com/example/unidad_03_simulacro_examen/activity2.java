package com.example.unidad_03_simulacro_examen;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity2);

        final Button btnWeight = findViewById(R.id.btnWeight);
        final Button btnStrength = findViewById(R.id.btnStrength);
        final Button btnFit = findViewById(R.id.btnFit);
        final Button btnNext = findViewById(R.id.btnNext);
        // int pinkColorValue = Color.rgb(247,193,234); // Dejo comentado el color original, por si me sirve en un futuro
        int softPinkColorValue = Color.rgb(243,230,248);

        btnWeight.setOnClickListener(v -> btnWeight.setBackgroundColor(softPinkColorValue));

        btnStrength.setOnClickListener(v -> btnStrength.setBackgroundColor(softPinkColorValue));

        btnFit.setOnClickListener(v -> btnFit.setBackgroundColor(softPinkColorValue));

        btnNext.setOnClickListener(view -> {
            Toast.makeText(activity2.this, "Accediendo a la siguiente pantalla.", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(activity2.this, activity3.class);
                startActivity(intent);
            }, 2000);

        });
    }
}