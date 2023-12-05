package com.example.unidad_05_apuntes_08;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listado;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        // menú info nos da la posición del elemento
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // la línea siguiente sirve para que haya un header en el menú contextual
        menu.setHeaderTitle(listado.getAdapter().getItem(info.position).toString().toUpperCase());

        switch (info.position) {
            case 0:
                inflater.inflate(R.menu.menu_c1, menu);
                break;
            case 2:
                inflater.inflate(R.menu.menu_c2, menu);
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        String texto;
        int id = item.getItemId();

        // Realizo una accion partiendo del elemento del menú que he pulsado
        if (id == R.id.mc1Op1 || id == R.id.mc1Op2 || id == R.id.mc1Op3 || id == R.id.mc2Op1) {
            texto = item.getTitle().toString();
            Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
        }

        // Obtengo información del elemento sobre el que hemos desplegado el menú
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pulsado = info.position;
        texto = listado.getItemAtPosition(pulsado).toString();
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();

        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Recuperar id del listado
        listado = findViewById(R.id.listado);

        // Creo datos de los listados
        ArrayList<String> datos = new ArrayList<String>();
        datos.add("Elemento 1");
        datos.add("Elemento 2");
        datos.add("Elemento 3");
        datos.add("Elemento 4");

        // Creo el adaptador
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, datos);
        listado.setAdapter(adaptador);

        registerForContextMenu(listado); // esto es lo nuevo


    }
}
