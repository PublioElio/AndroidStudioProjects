package com.example.practica_exam_adiaz;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.annotation.Nullable;

public class ConfiguracionActivity extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.configuracion);
    }
}


