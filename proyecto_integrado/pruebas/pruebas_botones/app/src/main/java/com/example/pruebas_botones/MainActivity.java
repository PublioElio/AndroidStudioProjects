package com.example.pruebas_botones;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Intent serviceIntent = new Intent(this, ButtonPressService.class);
        startService(serviceIntent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
            Log.i("INFO", "ha pulsado el botón volume down");
        }
        return true;
    }
}