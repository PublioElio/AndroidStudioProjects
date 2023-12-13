package grupos.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.practica_evaluacion.R;
import java.util.Objects;

public class PersonajeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        final TextView titulo = findViewById(R.id.titulo);
        final TextView subtitulo = findViewById(R.id.subtitulo);
        final Button volver = findViewById(R.id.btnVolver);

        // En función del contenido que ha seleccionado el usuario en Grupos Activity cargo unos contenidos en el layout
        if (Objects.requireNonNull(getIntent().getExtras()).containsKey("ficha")) {
            titulo.setText(R.string.ficha);
            subtitulo.setText(R.string.manten_pulsado_imagen);
            rellenarFicha(Objects.requireNonNull(getIntent().getStringExtra("ficha")));
        }
        final ImageView imagenPersonaje = findViewById(R.id.imagen);
        registerForContextMenu(imagenPersonaje);

        volver.setOnClickListener(view -> finish());

    }


    private void rellenarFicha(String nombrePersonaje) {

        final ImageView imagen = findViewById(R.id.imagen);
        final TextView contenido = findViewById(R.id.descripcion);

        switch (nombrePersonaje) {
            case "CAPITÁN AMÉRICA":
                imagen.setImageResource(R.drawable.capi_grande);
                contenido.setText(R.string.des_ficha_capi);
                break;
            case "IRON MAN":
                imagen.setImageResource(R.drawable.ironman_grande);
                contenido.setText(R.string.des_ficha_iron_man);
                break;
            case "VIUDA NEGRA":
                imagen.setImageResource(R.drawable.blackwidow_grande);
                contenido.setText(R.string.des_ficha_viuda);
                break;
            case "CÍCLOPE":
                imagen.setImageResource(R.drawable.cyclops_grande);
                contenido.setText(R.string.des_ficha_cyclops);
                break;
            case "JEAN GREY":
                imagen.setImageResource(R.drawable.jeangray_grande);
                contenido.setText(R.string.des_ficha_jeangrey);
                break;
            case "LOBEZNO":
                imagen.setImageResource(R.drawable.wolverine_grande);
                contenido.setText(R.string.des_ficha_wolverine);
                break;
        }
    }

    // ------------- Lógica del menú contextual -----------
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, view, menuInfo);

        MenuInflater inflater = getMenuInflater();

        // menu.setHeaderTitle();

        inflater.inflate(R.menu.menu_contextual_personajes, menu);
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

        String nombrePersonaje = "";
        if (Objects.requireNonNull(getIntent().getExtras()).containsKey("ficha")) {
            nombrePersonaje = (Objects.requireNonNull(getIntent().getStringExtra("ficha")));
        }

        if (id == R.id.ficha_wiki) {
            intent = new Intent(Intent.ACTION_VIEW, Uri.parse(verWiki(nombrePersonaje)));
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, "He encontrado un dato muy interesante del " +
                    "siguiente personaje de Marvel: " + nombrePersonaje);
        }
        startActivity(intent);
        return super.onContextItemSelected(item);
    }

    private String verWiki(String nombrePersonaje) {
        String url = "";
        switch (nombrePersonaje) {
            case "CAPITÁN AMÉRICA":
                url = "https://marvel.fandom.com/wiki/Steven_Rogers_(Earth-616)";
                break;
            case "IRON MAN":
                url = "https://marvel.fandom.com/wiki/Anthony_Stark_(Earth-616)";
                break;
            case "VIUDA NEGRA":
                url = "https://marvel.fandom.com/wiki/Natalia_Romanova_(Earth-616)";
                break;
            case "CÍCLOPE":
                url = "https://marvel.fandom.com/wiki/Scott_Summers_(Earth-616)";
                break;
            case "JEAN GREY":
                url = "https://marvel.fandom.com/wiki/Jean_Grey_(Earth-616)";
                break;
            case "LOBEZNO":
                url = "https://marvel.fandom.com/wiki/James_Howlett_(Earth-616)";
                break;
        }
        return url;
    }

}