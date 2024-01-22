package com.example.ejercicio_03;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import androidx.annotation.Nullable;

public class OpcionesActivity  extends PreferenceActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones2);
    }
}

