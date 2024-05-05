package com.example.saved_by_the_call.ui.top_menu;

import android.app.Activity;
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
        if(id == R.id.topMenuOpA1){
            result = true;
        }
        if(id == R.id.topMenuOpA2){
            result = true;
        }
        if(id == R.id.topSubMenuOptionB1){
            result = true;
        }
        if(id == R.id.topSubMenuOptionB2){
            result = true;
        }
        if(id == R.id.topSubMenuOptionB3){
            result = true;
        }
        if(id == R.id.topSubMenuOptionB4){
            result = true;
        }
        return result;
    }
}

