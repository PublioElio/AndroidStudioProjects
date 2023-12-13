package tiendas.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.practica_evaluacion.R;

import java.util.ArrayList;
import java.util.Objects;

import contacto.activity.ContactoActivity;
import grupos.activity.DatosGrupos;
import grupos.activity.GruposActivity;
import grupos.activity.PersonajeActivity;

public class TiendasActivity extends AppCompatActivity {

    private ListView listaTiendas = findViewById(R.id.listViewTiendas);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiendas);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ArrayList<DatosTiendas> datosTiendas = new ArrayList<>();
        introducirDatos(datosTiendas);
        AdaptadorTiendas adaptador = new AdaptadorTiendas(this, datosTiendas);
        listaTiendas.setAdapter(adaptador);

        final Button guardar = findViewById(R.id.btnGuardar);

        guardar.setOnClickListener(view -> {
            final TextView mostrarTiendasSeleccionadas = findViewById(R.id.txtTiendasSeleccionadas);
            StringBuilder tiendasGuardadas = new StringBuilder(" ");
            for (int i = 0; i < datosTiendas.size(); i++) {
                if (datosTiendas.get(i).isCheckBoxTienda()) {
                    tiendasGuardadas.append(datosTiendas.get(i).getNombreTienda()).append(" ");
                }
            }
            if (!" ".equals(tiendasGuardadas.toString())) {
                mostrarTiendasSeleccionadas.setText(R.string.has_seleccionado);
                mostrarTiendasSeleccionadas.append(tiendasGuardadas.toString());
                mostrarTiendasSeleccionadas.setVisibility(View.VISIBLE);
            } else {
                mostrarTiendasSeleccionadas.setText("");
                mostrarTiendasSeleccionadas.setText(R.string.no_has_seleccionado_ninguna_tienda);
                mostrarTiendasSeleccionadas.setVisibility(View.VISIBLE);
            }
        });

        registerForContextMenu(listaTiendas);
    }

    /**
     * Este método introduce los datos de las tiendas que mostraremos en la lista
     *
     * @param datosTiendas
     */
    private void introducirDatos(ArrayList<DatosTiendas> datosTiendas) {
        datosTiendas.add(new DatosTiendas("EN PORTADA CÓMICS",
                R.drawable.en_portada,
                "C. Nosquera, 10, 29008 Málaga",
                "geo:36.72398084366321, -4.422052084658372",
                "admin@enportadacomics.com",
                false,
                "952603250"));
        datosTiendas.add(new DatosTiendas("CÓMIC STORES SOHO",
                R.drawable.comic_stores_soho,
                "C/ Trinidad Grund, 11, Distrito Centro, 29001 Málaga",
                "geo:36.72398084366321, -4.422052084658372",
                "info@comicstores.es",
                false,
                "952213056"));
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
     *
     * @param item The menu item that was selected.
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
                intent = new Intent(this, TiendasActivity.class);
                startActivity(intent);
                break;
            case "Contacto":
                intent = new Intent(this, ContactoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void seleccionarGrupoDesdeMenu(String nombreEquipo) {
        Intent intent = new Intent(this, GruposActivity.class);
        intent.putExtra("equipo", nombreEquipo);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    // ------------- Lógica del menú contextual -----------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        menu.setHeaderTitle(((DatosTiendas) listaTiendas.getAdapter().getItem(info.position)).getNombreTienda());

        inflater.inflate(R.menu.menu_contextual_tiendas, menu);
    }

    /**
     * Este método realiza los eventos en función del elemento seleccionado del menú desplegable
     *
     * @param item The context menu item that was selected.
     */
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Intent intent;
        int id = item.getItemId();

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        assert info != null;

        // Realizo una accion partiendo del elemento del menú que he pulsado
        if (id == R.id.google_maps) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(((DatosTiendas) listaTiendas.getAdapter().getItem(info.position)).getGoogleMapsTienda()));
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{((DatosTiendas) listaTiendas.getAdapter().getItem(info.position)).getNombreTienda()});
        }
        startActivity(intent);
        return super.onContextItemSelected(item);
    }

}