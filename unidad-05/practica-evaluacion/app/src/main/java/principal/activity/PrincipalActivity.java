package principal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practica_evaluacion.R;

import java.util.ArrayList;

import grupos.activity.GruposActivity;

/**
 * Esta activity es donde aparecen los equipos de súper héroes. También tiene un menú superior donde
 * se pueden acceder a otras actividades de la aplicación
 */
public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -------------- Lógica de la lista ------------------
        final ListView miLista = findViewById(R.id.miLista);

        // Creo la lista de datos he introduzco los logos
        ArrayList<DatosPrincipal> datos = new ArrayList<>();
        introducirDatos(datos);

        // Creo el adaptador de la lista
        AdaptadorPrincipal adaptador = new AdaptadorPrincipal(this, datos);
        miLista.setAdapter(adaptador);

        // Acciones que tendrán lugar cuando se pulse en cualquiera de los elementos de la lista
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Guardamos el nombre del equipo que ha pulsado el usuario
                String nombreEquipo = ((DatosPrincipal) parent.getItemAtPosition(position)).getNombreEquipo();
                if ("vengadores".equals(nombreEquipo) || "xmen".equals(nombreEquipo)) {
                    Intent intent = new Intent(PrincipalActivity.this, GruposActivity.class);
                    intent.putExtra("equipo", nombreEquipo);
                    setResult(RESULT_OK, intent);
                    startActivity(intent);
                } else {
                    // solo se han implementado dos equipos (Vengadores y Xmen), este es el
                    // mensaje que aparecerá cuando se pulse un equipo que aún no tiene información
                    // personalizar Toast -> https://stackoverflow.com/questions/7571917/adding-image-to-toast
                    Toast.makeText(PrincipalActivity.this, "¡Próximamente!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * Este método introduce los logotipos de los equipos en el listado
     *
     * @param datos
     */
    private static void introducirDatos(ArrayList<DatosPrincipal> datos) {
        datos.add(new DatosPrincipal(R.drawable.vengadores, "vengadores"));
        datos.add(new DatosPrincipal(R.drawable.xmen, "xmen"));
        datos.add(new DatosPrincipal(R.drawable.guardians, "guardianes"));
        datos.add(new DatosPrincipal(R.drawable.fantastic_4, "fantastic4"));
        datos.add(new DatosPrincipal(R.drawable.thunderbolts, "thunderbolts"));
        datos.add(new DatosPrincipal(R.drawable.alphaflight, "alphaflight"));
        datos.add(new DatosPrincipal(R.drawable.defenders, "defenders"));
        datos.add(new DatosPrincipal(R.drawable.new_warriors, "newwarriors"));
    }

    // -------------- Lógica del menú ---------------
    // Creo menú desde XML
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        getSupportActionBar().setTitle(""); // quito el téxto del título
        getSupportActionBar().setLogo(R.drawable.logo_horizontal_small); // introduzco el logo en el menú
        getSupportActionBar().setDisplayUseLogoEnabled(true); // muestro el logo
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int opcion = item.getItemId();
// TODO - opciones de selección de actividades
        return super.onOptionsItemSelected(item);
    }
}