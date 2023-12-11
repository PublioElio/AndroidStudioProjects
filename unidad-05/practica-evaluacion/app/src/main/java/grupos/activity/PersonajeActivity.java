package grupos.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.practica_evaluacion.R;
import java.util.Objects;

public class PersonajeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personaje);

        final TextView titulo = findViewById(R.id.titulo);
        final Button volver = findViewById(R.id.btnVolver);

        // En función del contenido que ha seleccionado el usuario en Grupos Activity cargo unos contenidos en el layout
        if (Objects.requireNonNull(getIntent().getExtras()).containsKey("ficha")) {
            titulo.setText(R.string.ficha);
            rellenarFicha(Objects.requireNonNull(getIntent().getStringExtra("ficha")));
        }
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

}