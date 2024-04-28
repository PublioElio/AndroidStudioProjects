package com.example.pruebas_alarma_00;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

public class FakeCallLauncher extends BroadcastReceiver {
    private static final String CHANNEL_ID = "alarm_channel";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "ALARMA", Toast.LENGTH_LONG).show();
    }

    public static void setAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, FakeCallLauncher.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + 60000; // 1 minuto
        if (alarmManager != null) {
            alarmManager.setExact(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                    futureInMillis, pendingIntent);
        }
    }

    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Alarm Channel";
            String description = "Canal para las notificaciones de alarma";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    public static void showNotification(Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle("Title")
                .setContentText("Mensaje mostrado por la aplicación")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}

