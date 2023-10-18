package com.example.aaaa;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad1);

        //Declaro las variables del primer layout
        final Button boton = findViewById(R.id.boton);
        final Switch inicioSwitch = findViewById(R.id.inicioSwitch);
        final TextView txtCondicones = findViewById(R.id.txtCondiciones);

        //Listener del boton del inicio.
        //Listener creado para poner pasar al siguiente layout.
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Contiene un if else que determina si pasa de layout si el checkbox esta activado.
                if (!inicioSwitch.isChecked()) {
                    txtCondicones.setVisibility(View.VISIBLE);
                } else {
                    //Pasa al segundo layout
                    setContentView(R.layout.actividad2);

                    //Declaro las variables del segundo layout
                    final EditText editTextNombre = findViewById(R.id.editText1);
                    final EditText editTextCumple = findViewById(R.id.editText2);
                    final Button botonAceptar = findViewById(R.id.botonAceptar);
                    final TextView textoAlerta = findViewById(R.id.txtAlerta);
                    final CheckBox checkBoxRecordatorio = findViewById(R.id.checkboxRecordatorio);
                    final Button botonSiguiente = findViewById(R.id.botonSiguiente);

                    //Listener del boton aceptar.
                    botonAceptar.setOnClickListener(new View.OnClickListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onClick(View v) {
                            //Este conjunto de if else es para cubrir todas las posibilidades
                            // a la hora de rellenar el EditText
                            if (editTextNombre.length()!=0 & editTextCumple.length()!=0){
                                botonSiguiente.setVisibility(View.VISIBLE);
                                textoAlerta.setText("¡Hola "+ editTextNombre.getText()+"! Has nacido el "+editTextCumple.getText()+".");
                                if(checkBoxRecordatorio.isChecked()){
                                    textoAlerta.append(" Se ha creado un recordario.");
                                }
                            }else if(editTextNombre.length()==0 & editTextCumple.length()==0){
                                textoAlerta.setText("ERROR: No has escrito el nombre y la fecha.");
                            }else if(editTextNombre.length()==0){
                                textoAlerta.setText("ERROR: No has escrito el nombre.");
                            }else if(editTextCumple.length()==0){
                                textoAlerta.setText("ERROR: No has escrito la fecha.");
                            }

                        }
                    });

                    //Listener para el boton que lleva al layout 3.
                    botonSiguiente.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //Pasa al tercer layout
                            setContentView(R.layout.actividad3);

                            //Declaro las variables del layout3
                            final Button botonReset = findViewById(R.id.botonReset);
                            final Button botonEnviar = findViewById(R.id.botonEnviar);
                            final GridLayout grid1 = findViewById(R.id.grid1);
                            final RadioGroup radiogroup = findViewById(R.id.radiogroup);
                            final TextView txtResumenFinal = findViewById(R.id.txtResumenFinal);
                            final TextView txtSeekBar = findViewById(R.id.txtSeekBar);
                            final RatingBar rating = findViewById(R.id.rating);
                            final SeekBar seekBar = findViewById(R.id.seekBar);

                            //Listener para que salga en pantalla los numeros del SeekBar
                            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                                @Override
                                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                    txtSeekBar.setText("" + progress);
                                }

                                @Override
                                public void onStartTrackingTouch(SeekBar seekBar) {

                                }

                                @Override
                                public void onStopTrackingTouch(SeekBar seekBar) {

                                }
                            });

                            //Listener para suspuestamente enviar la información
                            //Tambien añade información sobre lo añadido en el cuestionario
                            botonEnviar.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    txtResumenFinal.setText("\nLo que te ha gustado de la aplicación ha sido:\n ");
                                    for (int i = 0; i < grid1.getChildCount(); i++) {
                                        CheckBox miCheck = (CheckBox) grid1.getChildAt(i);
                                        if (miCheck.isChecked()) {
                                            txtResumenFinal.append("- "+miCheck.getText() + " \n");
                                        }
                                    }
                                    //Añade el mensaje de la valoración.
                                    txtResumenFinal.append("Has valorado la aplicación con un " + rating.getRating());


                                    //Inserto en el text view resumen en un bucle al igual que en el GridLayout
                                    for (int i = 0; i < radiogroup.getChildCount(); i++) {
                                        RadioButton radio = (RadioButton) radiogroup.getChildAt(i);
                                        if (radio.isChecked()) {
                                            txtResumenFinal.append("\nLa caracteristica que más te ha gustado ha sido: "+radio.getText());
                                        }
                                    }

                                    txtResumenFinal.append("\nHas usado "+seekBar.getProgress() +" veces");

                                }
                            });




                            //Listener para resetear todo lo de esta pantalla
                            botonReset.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    for (int i = 0; i < grid1.getChildCount(); i++) {
                                        CheckBox miCheck = (CheckBox) grid1.getChildAt(i);
                                        miCheck.setChecked(false);
                                    }
                                    radiogroup.clearCheck();
                                    txtResumenFinal.setText("");
                                    seekBar.setProgress(0);
                                }
                            });


                        }
                    });

                }
            }
        });
    }
}