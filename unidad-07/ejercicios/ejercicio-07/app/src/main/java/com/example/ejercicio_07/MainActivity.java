package com.example.ejercicio_07;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtengo los elementos del layout
        final EditText editTexCodigo = findViewById(R.id.editTextCodigo);
        final EditText editTexNombre = findViewById(R.id.editTextNombre);
        final EditText editTexApellidos = findViewById(R.id.editTextApellidos);
        final Button btnActBorr = findViewById(R.id.btnActBorr);
        final Button btnInsertar = findViewById(R.id.btnIns);
        final Button btnCons = findViewById(R.id.btnCons);
        final Button btnBorrar = findViewById(R.id.btnBorrar);
        final Button btnActualizar = findViewById(R.id.btnActualizar);
        final TextView textViewCons = findViewById(R.id.textViewCons);
        final LinearLayout linearLayoutCons = findViewById(R.id.linearLayoutCons);
        final LinearLayout linearLayoutActBorr = findViewById(R.id.linearLayoutActBorr);

        // Abrimos la base de datos en modo escritura
        EscritoresBBDD escritoresBBDD = new EscritoresBBDD(this, "BDEscritories", null, 1);
        SQLiteDatabase db = escritoresBBDD.getWritableDatabase();


        if (db != null) {
            Log.i("BBDD", "Se ha creado la base de datos correctamente");
            // Insertamos los datos
            btnInsertar.setOnClickListener(view -> {
                String nombre = editTexNombre.getText().toString();
                String apellidos = editTexApellidos.getText().toString();

                ContentValues registro = new ContentValues();
                registro.put("nombre", nombre);
                registro.put("apellidos", apellidos);
                if (db.insert("Escritores", null, registro) < 0) {
                    Toast.makeText(MainActivity.this, "ERROR INSERTANDO REGISTRO", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "REGISTRO INSERTADO CORRECTAMENTE", Toast.LENGTH_LONG).show();
                    resetearFormulario(editTexCodigo, editTexNombre, editTexApellidos);
                    ocultarLayouts(linearLayoutCons, linearLayoutActBorr);
                }

            });

            // Consultar datos
            btnCons.setOnClickListener(view -> {
                Cursor miCursor = db.rawQuery("SELECT codigo, nombre, apellidos FROM Escritores", null);
                textViewCons.setText("");
                if (miCursor.moveToFirst()) {
                    do {
                        String codigo = miCursor.getString(0);
                        String nombre = miCursor.getString(1);
                        String apellidos = miCursor.getString(2);
                        textViewCons.append(codigo + " " + nombre + " " + apellidos + "\n");
                    } while (miCursor.moveToNext());
                    linearLayoutCons.setVisibility(View.VISIBLE);
                    miCursor.close();
                }
            });

            // Borramos datos
            btnBorrar.setOnClickListener(view -> {
                String codigoParaBorrar = editTexCodigo.getText().toString();

                if (!codigoParaBorrar.isEmpty()) {
                    String whereClause = "codigo = ?";
                    String[] whereArgs = {codigoParaBorrar};

                    int numEntradasBorradas = db.delete("Escritores", whereClause, whereArgs);

                    if (numEntradasBorradas > 0) {
                        Toast.makeText(MainActivity.this, "ENTRADA BORRADA CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        resetearFormulario(editTexCodigo, editTexNombre, editTexApellidos);
                        ocultarLayouts(linearLayoutCons, linearLayoutActBorr);
                    } else {
                        Toast.makeText(MainActivity.this, "NO SE ENCONTRÓ NINGUNA ENTRADA CON EL CÓDIGO PROPORCIONADO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "ERROR. CÓDIGO NO VÁLIDO", Toast.LENGTH_LONG).show();
                }
            });

            // Mostramos las opciones para actualizar o borrar
            btnActBorr.setOnClickListener(view -> {
                linearLayoutActBorr.setVisibility(View.VISIBLE);
                resetearFormulario(editTexCodigo, editTexNombre, editTexApellidos);
                linearLayoutCons.setVisibility(View.GONE);
            });

            // Actualizamos la base de datos
            btnActualizar.setOnClickListener(view -> {
                try {
                    String codigo = editTexCodigo.getText().toString();
                    String nombre = editTexNombre.getText().toString();
                    String apellidos = editTexApellidos.getText().toString();
                    // Otra forma sería:
                    // String updateArgs[] = {nombre, apellidos, codigo};
                    // db.execSQL("UPDATE Usuarios SET nombre=?, apellidos=? WHERE codigo=?", updateArgs);

                    int numRowsUpdated = db.update("Escritores", createContentValues(nombre, apellidos), "codigo=?", new String[]{codigo});
                    if (numRowsUpdated > 0) {
                        Toast.makeText(MainActivity.this, "ENTRADA ACTUALIZADA CORRECTAMENTE", Toast.LENGTH_LONG).show();
                        resetearFormulario(editTexCodigo, editTexApellidos, editTexNombre);
                        ocultarLayouts(linearLayoutCons, linearLayoutActBorr);
                    } else {
                        Toast.makeText(MainActivity.this, "NO SE ENCONTRÓ NINGUNA ENTRADA CON EL CÓDIGO ESPECIFICADO", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "ERROR AL ACTUALIZAR LA ENTRADA: " + e.getMessage(), Toast.LENGTH_LONG).show();throw new RuntimeException(e);
                }
            });


        }
    }

    private ContentValues createContentValues(String nombre, String apellidos) {
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("apellidos", apellidos);
        return values;
    }

    private static void ocultarLayouts(LinearLayout linearLayoutCons, LinearLayout linearLayoutActBorr) {
        linearLayoutCons.setVisibility(View.GONE);
        linearLayoutActBorr.setVisibility(View.GONE);
    }

    private static void resetearFormulario(EditText editTexCodigo, EditText editTexNombre, EditText editTexApellidos) {
        editTexCodigo.setText("");
        editTexNombre.setText("");
        editTexApellidos.setText("");
    }


}