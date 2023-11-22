package com.example.unidad_05_ejercicios_07;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
        final TextView miTextView = findViewById(R.id.miTextView);

        // Creo los datos
        ArrayList<Datos> datos = new ArrayList<>();

        introducirDatos(datos);

        // Creo el adaptador
        Adaptador adaptador = new Adaptador(this, datos);
        miLista.setAdapter(adaptador);

        // Inserto el listener
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String seleccionado = ((Datos) parent.getItemAtPosition(position)).getTexto2();
                miTextView.setText(seleccionado);

            }
        });

    }

    private static void introducirDatos(ArrayList<Datos> datos) {
        datos.add(new Datos(R.drawable.ima1, "DONUTS", "El 15 de septiembre de 2009, " +
                "fue lanzado el SDK de Android 1.6 Donut, " +
                "basado en el núcleo Linux 2.6.29. " +
                "En la actualización se incluyen numerosas características nuevas."));

        datos.add(new Datos(R.drawable.ima2, "FROYO", "El 20 de mayo de 2010, " +
                "El SDK de Android 2.2 Froyo (Yogur helado) fue lanzado, " +
                "basado en el núcleo Linux 2.6.32."));

        datos.add(new Datos(R.drawable.ima3, "GINGERBREAD",
                "El 6 de diciembre de 2010, el SDK de Android 2.3 Gingerbread " +
                        "(Pan de Jengibre) fue lanzado, basado en el núcleo Linux 2.6.35."));

        datos.add(new Datos(R.drawable.ima4, "HONEYCOMB", "El 22 de febrero de 2011, " +
                "sale el SDK de Android 3.0 Honeycomb (Panal de Miel). " +
                "Fue la primera actualización exclusiva para TV y tableta, " +
                "lo que quiere decir que sólo es apta para TV y tabletas y no para teléfonos Android."));

        datos.add(new Datos(R.drawable.ima5, "ICE CREAM", "El SDK para Android" +
                " 4.0.0 Ice Cream Sandwich (Sándwich de Helado), " +
                "basado en el núcleo de Linux 3.0.1, " +
                "fue lanzado públicamente el 12 de octubre de 2011."));

        datos.add(new Datos(R.drawable.ima6, "JELLY BEAN", "Google anunció Android " +
                "4.1 Jelly Bean (Gomita Confitada o Gominola) en la conferencia del " +
                "30 de junio de 2012. Basado en el núcleo de linux 3.0.31, " +
                "Bean fue una actualización incremental con el enfoque primario de mejorar " +
                "la funcionalidad y el rendimiento de la interfaz de usuario."));

        datos.add(new Datos(R.drawable.ima7, "KITKAT", "Su nombre se debe a " +
                "la chocolatina KitKat, de la empresa internacional Nestlé. " +
                "Posibilidad de impresión mediante WIFI. " +
                "WebViews basadas en el motor de Chromium."));

        datos.add(new Datos(R.drawable.ima8, "LOLLIPOP", "Incluye Material Design," +
                " un diseño intrépido, colorido, y sensible interfaz de usuario para " +
                "las experiencias coherentes e intuitivos en todos los dispositivos. " +
                "Movimiento de respuesta natural, iluminación y sombras realistas y" +
                " familiares elementos visuales hacen que sea más fácil de navegar su dispositivo."));
    }
}