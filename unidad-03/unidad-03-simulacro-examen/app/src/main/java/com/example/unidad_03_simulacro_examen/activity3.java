package com.example.unidad_03_simulacro_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity3);

        final TextView txtDays = findViewById(R.id.txtDays);
        final Spinner spDays = findViewById(R.id.spDays);
        String[] elementosSpinner = {"1 día", "2 días", "3 días", "4 días", "5 días", "6 días", "7 días"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementosSpinner);
        spDays.setAdapter(adapter);
        
        spDays.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String cadena = adapter.getItem(position);
                txtDays.setText(cadena);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        
    }
}