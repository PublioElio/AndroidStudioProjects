package com.example.unidad_05_apuntes06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int menuOp1 = 1;
    private static final int menuOp2 = 2;
    private static final int menuOp3 = 3;
    private static final int menuOp2_1 = 4;
    private static final int menuOp2_2 = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
// Creo menú desde XML
/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int opcion = item.getItemId();

        // Como en el ejemplo voy a hacer lo mismo en todas las opciones del menú no voy a separar
        // las distintas opciones en un else-if
        if(opcion > 0){
            Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    // Creo menú con Java
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(Menu.NONE, menuOp1, menu.NONE, "Opción A desde Java");

        SubMenu subMenu = menu.addSubMenu(Menu.NONE, menuOp2, Menu.NONE, "Opción B desde Java");
        subMenu.add(Menu.NONE, menuOp2_1, menu.NONE, "Opción B.1 desde Java");
        subMenu.add(Menu.NONE, menuOp2_2, menu.NONE, "Opción B.2 desde Java");

        menu.add(Menu.NONE, menuOp3, menu.NONE, "Opción C desde Java");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case menuOp1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case menuOp2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case menuOp3:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case menuOp2_1:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
            case menuOp2_2:
                Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}