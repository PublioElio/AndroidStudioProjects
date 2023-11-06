package com.example.t4_ejercicio4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        final Button preparate = findViewById(R.id.preparate);

        preparate.setOnClickListener(view -> {
            Intent intent = new Intent(Activity3.this, MainActivity.class);
            startActivity(intent);
        });
    }
}