package com.example.t4_ejercicio4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        // Comprobamos qué vista (botón) ha invocado al método
        int id = view.getId();
        if (id == R.id.fantasmas) {
            intent.putExtra("fondo_boton", "fantasma");
            setResult(RESULT_OK, intent);
        } else if (id == R.id.calabazas) {
            intent.putExtra("fondo_boton", "calabaza");
            setResult(RESULT_OK, intent);
        }
        finish();
    }

}