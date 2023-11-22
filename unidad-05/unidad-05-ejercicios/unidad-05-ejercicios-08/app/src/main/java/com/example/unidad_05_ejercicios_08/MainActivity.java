package com.example.unidad_05_ejercicios_08;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //defino los datos
        ArrayList<Datos> datos = new ArrayList<Datos>();
        datos.add(new Datos(R.drawable.buho, "Búho"));
        datos.add(new Datos(R.drawable.cachorros, "Cachorros"));
        datos.add(new Datos(R.drawable.cerdo, "Cerdo"));
        datos.add(new Datos(R.drawable.jirafas, "Jirafas"));
        datos.add(new Datos(R.drawable.lobo, "Lobo"));
        datos.add(new Datos(R.drawable.potro, "Potro"));
        datos.add(new Datos(R.drawable.tigre, "Tigre"));
        datos.add(new Datos(R.drawable.tortuga, "Tortuga"));
        datos.add(new Datos(R.drawable.tucan, "Tucán"));


        // Incorporo el elemento ListView
        GridView miGridView = findViewById(R.id.miGridView);

        // Defino el adaptador
        Adaptador miAdaptador = new Adaptador(this, datos);
        miGridView.setAdapter(miAdaptador);

        miGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String seleccionado = ((Datos) parent.getItemAtPosition(position)).getTexto();
                Toast.makeText(MainActivity.this, seleccionado, Toast.LENGTH_SHORT).show();
            }
        });


    }
}