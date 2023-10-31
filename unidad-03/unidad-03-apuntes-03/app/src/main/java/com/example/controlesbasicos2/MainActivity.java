package com.example.controlesbasicos2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ---------------- EJEMPLO SPINNER
        final Spinner spinner = findViewById(R.id.miSpinner);
        String[] valores = {"Valor 1", "Valor 2", "Valor 3", "Valor 4", "Valor 5",};
        spinner.setAdapter(new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_item, valores)); // ERROR típico no pasarle el array al final (valores)
        // recupero el contenido del elemento seleccionado y lo pongo en el text view
        final TextView texto = findViewById(R.id.txt1);
        String valor = spinner.getSelectedItem().toString();
        texto.setText(valor);
        // hay que hacer un manejador de eventos para que cuando cambie de opción lo pille
        // creo un adapter para poder usarlo con el manejador de eventos
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adaptador, View view, int position, long id) {
                String cadena = (String) adaptador.getItemAtPosition(position);
                texto.setText(cadena);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // CREO EL ADAPTADOR PARA EL SEGUNDO SPINNER
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this, R.array.valores,
                        android.R.layout.simple_spinner_item);

        final Spinner spinner2 = findViewById(R.id.miSpinner2);
        spinner2.setAdapter(adapter);

        // --------- CHECKBOX
        // ------ se puede marcar desde Java con la propiedad setChecked(boolean)
        final TextView texto2 = findViewById(R.id.lbl1);
        final CheckBox checkBox = findViewById(R.id.myCheck);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    texto2.setText("Seleccionado");
                } else{
                    texto2.setText("Deseleccionado");
                }
            }
        });

        // ----- RADIOBUTTON
        final RadioGroup miGrupo = findViewById(R.id.grupo);

        // Manejador de eventos de radioGroup
        miGrupo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                final RadioButton rb = findViewById(checkedId);
                String texto = rb.getText().toString(); // esta variable es para poder ver el nombre del radio button elegido
                texto2.setText(texto);

            }
        });

        // -------------- SWITCH
        final Switch pulsador = findViewById(R.id.miSwitch);
        pulsador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean pulsado) {
                if(pulsado){
                    texto2.setText("Switch marcado");
                } else {
                    texto2.setText("Switch desmarcado");
                }
            }
        });

        // -------------- RATING BAR
        final RatingBar puntuacion = findViewById(R.id.miRating);

        puntuacion.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                texto2.setText("Valor en rating cambiado por: " + rating);
            }
        });


        // ---------------- SEEKBAR
        final SeekBar miControl = findViewById(R.id.miSeekBar);
        miControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final TextView etiqueta = findViewById(R.id.lblSeek);
                texto2.setText("Texto cambiado en SeekBar: " + progress);
                etiqueta.setAlpha(progress/60f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                texto2.setText("Inicio de cambio de texto en SeekBar");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                texto2.setText("Final del cambio de texto en SeekBar");
            }
        });

    }
}