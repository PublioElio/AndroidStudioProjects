package com.example.ejercicio_03;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.SwitchPreference;

@SuppressLint("ExportedPreferenceActivity")
public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.opciones);

        final SwitchPreference activationTime =
                (SwitchPreference) findPreference("activationTime");

        activationTime.setOnPreferenceChangeListener((preference, newValue) -> {
            boolean activationTimeEnabled = (Boolean) newValue;
            findPreference("start").setEnabled(activationTimeEnabled);
            findPreference("end").setEnabled(activationTimeEnabled);
            findPreference("repit").setEnabled(activationTimeEnabled);
            return true;
        });

        findPreference("additionalSettings").setOnPreferenceClickListener(preference -> {
            Intent intent = new Intent(MainActivity.this, OpcionesActivity.class);
            startActivity(intent);
            return true;
        });

    }
}