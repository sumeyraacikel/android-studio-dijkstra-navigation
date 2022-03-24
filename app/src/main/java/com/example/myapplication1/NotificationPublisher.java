package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;


public class NotificationPublisher extends BroadcastReceiver {



    @Override
    public void onReceive(Context context, Intent intent) {
        generateNotification(context);
        String action = intent.getAction();
//        if (SOMEACTION.equals(action)) {
//            generateNotification(context,"note");
//        }
    }

    @SuppressWarnings("deprecation")
    private void generateNotification(@NonNull Context context) {
        System.out.println("note" +"++++++++++2");
        int icon = R.drawable.icon;
        long when = System.currentTimeMillis() + (60 * 1000);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, "note", when);
        String title = context.getString(R.string.app_name);
        String subTitle = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("content", "note");
        @SuppressLint("UnspecifiedImmutableFlag") PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        Log.d("asda","fdfrs");

//        notification.setLatestEventInfo(context, title, subTitle, intent);
//        //To play the default sound with your notification:
//        notification.defaults |= Notification.DEFAULT_SOUND;
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;
//        notification.defaults |= Notification.DEFAULT_VIBRATE;
//        notificationManager.notify(0, notification);
        //notificationManager.cancel(notificationNumber);


    }}