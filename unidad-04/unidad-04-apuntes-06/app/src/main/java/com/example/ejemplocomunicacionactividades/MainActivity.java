package com.example.ejemplocomunicacionactividades;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtengo los datos al pulsar el botón
        final EditText etNombre = findViewById(R.id.etNombre);
        final Button verificar = findViewById(R.id.btnVerificar);
        final TextView lblResultado = findViewById(R.id.lblResultado);

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
                                        String resultado = extras.getString("boton_pulsado");
                                        lblResultado.setText(resultado);
                                    }
                                }
                            }
                        });

        verificar.setOnClickListener(view -> {
            String usuario = etNombre.getText().toString();

            // Llamo a la nueva actividad y le paso valores
            Intent intent_condiciones = new Intent(MainActivity.this, CondicionesUso.class);
            intent_condiciones.putExtra("usuario", usuario);
            // startActivity(intent_condiciones);
            resultLauncher.launch(intent_condiciones);
        });

    }
}