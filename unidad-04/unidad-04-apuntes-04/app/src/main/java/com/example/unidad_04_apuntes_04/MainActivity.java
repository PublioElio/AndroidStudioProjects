package com.example.unidad_04_apuntes_04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // esto es porque he puesto el onClick en el xml y hay que crear el método fuera del onCreate()
    public void onClick(View view){
        Intent intent = new Intent(this, Actividad2.class);
        startActivity(intent);
    }
}