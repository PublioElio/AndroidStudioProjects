package com.example.examenadrianodiaz;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Objects;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        final TextView miTextView = findViewById(R.id.miTxtView2);
        final Button btnAceptar = findViewById(R.id.btnOk);
        Bundle extras = getIntent().getExtras();
        assert extras != null;
        miTextView.append(" " + Objects.requireNonNull(extras.getString("dia")).toLowerCase());

        btnAceptar.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra("resultado","Cita pedida");
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}