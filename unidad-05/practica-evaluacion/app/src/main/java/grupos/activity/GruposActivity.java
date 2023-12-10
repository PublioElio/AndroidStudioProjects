package grupos.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practica_evaluacion.R;

import java.util.ArrayList;

public class GruposActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.equipos);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -------------- Lógica de la lista ------------------
        final GridView miGrid = findViewById(R.id.gridViewHeroes);
        // Recupero el nombre de equipo que ha seleccionado el usuario en Principal Activity
        String nombreEquipo = getIntent().getStringExtra("equipo");

        // Creo los datos de la lista
        ArrayList<DatosGrupos> datos = new ArrayList<>();
        introducirDatos(datos, nombreEquipo);

        // Creo el adaptador de la lista
        AdaptadorGrupos adaptador = new AdaptadorGrupos(this, datos);
        miGrid.setAdapter(adaptador);

        // Inserto el listener de la lista
        miGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    }


    private static void introducirDatos(ArrayList<DatosGrupos> datos, String nombreEquipo) {
        switch (nombreEquipo) {
            case "vengadores":
                datos.add(new DatosGrupos(R.drawable.capi_small, "CAPITÁN AMÉRICA"));
                datos.add(new DatosGrupos(R.drawable.ironman_small, "IRON MAN"));
                datos.add(new DatosGrupos(R.drawable.blackwidow_small, "VIUDA NEGRA"));
                break;
            case "xmen":
                datos.add(new DatosGrupos(R.drawable.cyclops_small, "CÍCLOPE"));
                datos.add(new DatosGrupos(R.drawable.jeangray_small, "JEAN GRAY"));
                datos.add(new DatosGrupos(R.drawable.wolverine_small, "LOBEZNO"));
                break;
        }
        ;

    }

    // -------------- Lógica del menú ---------------
    // Creo menú desde XML
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setLogo(R.drawable.logo_horizontal_small);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

}