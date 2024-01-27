package com.example.ejercicio_06;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.List;

public class Adaptador extends ArrayAdapter<Heroe> {

    public Adaptador(Context context, List<Heroe> dataList) {
        super(context, 0, dataList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    private View getCustomView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.elements, parent, false);
        }

        ImageView heroImageView = convertView.findViewById(R.id.ivSpinnerHeroe);
        Heroe data = getItem(position);

        if (data != null) {
            heroImageView.setImageResource(data.getimgHeroe());
        }

        return convertView;
    }
}

