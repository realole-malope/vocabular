package com.rom.works.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class BaseDatabaseHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_NAME = "VOCABULAR_LOCAL";
    static final String TABLE_NOTIFICATIONS = "NOTIFICATION_CONFIG";
    static final String KEY_ID = "ID";
    static final String KEY_NOTIFY_ON_SUNDAY = "NOTIFY_ON_SUNDAY";
    static final String KEY_NOTIFY_ON_MONDAY = "NOTIFY_ON_MONDAY";
    static final String KEY_NOTIFY_ON_TUESDAY = "NOTIFY_ON_TUESDAY";
    static final String KEY_NOTIFY_ON_WEDNESDAY = "NOTIFY_ON_WEDNESDAY";
    static final String KEY_NOTIFY_ON_THURSDAY = "NOTIFY_ON_THURSDAY";
    static final String KEY_NOTIFY_ON_FRIDAY = "NOTIFY_ON_FRIDAY";
    static final String KEY_NOTIFY_ON_SATURDAY = "NOTIFY_ON_SATURDAY";
    static final String KEY_NOTIFICATIONS_ON = "NOTIFICATIONS_ON";
    static final String KEY_NUMBER_OF_NOTIFICATIONS = "NUMBER_OF_NOTIFICATIONS";
    static final String TABLE_DEFINITION = "DEFINITION";
    static final String KEY_WORD = "WORD";
    static final String KEY_MEANING = "MEANING";
    static final String KEY_EXAMPLE = "EXAMPLE";
    static final String KEY_DATE_CREATED = "DATE_CREATED";
    static final String DATE_FORMAT = "YYYY-MM-DD HH:MM:SS.SSS";
    final DateFormat df = new SimpleDateFormat(DATE_FORMAT);

    public BaseDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDefinitionTable(db, "CREATE TABLE " + TABLE_DEFINITION + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_WORD + " TEXT NOT NULL UNIQUE,"
                + KEY_MEANING + " TEXT NOT NULL,"
                + KEY_EXAMPLE + " TEXT NOT NULL,"
                + KEY_DATE_CREATED + " TEXT NOT NULL"
                + ")");


        createDefinitionTable(db, "CREATE TABLE " + TABLE_NOTIFICATIONS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NOTIFY_ON_SUNDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFY_ON_MONDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFY_ON_TUESDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFY_ON_WEDNESDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFY_ON_THURSDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFY_ON_FRIDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFY_ON_SATURDAY + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NOTIFICATIONS_ON + " INTEGER NOT NULL DEFAULT 0,"
                + KEY_NUMBER_OF_NOTIFICATIONS + " INTEGER NOT NULL DEFAULT 0"
                + ")");
        storeInitData(db);
    }

    private void createDefinitionTable(SQLiteDatabase db, String sql) {
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DEFINITION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTIFICATIONS);
        onCreate(db);
    }

    private void storeInitData(SQLiteDatabase db) {
        emptyNotificationsDB(db);
        ContentValues values = new ContentValues();
        values.put(KEY_NOTIFY_ON_SUNDAY, 0);
        values.put(KEY_NOTIFY_ON_MONDAY, 0);
        values.put(KEY_NOTIFY_ON_TUESDAY, 0);
        values.put(KEY_NOTIFY_ON_WEDNESDAY, 0);
        values.put(KEY_NOTIFY_ON_THURSDAY, 0);
        values.put(KEY_NOTIFY_ON_FRIDAY, 0);
        values.put(KEY_NOTIFY_ON_SATURDAY, 0);
        values.put(KEY_NOTIFICATIONS_ON, 0);
        values.put(KEY_NUMBER_OF_NOTIFICATIONS, 0);

        db.insert(TABLE_NOTIFICATIONS, null, values);
    }

    private void emptyNotificationsDB(SQLiteDatabase db) {
        db.delete(TABLE_NOTIFICATIONS, KEY_ID + " > ?", new String[]{String.valueOf(0)});
    }
}
