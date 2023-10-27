package com.example.ejemplocontrolesbasicos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Busco el id de la primera etiqueta
        final TextView etiq1 = findViewById(R.id.lbl1);
        // Recupero el id de la etiqueta siete
        final TextView etiq7 = findViewById(R.id.lbl7);

        String texto = etiq1.getText().toString();
        etiq7.setText(texto + " copiado de lbl1");


        // Recojo la id del botón
        final TextView etiqBtn = findViewById(R.id.txtBtn);
        final Button miBoton = findViewById(R.id.btn1);

        miBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etiqBtn.setText("Pulsado botón simple");
            }
        });


        final ToggleButton tglBoton = findViewById(R.id.toggleBtn);

        tglBoton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    etiqBtn.setText("Toggle Button activado");
                } else{
                    etiqBtn.setText("Toggle Button desactivado.");
                }
            }
        });

        // Image Button

        // Cojo el primer botón
        final ImageView img1 = findViewById(R.id.imgBtn1);
        // Establecer una imagen en el segundo botón desde Java
        final ImageButton img2 = findViewById(R.id.imgBtn2);

        img2.setImageResource(R.drawable.pulsa);

        // Creo el manejador de eventos para el botón 2
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // cambio la imagen del primer botón
                img1.setImageResource(R.drawable.feliz);
            }
        });

        // ---------- AÑADIR IMAGEN
        final ImageView imagen = findViewById(R.id.img2);
        imagen.setImageResource(R.drawable.android);

        // ----- autocomplete textview
        // creamos los datos para el actw
        String[] opciones = {"Opción 1", "Opción 2", "Opción 3","Opción 4", "Opción 5"};
        final AutoCompleteTextView textoLeido = findViewById(R.id.acText);
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,opciones);
        textoLeido.setAdapter(adaptador);

        // ---- Multiautocomplete text view
        final MultiAutoCompleteTextView textoLeido2 = findViewById(R.id.macText);
        ArrayAdapter<String> adaptador2 =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line,opciones);
        // Este es el separador de los elementos
        textoLeido2.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        textoLeido2.setAdapter(adaptador2);

    }
}