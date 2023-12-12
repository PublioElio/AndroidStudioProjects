package com.example.examenadrianodiaz;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final String[] diasSemana = new String[]{"Lunes", "Martes", "Miércoles", "Jueves",
                "Viernes", "Sábado", "Domingo"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, diasSemana);

        final ListView miListView = findViewById(R.id.miListView);
        final GridView miGridView = findViewById(R.id.miGridView);
        final Spinner miSpinner = findViewById(R.id.miSpinner);
        miListView.setAdapter(adaptador);
        miGridView.setAdapter(adaptador);
        miSpinner.setAdapter(adaptador);

        registerForContextMenu(miListView);
    }

    //---------------------- LÓGICA DEL MENÚ
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_intents, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        final TextView miTxtView = findViewById(R.id.miTxtView);
        final ListView miLisView = findViewById(R.id.miListView);
        final GridView miGridView = findViewById(R.id.miGridView);
        final Spinner miSpinner = findViewById(R.id.miSpinner);

        Intent intent;
        if (item.getItemId() > 0) {
            miTxtView.setText("");
        }
        if (item.getItemId() == R.id.MnOp1_1) {
            miTxtView.setText(R.string.ejecutando_int_exp);
            intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.MnOp1_2) {
            miTxtView.setText(R.string.enviando_correo);
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_SUBJECT, "examen t5");
            intent.putExtra(Intent.EXTRA_TEXT, "Adriano. Intent implícito OK");
            intent.putExtra(Intent.EXTRA_EMAIL, new
                    String[]{"rbaebar@g.educaand.es"});
            startActivity(intent);

        }
        if (item.getItemId() == R.id.MnOp2_1) {
            miTxtView.setText(R.string.creando_list_view);
            miLisView.setVisibility(View.VISIBLE);
            miGridView.setVisibility(View.INVISIBLE);
            miSpinner.setVisibility(View.INVISIBLE);
        }
        if (item.getItemId() == R.id.MnOp2_2) {
            miTxtView.setText(R.string.creando_grid_view);
            miGridView.setVisibility(View.VISIBLE);
            miLisView.setVisibility(View.INVISIBLE);
            miSpinner.setVisibility(View.INVISIBLE);
        }
        if (item.getItemId() == R.id.MnOp2_3) {
            miTxtView.setText(R.string.creando_spinner);
            miSpinner.setVisibility(View.VISIBLE);
            miLisView.setVisibility(View.INVISIBLE);
            miGridView.setVisibility(View.INVISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }

    // ----------- LÓGICA DEL MENÚ CONTEXTUAL
    @Override
    public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = getMenuInflater();
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)
                menuInfo;
        final ListView miListView = findViewById(R.id.miListView);
        menu.setHeaderTitle(miListView.getAdapter().getItem(info.position).toString());
        if (info.position == 0) {
            inflater.inflate(R.menu.menu_contex, menu);
            return;
        }

        super.onCreateContextMenu(menu, view, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        Intent intent = new Intent(this, MainActivity3.class);
        if (item.getItemId() == R.id.ListaOpA1) {
            intent.putExtra("dia", "Lunes");
            resultLauncher.launch(intent);
        }
        return super.onContextItemSelected(item);
    }

    ActivityResultLauncher<Intent> resultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK) {
                    Intent intent = result.getData();
                    if (intent!=null){
                        Bundle extras = intent.getExtras();
                        assert extras != null;
                        Toast.makeText(MainActivity.this, Objects.requireNonNull(extras.getString("resultado")), Toast.LENGTH_SHORT).show();
                    }
                }
            });


}