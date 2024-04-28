package com.example.pruebas_botones02;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;

public class VolumeButtonService extends Service {
    private BroadcastReceiver receiver;
    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                if (event != null && event.getAction() == KeyEvent.ACTION_DOWN &&
                        event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP) {
                    Log.i("INFO", "se han pulsado los botones correctamente");
                }
            }
        };
        registerReceiver(receiver, filter);
        // Para que el servicio esté escuchando en primer plano
        startForeground(1, null);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

