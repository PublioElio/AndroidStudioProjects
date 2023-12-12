package com.example.examenadrianodiaz;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final Button botonVolver = findViewById(R.id.btnVolver);

        botonVolver.setOnClickListener(v -> finish());
    }
}