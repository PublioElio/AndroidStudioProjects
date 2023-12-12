package grupos.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.practica_evaluacion.R;
import java.util.ArrayList;
import java.util.Objects;
import contacto.activity.ContactoActivity;
import tiendas.activity.TiendasActivity;

public class GruposActivity extends AppCompatActivity {

    private GridView miGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipos);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -------------- Lógica de la lista ------------------
        miGrid = findViewById(R.id.gridViewHeroes);

        // Recupero el nombre de equipo que ha seleccionado el usuario en Principal Activity

        // Obtener datos del Intent
        Intent intent = getIntent();
        String nombreEquipo = "";
        if (intent != null && intent.hasExtra("equipo")) {
            Bundle extras = getIntent().getExtras();
            assert extras != null;
            nombreEquipo = extras.getString("equipo");
        }

        // Creo los datos de la lista
        ArrayList<DatosGrupos> datos = new ArrayList<>();
        assert nombreEquipo != null;
        introducirDatos(datos, nombreEquipo);

        // Creo el adaptador de la lista
        AdaptadorGrupos adaptador = new AdaptadorGrupos(this, datos);
        miGrid.setAdapter(adaptador);

        // Creo el menú contextual
        registerForContextMenu(miGrid);
    }

    /**
     * Esta función rellena la lista de datos con objetos del tipo DatosGrupos, que son súper
     * héroes, con su imagen pequeña y su nombre
     * @param datos la lista donde se guardarán los datos
     * @param nombreEquipo el equipo de personajes
     */
    private static void introducirDatos(ArrayList<DatosGrupos> datos, String nombreEquipo) {
        switch (nombreEquipo) {
            case "vengadores":
                datos.add(new DatosGrupos(R.drawable.capi_small, "CAPITÁN AMÉRICA"));
                datos.add(new DatosGrupos(R.drawable.ironman_small, "IRON MAN"));
                datos.add(new DatosGrupos(R.drawable.blackwidow_small, "VIUDA NEGRA"));
                break;
            case "xmen":
                datos.add(new DatosGrupos(R.drawable.cyclops_small, "CÍCLOPE"));
                datos.add(new DatosGrupos(R.drawable.jeangray_small, "JEAN GREY"));
                datos.add(new DatosGrupos(R.drawable.wolverine_small, "LOBEZNO"));
                break;
        }
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
                intent = new Intent(GruposActivity.this, TiendasActivity.class);
                startActivity(intent);
                break;
            case "Contacto":
                intent = new Intent(GruposActivity.this, ContactoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void seleccionarGrupoDesdeMenu(String nombreEquipo) {
        Intent intent = new Intent(GruposActivity.this, GruposActivity.class);
        intent.putExtra("equipo", nombreEquipo);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    // ------------- Lógica del menú contextual -----------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater inflater = getMenuInflater();
        // Menú info nos da la posición del elemento
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

        // La línea siguiente sirve para que haya un header en el menú contextual
        menu.setHeaderTitle(((DatosGrupos) miGrid.getAdapter().getItem(info.position)).getNombreHeroe());

        inflater.inflate(R.menu.menu_personajes, menu);
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
        // Obtengo información del elemento sobre el que hemos desplegado el menú
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        assert info != null;
        String nombreHeroe = ((DatosGrupos) miGrid.getAdapter().getItem(info.position)).getNombreHeroe();

        // Realizo una accion partiendo del elemento del menú que he pulsado
        if (id == R.id.ficha) {
            intent = new Intent(GruposActivity.this, PersonajeActivity.class);
            intent.putExtra("ficha", nombreHeroe);
            setResult(RESULT_OK, intent);
        } else {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(recomendacionLectura(nombreHeroe)));
        }
        startActivity(intent);
        return super.onContextItemSelected(item);
    }

    /**
     * Esta función recibe un String que es el nombre de un héroe y devuelve otro que es una url a
     * una lectura recomentdada.
     * @param nombreHeroe
     * @return
     */
    private String recomendacionLectura(String nombreHeroe) {
        String url = "";
        switch (nombreHeroe) {
            case "CAPITÁN AMÉRICA":
                url = "https://www.panini.es/shp_esp_es/marvel-integral-capit-n-am-rica-el-soldado-de-invierno-smain001-es01.html";
                break;
            case "IRON MAN":
                url = "https://www.panini.es/shp_esp_es/marvel-integral-iron-man-extremis-smain021-es01.html";
                break;
            case "VIUDA NEGRA":
                url = "https://www.panini.es/shp_esp_es/viuda-negra-witsi-witsi-ara-a-smcol001-es01.html";
                break;
            case "CÍCLOPE":
                url = "https://www.panini.es/shp_esp_es/marvel-deluxe-patrulla-x-naci-n-x-sluxe105-es01.html";
                break;
            case "JEAN GREY":
                url = "https://www.panini.es/shp_esp_es/marvel-must-have-la-patrulla-x-la-saga-de-f-nix-oscura-smust013-es01.html";
                break;
            case "LOBEZNO":
                url = "https://www.panini.es/shp_esp_es/100-marvel-hc-lobezno-origen-shmin071-es01.html";
                break;
        }
        return url;
    }


}