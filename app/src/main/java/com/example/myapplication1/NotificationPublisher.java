package com.example.myapplication1;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class NotificationPublisher extends BroadcastReceiver {
    //private final String SOMEACTION = "com.burak.alarm.ACTION";


    @Override
    public void onReceive(Context context, Intent intent) {
        generateNotification(context, "note");
        String action = intent.getAction();
//        if (SOMEACTION.equals(action)) {
//            generateNotification(context,"note");
//        }
    }

    @SuppressWarnings("deprecation")
    private void generateNotification(Context context, String message) {
        System.out.println(message+"++++++++++2");
        int icon = R.drawable.icon;
        long when = System.currentTimeMillis() + (1*60*1000);
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        String title = context.getString(R.string.app_name);
        String subTitle = context.getString(R.string.app_name);
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("content", message);
        PendingIntent intent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
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