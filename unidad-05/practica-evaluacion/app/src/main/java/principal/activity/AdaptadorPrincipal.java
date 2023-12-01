package principal.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.practica_evaluacion.R;
import java.util.ArrayList;

public class AdaptadorPrincipal extends ArrayAdapter {

    private ArrayList<DatosPrincipal> datos;

    public AdaptadorPrincipal(Context context, ArrayList<DatosPrincipal> datos) {
        super(context, R.layout.elementos_principal, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflo el elemento
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elementos_principal,parent,false);

        // Asigno logotipo del equipo MARVEL
        ImageView imagen = elemento.findViewById(R.id.logoEquipo);
        imagen.setImageResource(datos.get(position).getLogoEquipo());

        return elemento;
    }
}
