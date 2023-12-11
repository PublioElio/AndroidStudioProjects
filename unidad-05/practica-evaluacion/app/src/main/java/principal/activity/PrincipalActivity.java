package principal.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.practica_evaluacion.R;
import java.util.ArrayList;
import java.util.Objects;
import contacto.activity.ContactoActivity;
import grupos.activity.GruposActivity;
import tiendas.activity.TiendasActivity;

/**
 * Esta activity es donde aparecen los equipos de súper héroes. También tiene un menú superior donde
 * se pueden acceder a otras actividades de la aplicación
 */
public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -------------- Lógica de la lista ------------------
        final ListView miLista = findViewById(R.id.miLista);

        // Creo la lista de datos he introduzco los logos y nombres de los equipos
        ArrayList<DatosPrincipal> datos = new ArrayList<>();
        introducirDatos(datos);

        // Creo el adaptador de la lista
        AdaptadorPrincipal adaptador = new AdaptadorPrincipal(this, datos);
        miLista.setAdapter(adaptador);

        // Acciones que tendrán lugar cuando se pulse en cualquiera de los elementos de la lista
        miLista.setOnItemClickListener((parent, view, position, id) -> {

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
                // personalizar Toast -> https://stackoverflow.com/questions/11288475/custom-toast-on-android-a-simple-example
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.cust_toast_layout,
                        (ViewGroup)findViewById(R.id.relativeLayoutlToast));
                final ImageView image = (ImageView) v.findViewById(R.id.imgViewToast);
                image.setImageResource(R.drawable.stan);
                TextView text = (TextView) v.findViewById(R.id.txtViewToast);
                text.setText(R.string.proximamente);
                Toast toast = new Toast(getApplicationContext());
                toast.setView(v);
                toast.show();
            }
        });
    }

    /**
     * Este método introduce los logotipos de los equipos en el listado
     *
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
        Objects.requireNonNull(getSupportActionBar()).setTitle(""); // quito el téxto del título
        getSupportActionBar().setLogo(R.drawable.logo_horizontal_small); // introduzco el logo en el menú
        getSupportActionBar().setDisplayUseLogoEnabled(true); // muestro el logo
        return true;
    }

    /**
     * Este método realiza las acciones derivadas de seleccionar un elemento del menú
     * @param item The menu item that was selected.
     *
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String opcion = Objects.requireNonNull(item.getTitle()).toString();
        Intent intent;
        switch (opcion) {
            case "Vengadores":
            case "Xmen":
                seleccionarGrupoDesdeMenu(opcion.toLowerCase());
                break;
            case "Tiendas de cómics":
                intent = new Intent(PrincipalActivity.this, TiendasActivity.class);
                startActivity(intent);
                break;
            case "Contacto":
                intent = new Intent(PrincipalActivity.this, ContactoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void seleccionarGrupoDesdeMenu(String nombreEquipo) {
        Intent intent = new Intent(PrincipalActivity.this, GruposActivity.class);
        intent.putExtra("equipo", nombreEquipo);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }
}