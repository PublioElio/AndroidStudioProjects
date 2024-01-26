package com.example.ejercicio_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo las variables
        final Button guardar = findViewById(R.id.btnGuardar);
        final Button recuperar = findViewById(R.id.btnRecuperar);
        final EditText myEditText = findViewById(R.id.myEditTxt);
        final TextView myTextView = findViewById(R.id.myTextView);

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

        if (sdDisponible && sdAccesoEscritura) {

            guardar.setOnClickListener(view -> {
                try {
                    File ruta_sd = getExternalFilesDir(null);
                    File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
                    OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
                    fout.write(myEditText.getText().toString());
                    fout.close();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al escribir en la tarjeta SD");
                }
            });
        } else {
            Log.e("Ficheros", "La tarjeta SD no está disponible para acceso o escritura");
        }

        if (sdDisponible) {
            recuperar.setOnClickListener(view -> {
                try {
                    File ruta_sd = getExternalFilesDir(null);
                    File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
                    BufferedReader fin =
                            new BufferedReader(
                                    new InputStreamReader(new FileInputStream(f)));
                    myTextView.setText(fin.readLine());
                    fin.close();
                } catch (Exception ex) {
                    Log.e("Ficheros", "Error al leer en la tarjeta SD");
                }
            });
        } else {
            Log.e("Ficheros", "Tarjeta de memoria SD no disponible");
        }
    }
}