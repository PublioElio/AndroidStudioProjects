package grupos.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.example.practica_evaluacion.R;
import java.util.ArrayList;

public class VengadoresActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vengadores);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -------------- Lógica de la lista ------------------
        final ListView miLista = findViewById(R.id.listaVengadores);

        // Creo los datos de la lista
        ArrayList<DatosGrupos> datos = new ArrayList<>();
        introducirDatos(datos);

        // Creo el adaptador de la lista
        AdaptadorGrupos adaptador = new AdaptadorGrupos(this, datos);
        miLista.setAdapter(adaptador);

        // Inserto el listener de la lista
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    private static void introducirDatos(ArrayList<DatosGrupos> datos) {
        datos.add(new DatosGrupos(R.drawable.capi_small, "CAPITÁN AMÉRICA", false));
        datos.add(new DatosGrupos(R.drawable.ironman_small, "IRON MAN", false));
        datos.add(new DatosGrupos(R.drawable.blackwidow_small, "VIUDA NEGRA", false));
    }

    // -------------- Lógica del menú ---------------
    // Creo menú desde XML
    @Override
    public boolean onCreateOptionsMenu (Menu menu){
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