package com.example.pruebas_botones;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class ButtonPressService extends Service {
    // Guardo el código de la tecla de volume up
    private static final int VOLUME_UP_CODE = KeyEvent.KEYCODE_VOLUME_UP;
    // establezco un intervalo de tiempo en el cual el usuario debe pulsar la tecla
    private static final long TIME_INTERVAL = 50000;
    // número de veces que el usuario debe pulsar la tecla
    private static final int NUM_BUTTON_PRESS = 3;
    // estas variables son contadores
    private int pressCount = 0;
    private long lastPressTime = 0;

    private BroadcastReceiver volumeButtonReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {


                if (Intent.ACTION_MEDIA_BUTTON.equals(intent.getAction())) {
                    KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
                    if (event != null && event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP && event.getAction() == KeyEvent.ACTION_UP) {
                        int keyCode = intent.getIntExtra(String.valueOf(KeyEvent.KEYCODE_VOLUME_UP), -1);
                        if (keyCode == VOLUME_UP_CODE) {
                            long currentTime = System.currentTimeMillis();
                            if (currentTime - lastPressTime < TIME_INTERVAL) {
                                pressCount++;
                            } else {
                                pressCount = 1;
                            }

                            lastPressTime = currentTime;

                            if (pressCount == NUM_BUTTON_PRESS) {
                                Toast.makeText(context, "Ha presitonado tres veces el botón de volume up", Toast.LENGTH_SHORT).show();
                                Log.i("INFO","Pulsadas tres veces volume up");
                                pressCount = 0;
                            } else {
                                Log.i("INFO", "La cantidad de veces que ha pulsado volume up es: " + pressCount);
                            }
                        }


                    }
                }
        }
    };

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_MEDIA_BUTTON);
        registerReceiver(volumeButtonReceiver, filter);
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(volumeButtonReceiver);
    }
}

