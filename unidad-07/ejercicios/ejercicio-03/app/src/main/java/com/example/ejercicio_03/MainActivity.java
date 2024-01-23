package com.example.ejercicio_03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

@SuppressLint("ExportedPreferenceActivity")
public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);

        findPreference("additionalSettings").setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(MainActivity.this, OpcionesActivity.class);
            startActivity(intent);
            return true;
        });

    }
}