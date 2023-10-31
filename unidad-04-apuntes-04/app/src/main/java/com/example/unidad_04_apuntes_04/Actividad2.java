package com.example.unidad_04_apuntes_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Actividad2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);
        final Button boton = findViewById(R.id.btnActividad2);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Actividad2.this, MainActivity.class); // como está dentro del onCreate, como primer parámetro hay que pasarle el Actividad2.this
                startActivity(intent);
            }
        });
    }
}