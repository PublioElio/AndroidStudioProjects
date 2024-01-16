package com.example.unidad_07_apuntes_03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Escribo en la memoria interna del dispositivo
        try{
            OutputStreamWriter miFichero =
                    new OutputStreamWriter(openFileOutput("fichero.txt", MODE_PRIVATE));
            miFichero.write("TEXTO DE PRUEBA");
            miFichero.close();
        }catch (Exception e){
            Log.e("Ficheros", "Error al escribir en memoria interna");
        }

        // Recupero la información de memoria interna
        try{
            BufferedReader miFichero =
                    new BufferedReader(new InputStreamReader(openFileInput("fichero.txt")));
            String texto = miFichero.readLine();
            miFichero.close();
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            Log.e("Ficheros", "Error al escribir en memoria interna");
        }
    }
}