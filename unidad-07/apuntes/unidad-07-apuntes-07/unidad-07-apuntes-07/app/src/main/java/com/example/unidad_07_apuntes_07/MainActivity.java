package com.example.unidad_07_apuntes_07;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtengo los elementos del layout
        final TextView lblConsulta = findViewById(R.id.lblConsulta);
        final EditText codigo = findViewById(R.id.etCodigo);
        final EditText nombre = findViewById(R.id.etNombre);
        final Button btnActualizar = findViewById(R.id.btnActualizar);
        final Button btnInsertar = findViewById(R.id.btnInsertar);
        final Button btnBorrar = findViewById(R.id.btnBorrar);
        final Button btnCons = findViewById(R.id.btnCons);

        // Abrimos la base de datos en modo escritura
        UsuariosBBDD usuariosBBDD = new UsuariosBBDD(this, "BDUsusarios", null, 1);
        SQLiteDatabase db = usuariosBBDD.getWritableDatabase();

        // Comprobamos que se ha abierto correctamente la BBDD
        if (db != null) {
            //inserto los datos
            btnInsertar.setOnClickListener(view -> {
                String cod = codigo.getText().toString();
                String usuario = nombre.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("codigo", cod);
                registro.put("nombre", usuario);
                if(db.insert("Usuarios", null, registro) < 0){
                    Toast.makeText(this, "ERROR INSERTANDO REGISTRO", Toast.LENGTH_SHORT).show();
                };
            });

            // Borrar datos
            btnBorrar.setOnClickListener(view -> db.execSQL("DELETE FROM Usuarios"));

            // Actualizar datos
            btnActualizar.setOnClickListener(view -> {
                String cod = codigo.getText().toString();
                String usuario = nombre.getText().toString();
                String args[] = {usuario,cod};

                db.execSQL("UPDATE Usuarios SET nombre=? WHERE codigo=?", args);

            });

            // Consultar datos
            btnCons.setOnClickListener(v -> {
                Cursor miCursor = db.rawQuery("SELECT codigo, nombre FROM Usuarios;", null);
                lblConsulta.setText("");
                if (miCursor.moveToFirst()) {
                    do {
                        String cod = miCursor.getString(0);
                        String usuario = miCursor.getString(1);
                        lblConsulta.append(cod + " " + usuario + "\n");
                    } while (miCursor.moveToNext());
                    miCursor.close();
                }
            });

        }
    }
}