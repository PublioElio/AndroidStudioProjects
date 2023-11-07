package com.example.t4_ejercicio4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
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
        String[] elementosSpinner = {"Truco", "Trato"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, elementosSpinner);
        spinner.setAdapter(adapter);

        // Recogemos los datos de la actividad dos
        ActivityResultLauncher<Intent> resultLauncher =
                registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                        new ActivityResultCallback<ActivityResult>() {
                            @Override
                            public void onActivityResult(ActivityResult result) {
                                if (result.getResultCode() == Activity.RESULT_OK) {
                                    Intent intent = result.getData();
                                    if (intent != null) {
                                        Bundle extras = intent.getExtras();
                                        String resultado = extras.getString("fondo_boton");
                                        final ImageButton imageBtn = findViewById(R.id.imageBtn);
                                        if(resultado.equals("fantasma")){
                                            imageBtn.setImageResource(R.drawable.fantasma);
                                        } else {
                                            imageBtn.setImageResource(R.drawable.calabaza);
                                        }
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
            resultLauncher.launch(intent); // ¡No olvidar cambiar el de startActivity() a launch(intent)
        });


    }
}