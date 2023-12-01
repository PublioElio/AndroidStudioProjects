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

import com.example.practica_evaluacion.MainActivity;
import com.example.practica_evaluacion.R;
import java.util.ArrayList;

import grupos.activity.VengadoresActivity;
import grupos.activity.XmenActivity;

public class PrincipalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // -------------- Lógica de la lista ------------------
        final ListView miLista = findViewById(R.id.miLista);

        // Creo los datos de la lista
        ArrayList<DatosPrincipal> datos = new ArrayList<>();
        introducirDatos(datos);

        // Creo el adaptador de la lista
        AdaptadorPrincipal adaptador = new AdaptadorPrincipal(this, datos);
        miLista.setAdapter(adaptador);

        // Inserto el listener de la lista
        miLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(PrincipalActivity.this, VengadoresActivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(PrincipalActivity.this, XmenActivity.class);
                        startActivity(intent);
                        break;
                    default:
                        Toast.makeText(PrincipalActivity.this, "¡Próximamente!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private static void introducirDatos(ArrayList<DatosPrincipal> datos) {
        datos.add(new DatosPrincipal(R.drawable.vengadores));
        datos.add(new DatosPrincipal(R.drawable.xmen));
        datos.add(new DatosPrincipal(R.drawable.guardians));
        datos.add(new DatosPrincipal(R.drawable.fantastic_4));
        datos.add(new DatosPrincipal(R.drawable.thunderbolts));
        datos.add(new DatosPrincipal(R.drawable.alphaflight));
        datos.add(new DatosPrincipal(R.drawable.defenders));
        datos.add(new DatosPrincipal(R.drawable.new_warriors));
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