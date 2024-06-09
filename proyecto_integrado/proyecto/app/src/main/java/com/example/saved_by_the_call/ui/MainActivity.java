package com.example.saved_by_the_call.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;

import com.example.saved_by_the_call.R;
import com.example.saved_by_the_call.ui.top_menu.TopMenu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        final ImageView contacts = findViewById(R.id.imgViewContactsMain);
        final ImageView calls = findViewById(R.id.imgViewCallsMain);

        setupPopupMenu(contacts,
                R.menu.pop_menu_contacts_main,
                R.id.pop_menu_contacts_main_option_1,
                NewContactActivity.class,
                R.id.pop_menu_contacts_main_option_2,
                ContactListActivity.class);
        setupPopupMenu(calls,
                R.menu.pop_menu_calls_main,
                R.id.pop_menu_calls_main_option_1,
                NewCallActivity.class,
                R.id.pop_menu_calls_main_option_2,
                CallListActivity.class);
    }

    /**
     * Setup the popup menu for the image view.
     *
     * @param imageView    The image view.
     * @param menuResId    The menu resource id.
     * @param option1Id    The option 1 id.
     * @param option1Class The option 1 class.
     * @param option2Id    The option 2 id.
     * @param option2Class The option 2 class.
     */
    private void setupPopupMenu(ImageView imageView, int menuResId, int option1Id,
                                Class<?> option1Class, int option2Id, Class<?> option2Class) {
        imageView.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper(MainActivity.this,
                    R.style.PopupMenuMain), imageView);
            popupMenu.getMenuInflater().inflate(menuResId, popupMenu.getMenu());

            popupMenu.setOnMenuItemClickListener(item -> {
                int id = item.getItemId();
                boolean result = TopMenu.onOptionsItemSelected(MainActivity.this, item);
                Intent intent = null;
                if (id == option1Id) {
                    intent = new Intent(MainActivity.this, option1Class);
                    result = true;
                } else if (id == option2Id) {
                    intent = new Intent(MainActivity.this, option2Class);
                    result = true;
                }
                if (intent != null) {
                    MainActivity.this.startActivity(intent);
                }
                return result;
            });

            popupMenu.show();
        });
    }
}


