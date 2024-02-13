package com.example.examen_final_adiaz;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class ConfiguracionActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferencias);
    }
}


