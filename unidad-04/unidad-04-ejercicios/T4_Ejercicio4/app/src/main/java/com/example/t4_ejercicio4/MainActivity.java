package com.example.t4_ejercicio4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton imageBtn = findViewById(R.id.imageBtn);
        final Spinner spinner = findViewById(R.id.spinner);
        String[] elementosSpinner = {"Truco", "Trato"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementosSpinner);
        spinner.setAdapter(adapter);

        imageBtn.setOnClickListener(view -> {
            Intent intent;
            if(spinner.getSelectedItem().toString().equals("Trato")){
                intent = new Intent(MainActivity.this, Activity2.class);
            }else {
                intent = new Intent(MainActivity.this, Activity3.class);
            }
            startActivity(intent);
        });


    }
}