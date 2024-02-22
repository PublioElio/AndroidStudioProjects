package com.example.examen_cp;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.examen_cp.spinner.AdapterSpinner;
import com.example.examen_cp.spinner.DatosSpinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinnerAvatares = findViewById(R.id.spinnerAvatares);

        cargarAvatares(spinnerAvatares);

    }

    private void cargarAvatares(Spinner spinner){
        ArrayList<DatosSpinner> datos = new ArrayList<>();
        AdapterSpinner adaptador = new AdapterSpinner(this,datos);
        datos.add(new DatosSpinner(R.drawable.batman));
        datos.add(new DatosSpinner(R.drawable.capi));
        datos.add(new DatosSpinner(R.drawable.deadpool));
        datos.add(new DatosSpinner(R.drawable.furia));
        datos.add(new DatosSpinner(R.drawable.hulk));
        datos.add(new DatosSpinner(R.drawable.ironman));
        datos.add(new DatosSpinner(R.drawable.lobezno));
        datos.add(new DatosSpinner(R.drawable.spiderman));
        datos.add(new DatosSpinner(R.drawable.thor));
        datos.add(new DatosSpinner(R.drawable.wonderwoman));
        spinner.setAdapter(adaptador);
    }

}