package com.example.practica_exam_adiaz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout linearLayoutAdd = findViewById(R.id.linearLayoutAdd);
        LinearLayout linearLayoutQuitar = findViewById(R.id.linearLayoutQuitar);
        Button btnAdd = findViewById(R.id.btnAdd);
        Button btnAddCancelar = findViewById(R.id.btnAddCancelar);
        Button btnQuitar = findViewById(R.id.btnQuitar);
        Button btnQuitarCancelar = findViewById(R.id.btnQuitarCancelar);
        ListView listViewTendencias = findViewById(R.id.listViewTendencias);
        ListView listViewMiLista = findViewById(R.id.listViewMiLista);

    }
}