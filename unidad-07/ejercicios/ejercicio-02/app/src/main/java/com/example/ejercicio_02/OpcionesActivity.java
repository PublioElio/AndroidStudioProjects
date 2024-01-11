package com.example.ejercicio_02;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;

public class OpcionesActivity  extends PreferenceActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.opciones);

    }
}
