package com.rom.works.ui.notifications;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.rom.works.R;
import com.rom.works.entity.Definition;
import com.rom.works.service.UserDefinitionDatabaseHelper;

import java.util.List;
import java.util.Random;

public class NotificationIssuer {
    private static final String CHANNEL_ID = "VOCABULAR_CH";
    private Context context;
    private UserDefinitionDatabaseHelper helper;

    public NotificationIssuer(Context context) {
        this.context = context;
        helper = new UserDefinitionDatabaseHelper(context);
        createNotificationChannel();
    }

    public void generateNotification() {
        List<Definition> allContacts = helper.getAllContacts();
        int index = (new Random()).nextInt(allContacts.size());
        NotificationCompat.Builder notificationBuilder = getNotificationBuilder(allContacts.get(index));
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        // notificationId is a unique int for each notification that you must define
        int notificationId = 8080;
        notificationManager.notify(notificationId + index, notificationBuilder.build());
    }

    private NotificationCompat.Builder getNotificationBuilder(Definition definition) {
        String message = definition.getMeaning().concat(" \nExample: ".concat(definition.getExample()));
        return new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("Word for the day: "+definition.getWord())
                .setTicker("Vocabular")
                .setUsesChronometer(true)
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = context.getString(R.string.channel_name);
            String description = context.getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
            assert notificationManager != null;
            notificationManager.createNotificationChannel(channel);
        }
    }
}
