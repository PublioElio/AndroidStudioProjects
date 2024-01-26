package com.example.ejemplomemoriaexterna;

import static android.os.Environment.DIRECTORY_RINGTONES;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

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

        boolean sdDisponible = false;
        boolean sdAccesoEscritura = false;

        // Comprobamos el estado de la memoria externa (SD)
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED)){
            sdDisponible = true;
            sdAccesoEscritura = true;
        }
        else if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            sdDisponible = true;
        }

        // Escribo en la tarjeta
        if (sdDisponible && sdAccesoEscritura){

            try {
                // Obtengo la ruta del directorio raíz (solo disponible hasta API 29)
                // File ruta_sd = Environment.getExternalStorageDirectory();

                // Ruta para directorios predefinidos (opción actual y recomendada)
                 File ruta_sd = getExternalFilesDir(null);

                 // Ruta para directorios clasificados por tipo. El ejemplo no sería válido para nuestro tipo de dato
                 //File ruta_sd = getExternalFilesDir(DIRECTORY_RINGTONES);

                File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f));

                fout.write("Texto de prueba");
                fout.close();
            }
            catch (Exception ex){
                Log.e("Ficheros","Error al escribir en la tarjeta SD");
            }

            // Leemos
            try {
                File ruta_sd = getExternalFilesDir(null);
                File f = new File(ruta_sd.getAbsolutePath(),"prueba_sd.txt");

                BufferedReader fin =
                        new BufferedReader(
                            new InputStreamReader(
                                new FileInputStream(f)));
                String texto = fin.readLine();
                Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
                fin.close();
            }
            catch (Exception ex){
                Log.e("Ficheros","Error al leer en la tarjeta SD");
            }
        }



    }
}