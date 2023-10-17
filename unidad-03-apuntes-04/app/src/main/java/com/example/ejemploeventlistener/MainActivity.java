package com.example.ejemploeventlistener;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recupero los elementos del layout
        // Los TextView
        final TextView etiqueta1 = findViewById(R.id.lbl1);
        final TextView etiqueta2 = findViewById(R.id.lbl2);
        final TextView etiqueta3 = findViewById(R.id.lbl3);
        final TextView etiqueta4 = findViewById(R.id.lbl4);

        // El EditText
        final EditText texto = findViewById(R.id.et);

        // Defino el event listener TextChangedListener
        texto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // Este es un método que se lanza antes de que cambie el texto
                etiqueta1.setText(charSequence.toString());

                // Este método es llamado para notificar que, dentro de charSequence, count caracteres
                // a partir de start van a ser reemplazados por un nuevo texto cuya longitud
                // es after
                etiqueta3.setText(charSequence.toString() + " start: " + start + " count: " + count + " after: " + after);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                // Método que se lanza cuando el texto cambia
                etiqueta2.setText(charSequence.toString());
                etiqueta3.setText(charSequence.toString() + " start: " + start + " before: " + before + " count: " + count);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // No hacemos nada en este método
            }
        });
    }

    public void onClick(View view){
        final TextView etiqueta5 = findViewById(R.id.lbl5);
        int id = view.getId();

        if(id == R.id.btnAceptar){
            etiqueta5.setText("Aceptar");
        } else if (id == R.id.btnCancelar) {
            etiqueta5.setText("Cancelar");
        }

    }
}