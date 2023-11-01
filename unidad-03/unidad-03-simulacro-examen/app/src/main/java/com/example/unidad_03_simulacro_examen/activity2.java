package com.example.unidad_03_simulacro_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

        btnWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWeight.setBackgroundColor(softPinkColorValue);
            }
        });

        btnStrength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStrength.setBackgroundColor(softPinkColorValue);
            }
        });

        btnFit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnFit.setBackgroundColor(softPinkColorValue);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity2.this, "Accediendo a la siguiente pantalla.", Toast.LENGTH_SHORT).show();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(activity2.this, activity3.class);
                        startActivity(intent);
                    }
                }, 2000);

            }
        });
    }
}