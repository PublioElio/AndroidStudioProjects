package com.example.ejercicio_06;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargo elementos:
        final Spinner spinnerHeroes = findViewById(R.id.spinnerHeroes);
        final ImageView imageViewHeroe = findViewById(R.id.ivHeroe);
        final Button btnCargar = findViewById(R.id.btnCargar);
        final Button btnGuardar = findViewById(R.id.btnGuardar);
        final Button btnBorrar = findViewById(R.id.btnBorrar);
        final Button btnAnterior = findViewById(R.id.btnAnterior);
        final Button btnSiguiente = findViewById(R.id.btnSiguiente);

        ArrayList<Integer> referenciasCargadas = new ArrayList<Integer>();

        // Creo los datos para el spinner, los introduzco en el adaptador y luego asigno el adaptador al spinner
        ArrayList<Heroe> listaHeroes = new ArrayList<>();
        introducirHeroes(listaHeroes);
        Adaptador adaptador = new Adaptador(this, listaHeroes);
        spinnerHeroes.setAdapter(adaptador);

        // Comprobar el estado de la memoria externa (SD)
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
            // Método para guardar la referencia de la imagen en la SD
            guardarEnSD(spinnerHeroes, btnGuardar);
            borrarContenidoSD(imageViewHeroe, btnBorrar, btnAnterior, btnSiguiente, referenciasCargadas);
        } else {
            Log.e("Ficheros", "La tarjeta SD no está disponible para acceso o escritura");
        }


        if (sdDisponible) {
            cargarContenidoSD(imageViewHeroe, btnCargar, btnAnterior, btnSiguiente, referenciasCargadas);
        } else {
            Log.e("Ficheros", "Tarjeta de memoria SD no disponible");
        }

        btnSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

    }

    private void cargarContenidoSD(ImageView imageViewHeroe, Button btnCargar, Button btnAnterior, Button btnSiguiente, ArrayList<Integer> referenciasCargadas) {
        btnCargar.setOnClickListener(view -> {
            referenciasCargadas.clear();
            try {
                File ruta_sd = getExternalFilesDir(null);
                File f = new File(ruta_sd.getAbsolutePath(), "lista_heroes.txt");
                BufferedReader fin =
                        new BufferedReader(
                                new InputStreamReader(new FileInputStream(f)));
                String linea = fin.readLine();
                while (linea != null) {
                    Integer referencia = Integer.valueOf(linea);
                    referenciasCargadas.add(referencia);
                    Log.i("Ficheros", "Referencia leída desde la memoria externa (SD): " + referencia);
                    linea = fin.readLine();
                }
                fin.close();
                if(!referenciasCargadas.isEmpty()){
                    btnAnterior.setVisibility(View.VISIBLE);
                    btnAnterior.setEnabled(false);
                    btnSiguiente.setVisibility(View.VISIBLE);
                    if(referenciasCargadas.size() > 1){
                        btnSiguiente.setEnabled(true);
                    }
                    imageViewHeroe.setVisibility(View.VISIBLE);
                    imageViewHeroe.setImageResource(referenciasCargadas.get(0));
                }
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al leer en la tarjeta SD");
            }
        });
    }

    private void borrarContenidoSD(ImageView imageViewHeroe, Button btnBorrar, Button btnAnterior, Button btnSiguiente, ArrayList<Integer> referenciasCargadas) {
        btnBorrar.setOnClickListener(view -> {
            referenciasCargadas.clear();
            try {
                File ruta_sd = getExternalFilesDir(null);
                assert ruta_sd != null;
                File f = new File(ruta_sd.getAbsolutePath(), "lista_heroes.txt");
                if (f.exists()) {
                    PrintWriter writer = new PrintWriter(f);
                    writer.print("");
                    writer.close();
                } else {
                    f.createNewFile();
                }
                btnAnterior.setVisibility(View.INVISIBLE);
                btnAnterior.setEnabled(false);
                btnSiguiente.setVisibility(View.INVISIBLE);
                btnSiguiente.setEnabled(false);
                imageViewHeroe.setVisibility(View.INVISIBLE);
                imageViewHeroe.setImageDrawable(null);
                Log.i("Ficheros", "Se ha borrado el contenido de la tarjeta SD");
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al escribir en la tarjeta SD");
            }
        });
    }

    private void guardarEnSD(Spinner spinnerHeroes, Button btnGuardar) {
        btnGuardar.setOnClickListener(view -> {
            try {
                File ruta_sd = getExternalFilesDir(null);
                assert ruta_sd != null;
                File f = new File(ruta_sd.getAbsolutePath(), "lista_heroes.txt");
                OutputStreamWriter fout = new OutputStreamWriter(new FileOutputStream(f, true));

                int imgHeroe = -1;
                int posicionActual = spinnerHeroes.getSelectedItemPosition();
                Object itemSeleccionado = spinnerHeroes.getItemAtPosition(posicionActual);
                if (itemSeleccionado instanceof Heroe) {
                    Heroe heroeSeleccionado = (Heroe) itemSeleccionado;
                    imgHeroe = heroeSeleccionado.getimgHeroe();
                }
                fout.write(imgHeroe + "\n");
                fout.close();
                Log.i("Ficheros", "Se ha guardado en la tarjeta SD la referencia: " + imgHeroe);
            } catch (Exception ex) {
                Log.e("Ficheros", "Error al escribir en la tarjeta SD");
            }
        });
    }

    private void introducirHeroes(ArrayList<Heroe> listaHeroes){
        listaHeroes.add(new Heroe(R.drawable.batman));
        listaHeroes.add(new Heroe(R.drawable.capi));
        listaHeroes.add(new Heroe(R.drawable.deadpool));
        listaHeroes.add(new Heroe(R.drawable.furia));
        listaHeroes.add(new Heroe(R.drawable.hulk));
        listaHeroes.add(new Heroe(R.drawable.ironman));
        listaHeroes.add(new Heroe(R.drawable.lobezno));
        listaHeroes.add(new Heroe(R.drawable.spiderman));
        listaHeroes.add(new Heroe(R.drawable.thor));
        listaHeroes.add(new Heroe(R.drawable.wonderwoman));

    }
}