package com.example.t7examen;

import android.os.Bundle;
import android.preference.PreferenceActivity;

public class OpcionesActivity extends PreferenceActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.opciones);
    }
}

