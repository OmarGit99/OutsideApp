package com.google.android.gms.location.sample.geofencing;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        sendNotification(context, intent.getStringExtra("data"));
    }

    public static void sendNotification(Context mcontext, String messageBody) {
        Intent intent = new Intent(mcontext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(mcontext, 0 /* Request code */, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) mcontext.getSystemService(Context.NOTIFICATION_SERVICE);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(mcontext.getString(R.string.default_notification_channel_id), "Rewards Notifications", NotificationManager.IMPORTANCE_DEFAULT);

            // Configure the notification channel.
            notificationChannel.setDescription("Channel description");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.setVibrationPattern(new long[]{0, 500, 200, 500});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(mcontext, mcontext.getString(R.string.default_notification_channel_id))
                .setContentTitle(mcontext.getString(R.string.app_name))
                .setSmallIcon(R.drawable.xxxhdpi)
                .setContentText("Reminder to reapply your protection!")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);


        notificationManager.notify(1122 /* ID of notification */, notificationBuilder.build());
    }
}
