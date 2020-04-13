package com.rom.works.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rom.works.entity.NotificationConfig;

public class NotificationConfigDatabaseHelper extends BaseDatabaseHelper {
    public static NotificationConfigDatabaseHelper INSTANCE;

    public NotificationConfigDatabaseHelper(Context context) {
        super(context);
        INSTANCE = this;
    }

    public NotificationConfig getConfig() {
        // Select All Query
        NotificationConfig config = null;
        String selectQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {
            config = new NotificationConfig();
            config.setId(cursor.getInt(0));
            config.setNotifyOnSunday(cursor.getInt(1) != 0);
            config.setNotifyOnMonday(cursor.getInt(2) != 0);
            config.setNotifyOnTuesday(cursor.getInt(3) != 0);
            config.setNotifyOnWednesday(cursor.getInt(4) != 0);
            config.setNotifyOnThursday(cursor.getInt(5) != 0);
            config.setNotifyOnFriday(cursor.getInt(6) != 0);
            config.setNotifyOnSaturday(cursor.getInt(7) != 0);
            config.setNotificationsOn(cursor.getInt(8) != 0);
            config.setNumberOfWords(cursor.getInt(9));
        }
        cursor.close();
        return config;
    }

    public int updateConfig(NotificationConfig config) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NOTIFY_ON_SUNDAY, getIntegerValueOfSwitch(config.isNotifyOnSunday()));
        values.put(KEY_NOTIFY_ON_MONDAY, getIntegerValueOfSwitch(config.isNotifyOnMonday()));
        values.put(KEY_NOTIFY_ON_TUESDAY, getIntegerValueOfSwitch(config.isNotifyOnTuesday()));
        values.put(KEY_NOTIFY_ON_WEDNESDAY, getIntegerValueOfSwitch(config.isNotifyOnWednesday()));
        values.put(KEY_NOTIFY_ON_THURSDAY, getIntegerValueOfSwitch(config.isNotifyOnThursday()));
        values.put(KEY_NOTIFY_ON_FRIDAY, getIntegerValueOfSwitch(config.isNotifyOnFriday()));
        values.put(KEY_NOTIFY_ON_SATURDAY, getIntegerValueOfSwitch(config.isNotifyOnSaturday()));
        values.put(KEY_NOTIFICATIONS_ON, getIntegerValueOfSwitch(config.isNotificationsOn()));
        values.put(KEY_NUMBER_OF_NOTIFICATIONS, config.getNumberOfWords());

        return db.update(TABLE_NOTIFICATIONS, values, KEY_ID + " = ?", new String[]{String.valueOf(config.getId())});
    }

    private int getIntegerValueOfSwitch(boolean isOn) {
        return isOn ? 1 : 0;
    }

    private int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_NOTIFICATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }
}
