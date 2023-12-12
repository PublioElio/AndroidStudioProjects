package contacto.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practica_evaluacion.R;

import java.util.Objects;

import grupos.activity.GruposActivity;
import principal.activity.PrincipalActivity;
import tiendas.activity.TiendasActivity;

public class ContactoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Switch modoHulk = findViewById(R.id.modoHulk);

        modoHulk.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                final FrameLayout parentLayout = findViewById(R.id.parentLayoutContactos);
                if(isChecked){
                    parentLayout.setBackgroundResource(R.drawable.background2);
                } else {
                    parentLayout.setBackgroundResource(R.drawable.background3);
                }
                LayoutInflater inflater = getLayoutInflater();
                View v = inflater.inflate(R.layout.cust_toast_layout,
                        (ViewGroup)findViewById(R.id.relativeLayoutlToast));
                final ImageView image = (ImageView) v.findViewById(R.id.imgViewToast);
                image.setImageResource(R.drawable.hulk);
                TextView text = (TextView) v.findViewById(R.id.txtViewToast);
                text.setText(generarFraseDeHulk());
                Toast toast = new Toast(getApplicationContext());
                toast.setView(v);
                toast.show();

            }
        });
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
                intent = new Intent(ContactoActivity.this, TiendasActivity.class);
                startActivity(intent);
                break;
            case "Contacto":
                intent = new Intent(ContactoActivity.this, ContactoActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void seleccionarGrupoDesdeMenu(String nombreEquipo) {
        Intent intent = new Intent(ContactoActivity.this, GruposActivity.class);
        intent.putExtra("equipo", nombreEquipo);
        setResult(RESULT_OK, intent);
        startActivity(intent);
    }

    private String generarFraseDeHulk() {
        String frase = "";
        int numeroAleatorio = (int) (Math.random() * 4);
        switch (numeroAleatorio){
            case 0:
                frase = "¡Hulk aplasta!";
                break;
            case 1:
                frase = "¡Hulk es el más fuerte!";
                break;
            case 2:
                frase = "¡RAAAAAHHH!";
                break;
            case 3:
                frase = "¡Hulk odia al\nenclenque Banner!";
                break;
        }
        return frase;
    }
}