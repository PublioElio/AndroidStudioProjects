package com.example.unidad_05_ejercicios_09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero el elemento del layout y defino los valores del listado
        final ListView miLista = findViewById(R.id.miLista);
        final Button btnAceptar = findViewById(R.id.btnAceptar);

        // Creo los datos
        ArrayList<Datos> datos = new ArrayList<>();

        introducirDatos(datos);

        // Creo el adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        miLista.setAdapter(adaptador);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int totalDispSeleccionados = 0, numItemsEnAdaptador = adaptador.getCount();
                String nombresDispositivos[] = new String[numItemsEnAdaptador],
                        paraNavegar = "Para navegar por Internet utilizas ";

                // Obtengo un listado de los nombres de los items seleccionados, precedidos de un artículo (el, la)
                for (int i = 0; i < numItemsEnAdaptador; i++) {
                    Datos datos = (Datos) adaptador.getItem(i);
                    if (datos.isChecked()) {
                        nombresDispositivos[totalDispSeleccionados++] = datos.getTexto2()
                                + datos.getTexto().toLowerCase();
                    }
                }

                // En función del número de dispositivos seleccionados, muestro un toast diferente
                if (totalDispSeleccionados == 0) {
                    Toast.makeText(MainActivity.this, "No has seleccionado ninguna opción",
                            Toast.LENGTH_LONG).show();
                } else if (totalDispSeleccionados == 1) {
                    Toast.makeText(MainActivity.this, paraNavegar
                            + nombresDispositivos[--totalDispSeleccionados], Toast.LENGTH_LONG).show();
                } else {
                    while (totalDispSeleccionados > 0) {
                        if (totalDispSeleccionados == 1) {
                            paraNavegar = paraNavegar.substring(0, paraNavegar.length() - 2); // le quito la última coma  y el espacio a la cadena anterior
                            paraNavegar += " y " + nombresDispositivos[--totalDispSeleccionados];
                        } else {
                            paraNavegar += nombresDispositivos[--totalDispSeleccionados] + ", ";
                        }
                    }
                    Toast.makeText(MainActivity.this, paraNavegar,
                            Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private static void introducirDatos(ArrayList<Datos> datos) {
        datos.add(new Datos(R.drawable.tv, "Televisión", "la ", false));
        datos.add(new Datos(R.drawable.smartphone, "Teléfono móvil", "el ", false));
        datos.add(new Datos(R.drawable.tablet, "Tablet", "la ", false));
        datos.add(new Datos(R.drawable.ordenador_fijo, "Ordenador fijo", "el ", false));
        datos.add(new Datos(R.drawable.ordenador_portatil, "Ordenador portátil", "el ", false));
        datos.add(new Datos(R.drawable.reloj, "Reloj", "el ", false));
    }
}
