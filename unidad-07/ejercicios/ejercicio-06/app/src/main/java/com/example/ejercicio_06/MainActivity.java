package com.example.ejercicio_06;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargo elementos:
        final Spinner heroSpinner = findViewById(R.id.heroSpinner);
        final Button cargar = findViewById(R.id.btnCargar);
        final Button guardar = findViewById(R.id.btnGuardar);
        final Button borrar = findViewById(R.id.btnBorrar);

        // Creo los datos
        ArrayList<Data> datos = new ArrayList<>();

        enterData(datos);

        // Creo el adaptador
        Adapter adapter = new Adapter(this, datos);
        heroSpinner.setAdapter(adapter);

        // Hay que comprobar el estado de la memoria externa (SD)
        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)) {
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
        }

        // Guardamos en la tarjeta
        if (sdDisponible && sdAccesoEscritura) {
            saveOnSD(heroSpinner, guardar);
            deleteOnSD(borrar);
        } else {
            Log.e("Ficheros", "La tarjeta SD no está disponible para acceso o escritura");
        }

    }

    /**
     * Este método guarda en la tarjeta SD la ID del héroe seleccionado en el Spinner superior
     *
     */
    private void saveOnSD(Spinner heroSpinner, Button guardar) {
        guardar.setOnClickListener(view -> {
            try {
                String heroId = null;
                File ruta_sd = getExternalFilesDir(null);
                assert ruta_sd != null;
                File f = new File(ruta_sd.getAbsolutePath(), "heroes.txt");
                int currentPosition = heroSpinner.getSelectedItemPosition();
                Object selectedItem = heroSpinner.getItemAtPosition(currentPosition);
                if (selectedItem instanceof Data) {
                    Data selectedData = (Data) selectedItem;
                    heroId = selectedData.getheroId();
                }
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
                if (heroId != null) {
                    fout.write(heroId);
                    Log.i("Ficheros", "Guardado el siguiente héroe en el fichero: " + heroId);
                }
                fout.close();
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al escribir en la tarjeta SD");
            }
        });
    }

    private void deleteOnSD(Button borrar) {
        borrar.setOnClickListener(view -> {
            try {
                File ruta_sd = getExternalFilesDir(null);
                assert ruta_sd != null;
                File f = new File(ruta_sd.getAbsolutePath(), "heroes.txt");
                if (f.exists()) {
                    PrintWriter writer = new PrintWriter(f);
                    writer.print("");
                    writer.close();
                } else {
                    f.createNewFile();
                }
                Log.i("Ficheros", "Contenido del archivo heroes.txt borrado");
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al borrar contenido de la tarjeta SD");
            }
        });

    }

    private void enterData(ArrayList<Data> datos) {
        datos.add(new Data(R.drawable.capi, "capi"));
        datos.add(new Data(R.drawable.batman, "batman"));
        datos.add(new Data(R.drawable.deadpool, "deadpool"));
        datos.add(new Data(R.drawable.furia, "furia"));
        datos.add(new Data(R.drawable.hulk, "hulk"));
        datos.add(new Data(R.drawable.ironman, "ironman"));
        datos.add(new Data(R.drawable.lobezno, "lobezno"));
        datos.add(new Data(R.drawable.spiderman, "spiderman"));
        datos.add(new Data(R.drawable.thor, "thor"));
        datos.add(new Data(R.drawable.wonderwoman, "wonderwoman"));
    }
}