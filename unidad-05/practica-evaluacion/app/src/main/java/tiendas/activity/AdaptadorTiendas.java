package tiendas.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.practica_evaluacion.R;

import java.util.ArrayList;

/**
 * Este es el adaptador que introduce los datos de las tiendas de cómics
 */
public class AdaptadorTiendas extends ArrayAdapter {

    private final ArrayList<DatosTiendas> datosTiendas;

    public AdaptadorTiendas(Context context, ArrayList<DatosTiendas> datosTiendas) {
        super(context, R.layout.elementos_tiendas, datosTiendas);
        this.datosTiendas = datosTiendas;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Inflo el elemento
        LayoutInflater mostrado = LayoutInflater.from(getContext());
        @SuppressLint("ViewHolder") View elemento = mostrado.inflate(R.layout.elementos_tiendas, parent, false);

        // Asigno nombre a la tienda
        TextView nombreTienda = elemento.findViewById(R.id.txtViewNombreTienda);
        nombreTienda.setText(datosTiendas.get(position).getNombreTienda());

        // Asigno imagen de la tienda
        ImageView imagenTienda = elemento.findViewById(R.id.imgViewTienda);
        imagenTienda.setImageResource(datosTiendas.get(position).getImgTienda());

        // Relleno la dirección
        TextView direccionTienda = elemento.findViewById(R.id.txtViewDireccionTienda);
        direccionTienda.setText(datosTiendas.get(position).getDireccionTienda());

        // Checkbox de la tienda y su listener
        CheckBox checkBoxTienda = elemento.findViewById(R.id.checkboxTienda);
        checkBoxTienda.setChecked(datosTiendas.get(position).isCheckBoxTienda());
        checkBoxTienda.setOnCheckedChangeListener((buttonView, isChecked) -> datosTiendas.get(position).setCheckBoxTienda(isChecked));

        // Botón llamar de la tienda
        ImageButton btnLlamarTienda = elemento.findViewById(R.id.btnLlamar);
        btnLlamarTienda.setOnClickListener(view -> {
            String tlfno = datosTiendas.get(position).getTlfnTienda();
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tlfno));
            getContext().startActivity(intent);
        });
        return elemento;
    }

}
