package com.example.unidad_07_apuntes_09;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements
        ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int PERMISSION_REQUEST_CONTACTS = 0;
    private static final int PERMISSION_REQUEST_CALL_LOG = 1;
    private Button btnLlamadas;
    private TextView textResultados;
    private LinearLayout vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLlamadas = findViewById(R.id.btnLlamadas);
        textResultados = findViewById(R.id.lblResultados);
        vista = findViewById(R.id.vistaPrincipal);

        // Accedo al content provider público a partir de la API 22
        btnLlamadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarLlamadas();
            }
        });

    }


    private void realizarLlamadas() {
        // Comprueblo si los permisos para la llamada han sido concedidos
        // El manifest elijo el de Android
        int permisoLecturaHistorial = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CALL_LOG);

        if (permisoLecturaHistorial != PackageManager.PERMISSION_GRANTED) {
            // Si no tengo concedidos los permisos, los pido
            peticionPermisos(Manifest.permission.READ_CALL_LOG,
                    new String[]{Manifest.permission.READ_CALL_LOG},
                    PERMISSION_REQUEST_CALL_LOG,
                    " a los contactos"); // Este es el texto que aparecerá en la ventana que pide los permisos
        } else {
            // Los permisos han sido concedidos, compruebo las llamadas
            String columnas[] = {CallLog.Calls.TYPE, CallLog.Calls.NUMBER};
            Uri llamadasUri = CallLog.Calls.CONTENT_URI;
            ContentResolver cr = getContentResolver();
            Cursor cur = cr.query(llamadasUri, columnas, null, null, null);
            if (cur.moveToFirst()) {
                int tipo;
                String tipoLlamadas = "";
                String telefono;

                int colTipo = cur.getColumnIndex(CallLog.Calls.TYPE);
                int colTelefono = cur.getColumnIndex(CallLog.Calls.NUMBER);

                textResultados.setText("");
                do {
                    tipo = cur.getInt(colTipo);
                    telefono = cur.getString(colTelefono);
                    if (tipo == CallLog.Calls.INCOMING_TYPE) {
                        tipoLlamadas = "ENTRADA";
                    } else if (tipo == CallLog.Calls.OUTGOING_TYPE) {
                        tipoLlamadas = "SALIDA";
                    } else if (tipo == CallLog.Calls.MISSED_TYPE) {
                        tipoLlamadas = "PERDIDA";
                    }
                    textResultados.append(tipoLlamadas + " - " + telefono + "\n");
                } while (cur.moveToNext());
            }
        }
    }

    private void peticionPermisos(String permiso, final String[] manifest, final int id,
                                  String tipo) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, permiso)) {
            Snackbar.make(vista,
                    "Es necesario el acceso " + tipo + " para su gestión de la app",
                    Snackbar.LENGTH_INDEFINITE).setAction("ACEPTAR",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            ActivityCompat.requestPermissions(MainActivity.this, manifest, id);
                        }
                    }).show();
        } else {
            ActivityCompat.requestPermissions(MainActivity.this, manifest, id);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case PERMISSION_REQUEST_CALL_LOG:
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Snackbar.make(vista,
                            "Permiso de lectura del historial establecido",
                            Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(vista,
                            "Permiso de lectura de contactos denegado",
                            Snackbar.LENGTH_LONG).show();
                }
            case PERMISSION_REQUEST_CONTACTS:

        }
    }
}