package grupos.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.practica_evaluacion.R;
import java.util.ArrayList;

/**
 * Este es el adaptador que muestra los integrantes de un grupo de súper héroes
 */
public class AdaptadorGrupos extends ArrayAdapter {

    private final ArrayList<DatosGrupos> datos;

    public AdaptadorGrupos(Context context, ArrayList<DatosGrupos> datos) {
        super(context, R.layout.elementos_equipos, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflo el elemento
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elementos_equipos,parent,false);

        // Asigno contenido al elemento
        ImageView imagen = elemento.findViewById(R.id.retratoHeroe);
        imagen.setImageResource(datos.get(position).getFotoHeroe());

        TextView texto = elemento.findViewById(R.id.nombreHeroe);
        texto.setText(datos.get(position).getNombreHeroe());

        return elemento;
    }
}
