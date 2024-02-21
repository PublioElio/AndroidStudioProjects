package com.example.ejercicio_10;

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

    final int PERMISSION_REQUEST_INTERNET = 0;
    private TextView textView;
    private Button button;
    private LinearLayout vista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.miTextView);
        button = findViewById(R.id.btnMiBoton);
        vista = findViewById(R.id.miLinearLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                consultarEnlace("https://www.google.com/humans.txt");
            }
        });
    }

    private void consultarEnlace(String enlace) {
        // Comprueblo si los permisos para acceso a Internet han sido concedidos
        // El manifest elijo el de Android
        int permisoLecturaHistorial = ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.INTERNET);

        if (permisoLecturaHistorial != PackageManager.PERMISSION_GRANTED) {
            // Si no tengo concedidos los permisos, los pido
            peticionPermisos(Manifest.permission.INTERNET,
                    new String[]{Manifest.permission.INTERNET},
                    PERMISSION_REQUEST_INTERNET,
                    " a Internet"); // Este es el texto que aparecerá en la ventana que pide los permisos
        } else {
            // Los permisos han sido concedidos, accedo a la URL y leo el contenido
            new HttpRequestTask(textView).execute(enlace).toString();
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
        switch (requestCode) {
            case PERMISSION_REQUEST_INTERNET:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Snackbar.make(vista,
                            "Permiso de acceso a Internet concecido",
                            Snackbar.LENGTH_LONG).show();
                } else {
                    Snackbar.make(vista,
                            "Permiso de acceso a Internet denegado",
                            Snackbar.LENGTH_LONG).show();
                }
        }
    }
}

