package grupos.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.practica_evaluacion.R;

import java.util.ArrayList;

public class AdaptadorGrupos extends ArrayAdapter {

    private ArrayList<DatosGrupos> datos;

    public AdaptadorGrupos(Context context, ArrayList<DatosGrupos> datos) {
        super(context, R.layout.elementos_grupos, datos);
        this.datos = datos;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Inflo el elemento
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        View elemento = mostrado.inflate(R.layout.elementos_grupos,parent,false);

        // Asigno contenido al elemento
        ImageView imagen = elemento.findViewById(R.id.retratoHeroe);
        imagen.setImageResource(datos.get(position).getFotoHeroe());

        TextView texto = elemento.findViewById(R.id.nombreHeroe);
        texto.setText(datos.get(position).getNombreHeroe());

        CheckBox checkBox = elemento.findViewById(R.id.checkGrupos);
        checkBox.setChecked(datos.get(position).isSeleccionado());
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                datos.get(position).setSeleccionado(isChecked);
            }
        });

        return elemento;
    }
}
