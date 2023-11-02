package com.example.unidad_03_simulacro_examen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final LinearLayout loginArea = findViewById(R.id.loginArea);

        final Handler handler = new Handler();
        handler.postDelayed(() -> loginArea.setVisibility(View.VISIBLE), 3000);
    }

    public void onClick(View view) {
        final TextView userName = findViewById(R.id.userName);
        final TextView pwd = findViewById(R.id.pwd);

        if (userName.getText().toString().isEmpty() || pwd.getText().toString().isEmpty()) {
            Toast.makeText(MainActivity.this, "Debe introducir un nombre de usuario y contraseña válidos", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Hola " + userName.getText() + ". Accediendo a la app", Toast.LENGTH_SHORT).show();
            final Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(MainActivity.this, activity2.class);
                startActivity(intent);
            }, 2000);
        }
    }
}