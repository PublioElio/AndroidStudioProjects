package com.example.unidad_07_apuntes_05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // ESTE PROGRAMA FUNCIONA SOLO HASTA LA API 29 - SIN INCLUIR
        // PARA QUE FUNCIONE EN TODAS LAS APIS, EJECUTAR LAS LÍNEAS COMENTADAS

        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;

        // Comprobamos el estado de la memoria SD
        String estado = Environment.getExternalStorageState();
        if(estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdAccesoEscritura = true;
        } else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            sdDisponible = true;
        }

        // Escribo en la tarjeta SD
        if(sdDisponible && sdAccesoEscritura){
            try{
                // Obtengo la ruta del directorio raíz
                //File ruta_sd = Environment.getExternalStorageDirectory(); // Este es el método que funciona hasta la API 28
                File ruta_sd = getExternalFilesDir(null); // Este método es el más reciente
                File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));
                fout.write("TEXTO DE PRUEBA");
                fout.close();
            } catch (IOException e) {
                Log.e("Ficheros", "Error al escribir en la tarjeta SD");
            }
        }

        // Leo los datos, así que no necesito los permisos de escritura
        if(sdDisponible){
            try{
                // Obtengo la ruta del directorio raíz
                // File ruta_sd = Environment.getExternalStorageDirectory(); // Este es el método que funciona hasta la API 28
                File ruta_sd = getExternalFilesDir(null); // Este método es el más reciente
                File f = new File(ruta_sd.getAbsolutePath(), "prueba_sd.txt");

                BufferedReader fin =
                        new BufferedReader(new InputStreamReader(new FileInputStream(f)));
                String texto = fin.readLine();
                Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
                fin.close();
            } catch (IOException e) {
                Log.e("Ficheros", "Error al leer en la tarjeta SD");
            }
        }

    }
}