package com.example.t4_ejercicio4;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageButton imageBtn = findViewById(R.id.imageBtn);
        final Spinner spinner = findViewById(R.id.spinner);
        final LinearLayout fondo = findViewById(R.id.fondo);
        String[] elementosSpinner = {"Truco", "Trato"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementosSpinner);
        spinner.setAdapter(adapter);

        // Recogemos los datos de la actividad dos
        ActivityResultLauncher<Intent> resultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent intent = result.getData();
                                if (intent != null) {
                                    Bundle extras = intent.getExtras();
                                    if (extras.containsKey("fondo_boton")) {
                                        String resultado = extras.getString("fondo_boton");
                                        if (resultado.equals("fantasma")) {
                                            imageBtn.setImageResource(R.drawable.fantasma);
                                        } else {
                                            imageBtn.setImageResource(R.drawable.calabaza);
                                        }
                                        ColorDrawable colorDrawable = new ColorDrawable(getResources().getColor(R.color.orange));
                                        fondo.setBackground(colorDrawable);
                                    } else {
                                        BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), BitmapFactory.decodeResource(getResources(), R.drawable.fondo6));
                                        fondo.setBackground(bitmapDrawable);
                                    }
                                }
                            }
                        });

        imageBtn.setOnClickListener(view -> {
            Intent intent;
            if (spinner.getSelectedItem().toString().equals("Trato")) {
                intent = new Intent(MainActivity.this, Activity2.class);
            } else {
                intent = new Intent(MainActivity.this, Activity3.class);
            }
            resultLauncher.launch(intent); // ¡No olvidar cambiar el de startActivity() a launch(intent)!
        });


    }
}