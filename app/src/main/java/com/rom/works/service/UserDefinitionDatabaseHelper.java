package com.rom.works.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rom.works.entity.Definition;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class UserDefinitionDatabaseHelper extends BaseDatabaseHelper {
    private static final String TAG = UserDefinitionDatabaseHelper.class.getName();
    private static UserDefinitionDatabaseHelper INSTANCE;

    public UserDefinitionDatabaseHelper(Context context) {
        super(context);
        INSTANCE = this;
    }

    public static UserDefinitionDatabaseHelper getInstance() {
        return INSTANCE;
    }

    public void addDefinition(Definition definition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, definition.getWord());
        values.put(KEY_MEANING, definition.getMeaning());
        values.put(KEY_EXAMPLE, definition.getExample());
        values.put(KEY_DATE_CREATED, df.format(definition.getDateCreated()));

        db.insert(TABLE_DEFINITION, null, values);
        db.close();
    }

    public List<Definition> getAllContacts() {
        List<Definition> contactList = new ArrayList<>();
        // Select All Query  
        String selectQuery = "SELECT  * FROM " + TABLE_DEFINITION;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list  
        if (cursor.moveToFirst()) {
            do {
                try {
                    Definition definition = new Definition();
                    definition.setId(Integer.parseInt(cursor.getString(0)));
                    definition.setWord(cursor.getString(1));
                    definition.setMeaning(cursor.getString(2));
                    definition.setExample(cursor.getString(3));
                    definition.setDateCreated(df.parse(cursor.getString(4)));
                    // Adding definition to list
                    contactList.add(definition);
                } catch (ParseException e) {
                    Log.e(TAG, "getAllContacts: ", e);
                }
            } while (cursor.moveToNext());
        }

        // return Definition list  
        return contactList;
    }

    // Deleting single definition
    public void deleteDefinition(Definition definition) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DEFINITION, KEY_WORD + " = ?",
                new String[]{String.valueOf(definition.getWord())});
        db.close();
    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_DEFINITION;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }
}
