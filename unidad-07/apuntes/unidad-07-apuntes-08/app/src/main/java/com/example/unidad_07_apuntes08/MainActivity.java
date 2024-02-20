package com.example.unidad_07_apuntes08;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // ELementos del layout
    private Button btnInsertar;
    private Button btnBorrar;
    private Button btnEliminar;

    private Button btnConsultar;
    private Button btnLlamadas;
    private TextView txtResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtengo las referencias
        txtResultados = findViewById(R.id.lblResultados);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnEliminar = findViewById(R.id.btnBorrar);
        btnLlamadas = findViewById(R.id.btnLlamadas);

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Columnas a recuperar
                String[] columnas = {
                        ClientesProvider.Clientes._ID,
                        ClientesProvider.Clientes.COL_NOMBRE,
                        ClientesProvider.Clientes.COL_TELEFONO,
                        ClientesProvider.Clientes.COL_EMAIL
                };

                Uri clientesUri = ClientesProvider.CONTENT_URI;
                ContentResolver cr = getContentResolver();
                // Hacemos la consulta
                Cursor cursor = cr.query(
                        clientesUri,
                        columnas, // Columnas a devolver
                        null, // Condición de la consulta
                        null, // Argumentos de la consulta
                        null // Orden de los resultados
                );

                if (cursor != null) {
                    if (cursor.moveToFirst()) {
                        String nombre;
                        String tlfn;
                        String email;

                        int colNombre = cursor.getColumnIndex(ClientesProvider.Clientes.COL_NOMBRE);
                        int colTelefono = cursor.getColumnIndex(ClientesProvider.Clientes.COL_TELEFONO);
                        int colEmail = cursor.getColumnIndex(ClientesProvider.Clientes.COL_EMAIL);

                        txtResultados.setText("");

                        do {
                            nombre = cursor.getString(colNombre);
                            tlfn = cursor.getString(colTelefono);
                            email = cursor.getString(colEmail);

                            txtResultados.append(nombre + " " + tlfn + " " + email + "\n");

                        } while (cursor.moveToNext());
                    }
                }
            }
        });

        // --------------------------------------------------------------------
        // Inserto datos con el Content Resolver en el Content Provides
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues registro = new ContentValues();
                registro.put(ClientesProvider.Clientes.COL_NOMBRE, "Cliente nuevo");
                registro.put(ClientesProvider.Clientes.COL_TELEFONO, "999111222");
                registro.put(ClientesProvider.Clientes.COL_EMAIL, "nuevo@gmail.com");
                ContentResolver cr = getContentResolver();
                cr.insert(ClientesProvider.CONTENT_URI, registro);
            }
        });

        // Borrar datos del content provider con el content resolver
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver cr = getContentResolver();
                cr.delete(ClientesProvider.CONTENT_URI,
                        ClientesProvider.Clientes.COL_NOMBRE + "= 'Cliente nuevo'",
                        null);
            }
        });
    }


}