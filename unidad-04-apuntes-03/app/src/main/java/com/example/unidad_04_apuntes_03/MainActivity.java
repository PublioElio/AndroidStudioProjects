package com.example.unidad_04_apuntes_03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Intent ejemplo = new Intent(this, Actividad2.class); // creo el intent con la Actividad 2
        startActivity(ejemplo); // Inicio la actividad
    }
}