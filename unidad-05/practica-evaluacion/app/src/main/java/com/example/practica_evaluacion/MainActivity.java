package com.example.practica_evaluacion;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import principal.activity.PrincipalActivity;

/**
 * El main activity es la portada de la aplicación, con un botón de acceso y el logo
 */
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button bntAcceder = findViewById(R.id.btnAcceder);

        bntAcceder.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, PrincipalActivity.class);
            startActivity(intent);
        });
    }
}