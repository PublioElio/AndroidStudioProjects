package com.example.saved_by_the_call.ui.top_menu;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.saved_by_the_call.R;

public class TopMenu {

    public static void inflateMenu(MenuInflater inflater, Menu menu, int menuResourceId) {
        inflater.inflate(menuResourceId, menu);
    }

    public static boolean onOptionsItemSelected(Activity activity, MenuItem item) {
        int id = item.getItemId();
        boolean result = false;
        Intent intent;

        if (id == R.id.topSubMenuOptionB1) {
            result = true;
        }
        if (id == R.id.topSubMenuOptionB2) {
            intent = new Intent(activity, com.example.saved_by_the_call.ui.NewContactActivity.class);
            activity.startActivity(intent);
            result = true;
        }
        if (id == R.id.topSubMenuOptionB3) {
            result = true;
        }
        if (id == R.id.topSubMenuOptionB4) {
            intent = new Intent(activity, com.example.saved_by_the_call.ui.NewCallActivity.class);
            activity.startActivity(intent);
            result = true;
        }
        return result;
    }
}

