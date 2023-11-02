package com.example.examentema3adrianodiaz;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        final int PINK = Color.rgb(247, 193, 234);
        final int SOFT_PINK = Color.rgb(243, 230, 248);
        final Button btnNext = findViewById(R.id.btnNext);
        final Button btnFit = findViewById(R.id.btnFit);
        final Button btnStrength = findViewById(R.id.btnStrength);
        final Button btnWeight = findViewById(R.id.btnWeight);

        btnWeight.setOnClickListener(view -> {
            btnFit.setBackgroundColor(PINK);
            btnStrength.setBackgroundColor(PINK);
            btnWeight.setBackgroundColor(SOFT_PINK);
        });
        btnFit.setOnClickListener(view -> {
            btnFit.setBackgroundColor(SOFT_PINK);
            btnStrength.setBackgroundColor(PINK);
            btnWeight.setBackgroundColor(PINK);
        });

        btnStrength.setOnClickListener(view -> {
            btnFit.setBackgroundColor(PINK);
            btnStrength.setBackgroundColor(SOFT_PINK);
            btnWeight.setBackgroundColor(PINK);
        });

        btnNext.setOnClickListener(view -> {
            Toast.makeText(Activity2.this, "Accediendo a la siguiente pantalla", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Activity2.this, Activity3.class);
            startActivity(intent);
        });

    }
}